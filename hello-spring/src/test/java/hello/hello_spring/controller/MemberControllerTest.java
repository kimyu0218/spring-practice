package hello.hello_spring.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MemberController.class)
public class MemberControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MemberService memberService;

  @Test
  void createForm() throws Exception {
    mockMvc.perform(get("/members/new"))
        .andExpect(status().isOk())
        .andExpect(view().name("members/createMemberForm"));
  }

  @Test
  void create() throws Exception {
    Member member = new Member();
    member.setName("member");

    when(memberService.join(member)).thenReturn(0L);

    mockMvc.perform(post("/members/new"))
        .andExpect(status().is3xxRedirection());
  }

  @Test
  void list() throws Exception {
    Member member1 = new Member();
    member1.setName("member1");

    Member member2 = new Member();
    member2.setName("member2");

    List<Member> members = Arrays.asList(member1, member2);

    when(memberService.findMembers()).thenReturn(members);

    mockMvc.perform(get("/members"))
        .andExpect(status().isOk())
        .andExpect(view().name("members/memberList"))
        .andExpect(model().attribute("members", members));
  }
}
