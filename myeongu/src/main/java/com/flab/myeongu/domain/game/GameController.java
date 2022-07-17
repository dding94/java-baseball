package com.flab.myeongu.domain.game;

import com.flab.myeongu.domain.DTO.GameStartDTO;
import com.flab.myeongu.domain.Data;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GameController {

    private final GameRepository gameRepository;

    @PostMapping("/game/start")
    public JSONObject gameStart() {

        Game game = Game.builder()
                .remainingCount(0)
                .success(true)
                .build();

        gameRepository.save(game);

        JSONObject data1 = new JSONObject();
        data1.put("roomId", game.getRoomId());

        JSONObject data2 = new JSONObject();
        data2.put("success", game.isSuccess());
        data2.put("data", data1);

        return data2;
    }


}
