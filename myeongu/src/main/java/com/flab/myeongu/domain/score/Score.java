package com.flab.myeongu.domain.score;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Score {
    private int strike;
    private int ball;
    private int out;
    private boolean correct;
}
