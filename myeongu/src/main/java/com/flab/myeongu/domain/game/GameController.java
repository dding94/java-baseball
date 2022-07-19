package com.flab.myeongu.domain.game;

import com.flab.myeongu.domain.InputRequestVO;
import com.flab.myeongu.domain.dto.GamePlayDTO;
import com.flab.myeongu.domain.dto.ScoreDTO;
import com.flab.myeongu.domain.score.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

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
        data2.put("success", findGame.isSuccess());
        data2.put("data", data1);

        return data2;
    }

    private JSONObject gamePlayDataToJSON(Game game) {
        GamePlayDTO gamePlayDTO = GamePlayDTO.builder()
                .correct(game.getScore().isCorrect())
                .remainingCount(game.getRemainingCount())
                .strike(game.getScore().getStrike())
                .ball(game.getScore().getBall())
                .out(game.getScore().getOut())
                .build();

        Map<Object, Object> obj = new LinkedHashMap<>();
        obj.put("success", game.isSuccess());
        obj.put("data", gamePlayDTO);

        JSONObject data = new JSONObject(obj);

        return data;
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
