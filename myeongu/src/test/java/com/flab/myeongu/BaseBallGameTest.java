package com.flab.myeongu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;


class BaseBallGameTest {


    @RepeatedTest(1000)
    @DisplayName("랜덤 숫자 만들기")
    void createRandomNum() {
        ArrayList<Integer> list = new ArrayList<>();

        while (list.size() < 3) {
            int random = (int) (Math.random() * 9) + 1;

            if (!list.contains(random)) {
                list.add(random);
            }
        }

        //중복되는 수가 있으면 안된다
        assertThat(list.get(0))
                .isNotEqualTo(list.get(1))
                .isNotEqualTo(list.get(2));

        //3개의 숫자를 만드는지 확인
        assertThat(list.size()).isEqualTo(3);

    }

    @Test
    void answerCheck() {
        String input = "365";
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(2, 1, 9));

        int strike = 0;
        int ball = 0;
        int out = 0;

        for (int i = 0; i < input.length(); i++) {
            if (list.get(i) == Character.getNumericValue(input.charAt(i))) {
                strike++;
                continue;
            }
            if (list.contains(Character.getNumericValue(input.charAt(i)))) {
                ball++;
                continue;
            }
            out++;
        }
    }


}