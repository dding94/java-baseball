package com.flab.myeongu.domain.game;

import com.flab.myeongu.domain.member.Member;
import com.flab.myeongu.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class GameService {

    private final MemberRepository memberRepository;

    public void createGame() {
        Member member = new Member();

        memberRepository.save(member);
    }


    public ArrayList<Integer> createRandomNum() {
        ArrayList<Integer> list = new ArrayList<>();

        while (list.size() < 3) {
            int random = (int) (Math.random() * 9) + 1;

            if (!list.contains(random)) {
                list.add(random);
            }
        }

        return list;
    }


}
