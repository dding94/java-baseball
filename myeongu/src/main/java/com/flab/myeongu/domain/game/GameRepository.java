package com.flab.myeongu.domain.game;


import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class GameRepository {

    private static long sequence = 0L;
    private static final Map<Long, Game> store = new HashMap<>();

    public Game save() {
        Game game = makeStartGame();
        game.setRoomId(++sequence);
        game.setAnswer(createRandomNum());
        store.put(game.getRoomId(), game);

        return game;
    }

    public Game findByRoomId(Long roomId) {
        return store.get(roomId);
    }

    public void update(Game game) {
        game.setRemainingCount(game.getRemainingCount() + 1);
    }


    private Game makeStartGame() {
        return Game.builder()
                .remainingCount(0)
                .success(true)
                .build();
    }

    private ArrayList<Integer> createRandomNum() {
        ArrayList<Integer> answer = new ArrayList<>();

        while (answer.size() < 3) {
            int random = (int) (Math.random() * 9) + 1;

            if (!answer.contains(random)) {
                answer.add(random);
            }
        }

        return answer;
    }
}
