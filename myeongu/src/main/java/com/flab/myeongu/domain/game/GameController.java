package com.flab.myeongu.domain.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.myeongu.domain.InputRequestVO;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class GameController {

    private final GameRepository gameRepository;
    private final GameService gameService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/game/start")
    public JSONObject gameStart() {

        Game game = gameService.startGame();

        JSONObject response = gameStartDataToJSON(game);

        return response;
    }

    @PostMapping("/game/{roomId}/answer")
    public JSONObject gamePlaying(@PathVariable Long roomId, @RequestBody InputRequestVO answer) {

        Game game = gameService.playingGame(gameRepository.findByRoomId(roomId), answer.getAnswer());

        JSONObject response = gamePlayDataToJSON(game);

        return response;
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
