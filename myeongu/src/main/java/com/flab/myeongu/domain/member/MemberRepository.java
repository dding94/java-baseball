package com.flab.myeongu.domain.member;

import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    private static long sequence = 0L;

    public Member save(Member member) {
        member.setRoomId(++sequence);
        member.setRemainingCount(0);

        return member;
    }
}
