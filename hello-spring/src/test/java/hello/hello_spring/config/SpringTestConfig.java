package hello.hello_spring.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@TestPropertySource(locations = "classpath:application-test.properties")
@ComponentScan(
    basePackages = "hello.hello_spring",
    excludeFilters = @ComponentScan.Filter(
        type = FilterType.ASPECTJ,
        pattern = {"hello.hello_spring.config.*", "hello.hello_spring.*Application"}))
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "hello.hello_spring.repository")
@EntityScan(basePackages = "hello.hello_spring.domain")
@Import(MemberConfig.class)
public class SpringTestConfig {

}
