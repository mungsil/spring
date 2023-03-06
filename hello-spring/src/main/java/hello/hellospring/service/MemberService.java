package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

//@Service//->스프링이 이걸보고 스프링 컨테이너에 MemberService를 등록해줌
@Transactional
public class MemberService {
    private MemberRepository memberRepository;

    @Autowired//->스프링 컨테이너에 있는 MemberRepository를 서비스에 주입해줌.DI
    public MemberService(MemberRepository repository) {
        this.memberRepository = repository;
    }

    public long join(Member member){
        //회원명이 똑같은 경우 가입 불가
        /*
        Optional<Member> result = repository.findByName(member.getName());
        //optional이기에 가능한 메서드: ifPresent
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });
        */

            validateDuplicateMember(member);//중복회원검증
            memberRepository.save(member);
            return member.getId();


    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long id){
        return memberRepository.findById(id);
    }
}
