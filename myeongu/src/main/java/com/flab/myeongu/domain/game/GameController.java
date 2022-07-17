package com.flab.myeongu.domain.game;

import com.flab.myeongu.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping("/game/start")
    public String gameStart() {

        gameService.createGame();

        return "ok";
    }

    @PostMapping("/game/{roomId}/answer")
    public String playGame(@PathVariable Long roomId) {

        return "ok";
    }

}
