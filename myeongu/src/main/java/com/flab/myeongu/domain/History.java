package com.flab.myeongu.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class History {

    private String answer;
    private Score result;

    public int strikeCount() {
        return result.getStrike();
    }

    public int ballCount() {
        return result.getBall();
    }

    public int outCount() {
        return result.getOut();
    }
}
