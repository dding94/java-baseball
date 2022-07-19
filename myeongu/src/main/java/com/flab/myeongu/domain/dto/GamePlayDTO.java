package com.flab.myeongu.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class GamePlayDTO {
    private boolean correct;
    private int remainingCount;
    private int strike;
    private int ball;
    private int out;
}
