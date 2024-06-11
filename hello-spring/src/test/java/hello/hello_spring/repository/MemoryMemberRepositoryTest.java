package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {
  MemoryMemberRepository repository = new MemoryMemberRepository();

  @AfterEach
  void afterEach() {
    repository.clearStore();
  }

  @Test
  void save() {
    Member member = new Member();
    member.setName("member");

    repository.save(member);
    Member result = repository.findById(member.getId()).get();

    assertThat(member).isEqualTo(result);
  }

  @Test
  void findById() {
    Member member = new Member();
    member.setName("member");

    Member saveMember = repository.save(member);
    Member findMember = repository.findById(saveMember.getId()).get();

    assertThat(findMember).isEqualTo(saveMember);
  }

  @Test
  void findByName() {
    Member member1 = new Member();
    member1.setName("member1");

    Member member2 = new Member();
    member2.setName("member2");

    repository.save(member1);
    repository.save(member2);

    Member result = repository.findByName("member1").get();

    assertThat(result).isEqualTo(member1);
    assertThat(result).isNotEqualTo(member2);
  }

  @Test
  void findAll() {
    Member member1 = new Member();
    member1.setName("member1");

    Member member2 = new Member();
    member2.setName("member2");

    repository.save(member1);
    repository.save(member2);

    List<Member> result = repository.findAll();

    assertThat(result.size()).isEqualTo(2);
  }
}
