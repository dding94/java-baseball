package com.flab.myeongu.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class Score {

    private int strike;
    private int ball;
    private int out;
}
