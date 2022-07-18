package com.flab.myeongu.domain.score;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoreDTO {
    private String answer;
    private int strike;
    private int ball;
    private int out;
}
