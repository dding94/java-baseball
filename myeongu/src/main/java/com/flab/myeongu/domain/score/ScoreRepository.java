package com.flab.myeongu.domain.score;

import com.flab.myeongu.domain.game.Game;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
@Slf4j
public class ScoreRepository {

    private static final Map<Long, ArrayList<ScoreDTO>> history = new HashMap<>();

    public void save(Game game, String userAnswer) {

        Score score = game.getScore();

        ScoreDTO scoreDTO = new ScoreDTO();
        scoreDTO.setStrike(score.getStrike());
        scoreDTO.setBall(score.getBall());
        scoreDTO.setOut(score.getOut());
        scoreDTO.setAnswer(userAnswer);

        ArrayList<ScoreDTO> list = history.getOrDefault(game.getRoomId(), new ArrayList<ScoreDTO>());
        list.add(scoreDTO);

        history.put(game.getRoomId(), list);
    }

    public ArrayList<ScoreDTO> findHistory(Long roomId) {
        return history.get(roomId);
    }


}
