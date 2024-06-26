package hello.hello_spring.repository;

import static org.assertj.core.api.Assertions.assertThat;

import hello.hello_spring.config.SpringTestConfig;
import hello.hello_spring.domain.Member;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@DataJpaTest
@SpringJUnitConfig(SpringTestConfig.class)
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create"})
@ActiveProfiles("member")
public class JpaMemberRepositoryTest {

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private TestEntityManager entityManager;

  @Test
  void save() {
    Member member = new Member();
    member.setName("member");

    entityManager.persistAndFlush(member);
    Member result = memberRepository.save(member);

    assertThat(result.getName()).isEqualTo(member.getName());
  }

  @Test
  void findById() {
    Member member = new Member();
    member.setName("member");

    Long saveMemberId = entityManager.persistAndGetId(member, Long.class);
    Member findMember = memberRepository.findById(saveMemberId).get();

    assertThat(findMember.getId()).isEqualTo(saveMemberId);
  }

  @Test
  void findByName() {
    Member member = new Member();
    member.setName("member");

    Member saveMember = entityManager.persistAndFlush(member);
    Member findMember = memberRepository.findByName(saveMember.getName()).get();

    assertThat(findMember).isEqualTo(saveMember);
  }

  @Test
  void findAll() {
    Member member1 = new Member();
    member1.setName("member1");

    Member member2 = new Member();
    member2.setName("member2");

    entityManager.persistAndFlush(member1);
    entityManager.persistAndFlush(member2);

    List<Member> result = memberRepository.findAll();

    assertThat(result.size()).isEqualTo(2);
  }
}
