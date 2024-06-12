package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaMemberRepository
    extends JpaRepository<Member, Long>, MemberRepository {

  Optional<Member> findByName(String name);
}