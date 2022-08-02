package com.flab.myeongu.application;

import com.flab.myeongu.domain.Game;
import com.flab.myeongu.infrastructure.GameRepository;
import com.flab.myeongu.domain.Score;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    public Game startGame() {
        return gameRepository.save(Game.makeGame());
    }

    public Game playingGame(Long roomId, String userAnswer) {
        Game game = gameRepository.findByRoomId(roomId);

        if (!game.play()) {
            throw new RuntimeException();
        }
        Score score = game.answerCheck(userAnswer);

        return gameRepository.saveHistory(game, score, userAnswer);
    }

    public Game findGame(Long roomId) {
        return gameRepository.findByRoomId(roomId);
    }
}
