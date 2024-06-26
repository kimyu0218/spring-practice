package hello.hello_spring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hello.hello_spring.config.SpringIntegrationTestConfig;
import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@SpringBootTest(classes = SpringIntegrationTestConfig.class, properties = "spring.config.location=classpath:application-test.properties")
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = SpringIntegrationTestConfig.class)
@SpringJUnitConfig(classes = SpringIntegrationTestConfig.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@ComponentScan(
    basePackages = "hello.hello_spring",
    excludeFilters = @ComponentScan.Filter(
        type = FilterType.ASPECTJ,
        pattern = {"hello.hello_spring.config.*", "hello.hello_spring.*Application"}))
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "hello.hello_spring.repository")
@EntityScan(basePackages = "hello.hello_spring.domain")
@EnableTransactionManagement
@ActiveProfiles("integration")
@Transactional
class MemberServiceIntegrationTest {

  @Autowired
  MemberService memberService;

  @Autowired
  MemberRepository memberRepository;

  @Test
  void join_success() {
    Member member = new Member();
    member.setName("member");

    Long savedId = memberService.join(member);

    Member findMember = memberService.findOne(savedId).get();
    assertThat(member.getName()).isEqualTo(findMember.getName());
  }

  @Test
  void join_fail_duplicate() {
    String name = "member";

    Member member1 = new Member();
    member1.setName(name);

    Member member2 = new Member();
    member2.setName(name);

    memberService.join(member1);
    IllegalStateException e =
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));

    assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
  }

  @Test
  void findMembers() {
    List<String> saveList = new ArrayList<>();

    Member member1 = new Member();
    member1.setName("member1");
    saveList.add(member1.getName());

    Member member2 = new Member();
    member2.setName("member2");
    saveList.add(member2.getName());

    memberService.join(member1);
    memberService.join(member2);

    List<String> findList = memberService.findMembers().stream().map(m -> m.getName()).toList();
    assertThat(findList).isEqualTo(saveList);
  }

  @Test
  void findOne() {
    Member member = new Member();
    member.setName("member");

    Long saveId = memberService.join(member);
    Member findMember = memberService.findOne(saveId).get();

    assertThat(findMember.getName()).isEqualTo(member.getName());
  }
}