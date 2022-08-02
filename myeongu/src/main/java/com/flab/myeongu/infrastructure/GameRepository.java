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
        game.setRoomId(sequence++);
        store.put(game.getRoomId(), game);

        return game;
    }

    public Game saveHistory(Game game, Score score, String userAnswer) {
        game.saveHistory(score, userAnswer);
        store.put(game.getRoomId(), game);

        return game;
    }

    public Game findByRoomId(Long roomId) {
        return store.get(roomId);
    }


}
