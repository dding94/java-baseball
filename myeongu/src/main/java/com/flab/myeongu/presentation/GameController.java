package com.flab.myeongu.presentation;

import com.flab.myeongu.application.GameService;
import com.flab.myeongu.domain.Game;
import com.flab.myeongu.infrastructure.GameRepository;
import com.flab.myeongu.domain.score.ScoreRepository;
import lombok.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class GameController {

    private final GameRepository gameRepository;
    private final GameService gameService;
    private final ScoreRepository scoreRepository;

    @PostMapping("/game/start")
    public GameResultResponse gameStart() {

        Game game = gameService.startGame();

        return GameResultResponse.startGameResponse(game);
    }

    @Getter
    @AllArgsConstructor
    static class GameResultResponse<T> {
        private boolean success;
        private T data;

        static GameResultResponse startGameResponse(Game game) {
            return new GameResultResponse(true, StartGameResponse.startGameResponse(game));
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

//
//    @PostMapping("/game/{roomId}/answer")
//    public JSONObject gamePlaying(@PathVariable Long roomId, @RequestBody InputRequestVO answer) {
//
//        Game findGame = gameService.playingGame(gameRepository.findByRoomId(roomId), answer.getAnswer());
//
//        return gamePlayDataToJSON(findGame);
//    }
//
//    @GetMapping("/game/{roomId}")
//    public JSONObject gameResult(@PathVariable Long roomId) {
//
//        Game findGame = gameRepository.findByRoomId(roomId);
//
//        return gameResultToJSON(findGame);
//    }
//
//    @GetMapping("/game/{roomId}/history")
//    public JSONObject gameHistory(@PathVariable Long roomId) {
//
//        Game findGame = gameRepository.findByRoomId(roomId);
//        ArrayList<ScoreDTO> history = scoreRepository.findHistory(findGame.getRoomId());
//
//        return gameHistoryToJSON(findGame, history);
//    }



}
