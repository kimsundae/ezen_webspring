package example.day01.webMvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 1. 스프링부트의 웹
@SpringBootApplication // springboot 의존성 [ MVC , RESTful , 내장톰캣 등등 지원 ]
public class WebStart {
    public static void main(String[] args) {
        SpringApplication.run( WebStart.class ); // spring 시작.
    }
}
