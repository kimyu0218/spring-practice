package hello.hello_spring.config;

import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.service.MemberService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class MemberConfig {
//  private final DataSource dataSource;
//
//  @Autowired
//  public SpringConfig(DataSource dataSource) {
//    this.dataSource = dataSource;
//  }

//  private final EntityManager em;
//
//  @Autowired
//  public SpringConfig(EntityManager em) {
//    this.em = em;
//  }

  private final MemberRepository memberRepository;

  @Autowired
  public MemberConfig(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @PostConstruct
  void postConstruct() {
    System.out.println("POST CONSTRUCT : " + this.getClass().getName());
  }

  @Bean
  public MemberService memberService() {
//    return new MemberService(memberRepository());
    return new MemberService(memberRepository);
  }

//  @Bean
//  public MemberRepository memberRepository() {
//    return new MemoryMemberRepository();
//    return new JdbcMemberRepository(dataSource);
//    return new JdbcTemplateMemberRepository(dataSource);
//    return new JpaMemberRepository(em);
//  }
}
