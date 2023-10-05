package day04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run( AppStart.class );
    }
}
/*
*    스프링이 view 파일들을 찾는 위치 resources 폴더
*       - 주의할 점 : 본인이 만들고 싶은 곳에 정적(view) 파일 만들면 안된다.
*    HTML : resources->templates-> html 파일
*    JS/CSS/Image : resources->static-> JS/CSS/Image 파일
*    - JSP 프로젝트와 SPRING 프로젝트의 정적파일 경로 차이
*       - JSP는 패키지의 경로와 파일명이 곧 URL
*       - SPRING는 정적파일 호출하는 URL 매핑
* */