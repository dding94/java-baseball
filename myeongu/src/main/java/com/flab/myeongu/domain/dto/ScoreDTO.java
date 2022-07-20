package com.flab.myeongu.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ScoreDTO {
    private String answer;
    private int strike;
    private int ball;
    private int out;
}
