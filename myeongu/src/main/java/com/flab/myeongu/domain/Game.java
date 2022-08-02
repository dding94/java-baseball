package com.flab.myeongu.domain;

import com.flab.myeongu.util.GameSupport;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;

@Getter
@Builder
public class Game {

    private long roomId;
    private boolean correct;
    private int remainingCount;
    private int answerCount;
    private String gameAnswer;
    private ArrayList<History> histories = new ArrayList<>();

    public static Game makeGame() {
        return Game.builder()
                .remainingCount(10)
                .answerCount(0)
                .gameAnswer(GameSupport.createRandomNum())
                .correct(false)
                .histories(new ArrayList<>())
                .build();
    }

    public boolean play() {
        this.remainingCount -= 1;
        this.answerCount += 1;

        if (!isPlayCheck()) {
            return false;
        }

        return true;
    }

    public Score answerCheck(String userAnswer) {

        int strike = 0;
        int ball = 0;
        int out = 0;

        if (gameAnswer.equals(userAnswer)) {
            this.correct = true;

            return Score.builder()
                    .strike(3)
                    .ball(0)
                    .out(0)
                    .build();
        }

        for (int i = 0; i < userAnswer.length(); i++) {
            if (gameAnswer.charAt(i) == userAnswer.charAt(i)) {
                strike++;
                continue;
            }
            if (gameAnswer.contains(String.valueOf(userAnswer.charAt(i)))) {
                ball++;
                continue;
            }
            out++;
        }

        return Score.builder()
                .strike(strike)
                .out(out)
                .ball(ball)
                .build();
    }

    public History nowPlayGameInfo() {
        return histories.get(this.answerCount - 1);
    }

    public void saveHistory(Score score, String userAnswer) {
        History history = History.builder()
                .result(score)
                .answer(userAnswer)
                .build();

        this.histories.add(history);
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    private boolean isPlayCheck() {
        if (this.remainingCount < 0 || this.answerCount > 10) {
            return false;
        }

        return true;
    }
}
