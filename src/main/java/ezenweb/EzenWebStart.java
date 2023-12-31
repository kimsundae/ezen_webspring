package ezenweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA Auditing
@SpringBootApplication // 스프링 부트 실행해주는 기능 주입 [ 1. @ComponentScan ]
public class EzenWebStart {
    public static void main(String[] args) {
        SpringApplication.run( EzenWebStart.class );
    }
}
