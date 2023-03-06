package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
//@Repository
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence=0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //null값이 반환될 수 있으므로 Optional로 감싸서 return
    }

    @Override
    public Optional<Member> findByName(String name) {

        return store.values().stream() // check
                .filter(member -> member.getName().equals(name)) //람다식: (매개변수 -> 실행문)
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()) ; // check
    }
    public void cleanStore(){
        store.clear();
    }

}
