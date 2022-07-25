package com.flab.myeongu.domain;

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
