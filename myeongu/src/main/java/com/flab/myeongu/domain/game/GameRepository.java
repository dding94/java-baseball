package com.flab.myeongu.domain.game;


import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class GameRepository {

    private static long sequence = 0L;
    private static final Map<Long, Game> store = new HashMap<>();

    public Game save(Game game) {
        game.setRoomId(++sequence);
        store.put(game.getRoomId(), game);
        return game;
    }

    public Game findByRoomId(Long roomId) {
        return store.get(roomId);
    }
}
