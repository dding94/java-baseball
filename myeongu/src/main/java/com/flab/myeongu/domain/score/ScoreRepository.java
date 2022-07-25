package com.flab.myeongu.domain.score;

import com.flab.myeongu.domain.Score;
import com.flab.myeongu.domain.dto.ScoreDTO;
import com.flab.myeongu.domain.Game;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
@Slf4j
public class ScoreRepository {

//    private static final Map<Long, ArrayList<ScoreDTO>> history = new HashMap<>();
//
//    public void save(Game game, String userAnswer) {
//
//        Score score = game.getScore();
//
//        ScoreDTO scoreDTO = ScoreDTO.builder()
//                .strike(score.getStrike())
//                .ball(score.getBall())
//                .out(score.getOut())
//                .answer(userAnswer)
//                .build();
//
//        ArrayList<ScoreDTO> list = history.getOrDefault(game.getRoomId(), new ArrayList<ScoreDTO>());
//        list.add(scoreDTO);
//
//        history.put(game.getRoomId(), list);
//    }
//
//    public ArrayList<ScoreDTO> findHistory(Long roomId) {
//        return history.get(roomId);
//    }
//

}
