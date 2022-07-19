package com.flab.myeongu.domain.game;


import com.flab.myeongu.domain.score.Score;
import com.flab.myeongu.domain.score.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class GameRepository {

    private final ScoreRepository scoreRepository;
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

    public boolean isPlayCheck(Game game) {
        game.setRemainingCount(game.getRemainingCount() - 1);
        game.setAnswerCount(game.getAnswerCount() + 1);

        if (game.getRemainingCount() < 0 || game.getAnswerCount() > 10) {
            game.setSuccess(false);
            return false;
        }

        return true;
    }

    public void saveHistory(Game game, Score score, String userAnswer) {
        game.setScore(score);
        scoreRepository.save(game, userAnswer);
    }


    private Game makeStartGame() {
        return Game.builder()
                .remainingCount(10)
                .answerCount(0)
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
