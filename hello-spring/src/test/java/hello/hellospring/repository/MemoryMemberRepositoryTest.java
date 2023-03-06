package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.AbstractBigDecimalAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.cleanStore();
    }

    @Test
    public void save(){
        Member m=new Member();
        m.setName("sprint");
        repository.save(m);
        Member result = repository.findById(m.getId()).get();// optional을 꺼낼 때 방법 1. get()
        assertThat(m).isEqualTo(result);
    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        Member member2 = new Member();

        member1.setName("spring");
        member2.setName("hehe");

        repository.save(member1);
        repository.save(member2);

        Member result=repository.findByName("spring").get();
        assertThat(result).isEqualTo(member1);

    }
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("hehe");
        repository.save(member2);

        List<Member> result=repository.findAll();
        assertThat(result.size()).isEqualTo(2);

    }
}
