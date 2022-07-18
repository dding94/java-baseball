package com.flab.myeongu.domain.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.myeongu.domain.InputRequestVO;
import com.flab.myeongu.domain.score.ScoreDTO;
import com.flab.myeongu.domain.score.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
public class GameController {

    private final GameRepository gameRepository;
    private final GameService gameService;
    private final ScoreRepository scoreRepository;

    @PostMapping("/game/start")
    public JSONObject gameStart() {

        Game game = gameService.startGame();

        return gameStartDataToJSON(game);
    }

    @PostMapping("/game/{roomId}/answer")
    public JSONObject gamePlaying(@PathVariable Long roomId, @RequestBody InputRequestVO answer) {

        Game findGame = gameService.playingGame(gameRepository.findByRoomId(roomId), answer.getAnswer());

        return gamePlayDataToJSON(findGame);
    }

    @GetMapping("/game/{roomId}")
    public JSONObject gameResult(@PathVariable Long roomId) {
        Game findGame = gameRepository.findByRoomId(roomId);

        return gameResultToJSON(findGame);
    }

    @GetMapping("/game/{roomId}/history")
    public JSONObject gameHistory(@PathVariable Long roomId) {
        //todo
        Game findGame = gameRepository.findByRoomId(roomId);
        ArrayList<ScoreDTO> history = scoreRepository.findHistory(findGame.getRoomId());

        return gameHistoryToJSON(findGame, history);
    }

    private JSONObject gameHistoryToJSON(Game findGame, ArrayList<ScoreDTO> history) {
        JSONArray histories = new JSONArray();
        for (ScoreDTO score : history) {
            JSONObject data1 = new JSONObject();
            data1.put("strike", score.getStrike());
            data1.put("ball", score.getBall());
            data1.put("out", score.getOut());
            data1.put("answer", score.getAnswer());

            histories.add(data1);
        }

        JSONObject data = new JSONObject();
        data.put("histories", histories);
        data.put("success", findGame.isSuccess());

        return data;
    }

    private JSONObject gameResultToJSON(Game findGame) {
        JSONObject data1 = new JSONObject();
        data1.put("remainingCount", findGame.getRemainingCount());
        data1.put("answerCount", findGame.getAnswerCount());

        JSONObject data2 = new JSONObject();
        data2.put("data", data1);
        data2.put("success", findGame.isSuccess());

        return data2;
    }

    private JSONObject gamePlayDataToJSON(Game game) {
        JSONObject data1 = new JSONObject();
        data1.put("correct", game.getScore().isCorrect());
        data1.put("remainingCount", game.getRemainingCount());
        data1.put("strike", game.getScore().getStrike());
        data1.put("ball", game.getScore().getBall());
        data1.put("out", game.getScore().getOut());

        JSONObject data2 = new JSONObject();
        data2.put("data", data1);
        data2.put("success", game.isSuccess());

        return data2;
    }

    private JSONObject gameStartDataToJSON(Game game) {
        JSONObject data1 = new JSONObject();
        data1.put("roomId", game.getRoomId());

        JSONObject data2 = new JSONObject();
        data2.put("success", game.isSuccess());
        data2.put("data", data1);

        return data2;
    }
}
