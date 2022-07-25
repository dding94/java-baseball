package com.flab.myeongu.infrastructure;


import com.flab.myeongu.domain.Game;
import com.flab.myeongu.domain.Score;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class GameRepository {

    private static long sequence = 100L;
    private static final Map<Long, Game> store = new HashMap<>();

    public Game save(Game game) {

        game.setRoomId(sequence);
        store.put(game.getRoomId(), game);

        return game;
    }

    public Game findByRoomId(Long roomId) {
        return store.get(roomId);
    }

    public boolean isPlayCheck(Game game) {
//        game.setRemainingCount(game.getRemainingCount() - 1);
//        game.setAnswerCount(game.getAnswerCount() + 1);
//
//        if (game.getRemainingCount() < 0 || game.getAnswerCount() > 10) {
//            game.setSuccess(false);
//            return false;
//        }

        return true;
    }

    public void saveHistory(Game game, Score score, String userAnswer) {
//        game.setScore(score);
//        scoreRepository.save(game, userAnswer);
    }


}
