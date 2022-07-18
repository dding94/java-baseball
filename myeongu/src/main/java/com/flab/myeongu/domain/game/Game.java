package com.flab.myeongu.domain.game;

import com.flab.myeongu.domain.score.Score;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@Builder
public class Game {

    private long roomId;
    private boolean success;
    private int remainingCount;
    private int answerCount;
    private ArrayList<Integer> answer = new ArrayList<>();
    private Score score;
}
