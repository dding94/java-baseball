package com.flab.myeongu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
        String userAnswer = "365";
        String answer = "222";

        int strike = 0;
        int ball = 0;
        int out = 0;

        for (int i = 0; i < userAnswer.length(); i++) {
            if (answer.charAt(i) == userAnswer.charAt(i)) {
                strike++;
                continue;
            }
            if (answer.contains(String.valueOf(userAnswer.charAt(i)))) {
                ball++;
                continue;
            }
            out++;
        }

        System.out.println(strike);
        System.out.println(ball);
        System.out.println(out);
    }

    @Test
    void HashMapTest() {
        Map<String, ArrayList<Integer>> hm = new HashMap<>();


    }


}