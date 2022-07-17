package com.flab.myeongu.domain.game;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Builder
public class Game {

    private long roomId;
    private boolean success;
    private int remainingCount;
}
