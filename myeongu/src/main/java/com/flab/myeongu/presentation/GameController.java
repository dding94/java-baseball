package com.flab.myeongu.presentation;

import com.flab.myeongu.application.GameService;
import com.flab.myeongu.domain.Game;
import com.flab.myeongu.domain.History;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    @PostMapping("start")
    public GameResultResponse gameStart() {
        Game game = gameService.startGame();

        return GameResultResponse.startGameResponse(game);
    }

    @PostMapping("{roomId}/answer")
    public GameResultResponse gamePlaying(@PathVariable Long roomId, @RequestBody InputRequest answer) {
        Game game = gameService.playingGame(roomId, answer.getAnswer());

        return GameResultResponse.playGameResponse(game);
    }

    @GetMapping("{roomId}")
    public GameResultResponse gameResult(@PathVariable Long roomId) {

        Game game = gameService.findGame(roomId);

        return GameResultResponse.resultGameResponse(game);
    }

    @GetMapping("{roomId}/history")
    public GameResultResponse gameHistory(@PathVariable Long roomId) {

        Game game = gameService.findGame(roomId);

        return GameResultResponse.historyGameResponse(game);
    }

    @GetMapping("test")
    public ErrorGameResponse testMapping() {

        return ErrorGameResponse.playGameResponse();
    }

    @Getter
    @AllArgsConstructor
    static class ErrorGameResponse<T> {

        private boolean success;
        private T data;
        private T Error;

        static ErrorGameResponse playGameResponse() {
            return new ErrorGameResponse(false, null, EndGameResponse.endGameResponse());
        }
    }

    @Getter
    @AllArgsConstructor
    static class EndGameResponse {

        private String code;
        private String message;

        static EndGameResponse endGameResponse() {
            return new EndGameResponse("CLOSE_GAME", "");
        }
    }

    @Getter
    @AllArgsConstructor
    static class GameResultResponse<T> {

        private boolean success;
        private T data;

        static GameResultResponse startGameResponse(Game game) {
            return new GameResultResponse(true, StartGameResponse.startGameResponse(game));
        }

        static GameResultResponse playGameResponse(Game game) {
            return new GameResultResponse(true, PlayingGameResponse.playingGameResponse(game));
        }

        static GameResultResponse resultGameResponse(Game game) {
            return new GameResultResponse(true, ResultGameResponse.resultGameResponse(game));
        }

        static GameResultResponse historyGameResponse(Game game) {
            return new GameResultResponse(true, HistoryGameResponse.historyGameResponse(game));
        }
    }

    @Getter
    @AllArgsConstructor
    static class StartGameResponse {

        private long roomId;

        static StartGameResponse startGameResponse(Game game) {
            return new StartGameResponse(game.getRoomId());
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    static class PlayingGameResponse {

        private boolean correct;
        private int remainingCount;
        private int strike;
        private int ball;
        private int out;

        static PlayingGameResponse playingGameResponse(Game game) {
            return PlayingGameResponse.builder()
                    .correct(game.isCorrect())
                    .remainingCount(game.getRemainingCount())
                    .strike(game.nowPlayGameInfo().strikeCount())
                    .ball(game.nowPlayGameInfo().ballCount())
                    .out(game.nowPlayGameInfo().outCount())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    static class ResultGameResponse {

        private int remainingCount;
        private int answerCount;

        static ResultGameResponse resultGameResponse(Game game) {
            return new ResultGameResponse(game.getRemainingCount(), game.getAnswerCount());
        }
    }

    @Getter
    @AllArgsConstructor
    static class HistoryGameResponse {

        private ArrayList<History> histories;

        static HistoryGameResponse historyGameResponse(Game game) {
            return new HistoryGameResponse(game.getHistories());
        }
    }

    @Getter
    @AllArgsConstructor
    public class InputRequest {
        private String answer;
    }
}
