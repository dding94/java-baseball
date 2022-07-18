package com.flab.myeongu.domain.game;

import com.flab.myeongu.domain.score.Score;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    public Game startGame() {
        return gameRepository.save();
    }

    public Game playingGame(Game game, String userAnswer) {
        if (game.isSuccess() == false) {
            //종료시
        }

        if (gameRepository.isPlayCheck(game)) {
            Score score = answerCheck(game, userAnswer);
            gameRepository.saveHistory(game, score, userAnswer);
        }

        return game;
    }

    private Score answerCheck(Game game, String userAnswer) {
        ArrayList<Integer> answer = game.getAnswer();
        Score score = new Score();

        int strike = score.getStrike();
        int ball = score.getBall();
        int out = score.getOut();

        for (int i = 0; i < userAnswer.length(); i++) {
            if (answer.get(i) == Character.getNumericValue(userAnswer.charAt(i))) {
                strike++;
                continue;
            }
            if (answer.contains(Character.getNumericValue(userAnswer.charAt(i)))) {
                ball++;
                continue;
            }
            out++;
        }

        score.setStrike(strike);
        score.setBall(ball);
        score.setOut(out);

        if (score.getStrike() == 3) {
            score.setCorrect(true);
        }

        return score;
    }
}
