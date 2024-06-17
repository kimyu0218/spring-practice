package hello.hello_spring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hello.hello_spring.SpringTestConfig;
import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = SpringTestConfig.class, properties = "spring.config.location=classpath:application-test.properties")
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
