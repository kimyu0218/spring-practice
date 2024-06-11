package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {
  MemberService memberService;
  MemoryMemberRepository memberRepository;

  @BeforeEach
  public void beforeEach() {
    memberRepository = new MemoryMemberRepository();
    memberService = new MemberService(memberRepository);
  }

  @AfterEach
  void afterEach() {
    memberRepository.clearStore();
  }

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
    List<Member> saveList = new ArrayList<>();

    Member member1 = new Member();
    member1.setName("member1");
    saveList.add(member1);

    Member member2 = new Member();
    member2.setName("member2");
    saveList.add(member2);

    memberService.join(member1);
    memberService.join(member2);

    List<Member> findList = memberService.findMembers();

    assertThat(findList).isEqualTo(saveList);
  }

  @Test
  void findOne() {
    Member member = new Member();
    member.setName("member");

    Long saveId = memberService.join(member);
    Member findMember = memberService.findOne(saveId).get();

    assertThat(findMember).isEqualTo(member);
  }
}
