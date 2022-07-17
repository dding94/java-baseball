package com.flab.myeongu.domain.DTO;

import com.flab.myeongu.domain.Data;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GameStartDTO {
    private boolean success;
    private Data data;
}
