package com.flab.myeongu.util;

import java.util.ArrayList;

public final class GameSupport {

    public static String createRandomNum() {
        ArrayList<Integer> answer = new ArrayList<>();

        while (answer.size() < 3) {
            int random = (int) (Math.random() * 9) + 1;

            if (!answer.contains(random)) {
                answer.add(random);
            }
        }

        String num = "";
        for (Integer i : answer) {
            num += i;
        }

        return num;
    }
}
