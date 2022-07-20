package com.flab.myeongu.domain;

import lombok.Builder;

import java.util.ArrayList;

@Builder
public class Data {
    private long roomId;
    private int remainingCount;
    private int answerCount;
    private int strike;
    private int ball;
    private int out;
}
