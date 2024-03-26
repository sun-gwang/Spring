package kr.co.onehourproject.member.service;

import kr.co.onehourproject.member.repository.MemberRepository;
import kr.co.onehourproject.member.repository.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public String join(String id, String name, String phoneNumber) {
        Member member = Member.builder()
                .id(id)
                .name(name)
                .phoneNumber(phoneNumber).build();
        return "success";
    }
}
