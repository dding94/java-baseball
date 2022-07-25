package com.flab.myeongu.domain;

import com.flab.myeongu.util.GameSupport;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Builder
public class Game {

    private long roomId;
    private boolean correct;
    private int remainingCount;
    private int answerCount;
    private String answer;

    public static Game makeGame() {
        return Game.builder()
                .remainingCount(10)
                .answerCount(0)
                .answer(GameSupport.createRandomNum())
                .correct(false)
                .build();
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }
}
