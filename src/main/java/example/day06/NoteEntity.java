package example.day06;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
/*
*   DDL : 테이블 생성 , 테이블 수정, 테이블 삭제
*       JSP프로젝트 : MYSQL워크벤치 작성
*       SPRING
* */

@Entity // 해당 클래스가 엔티티임을 주입 [ 실제 테이블 매핑/ㅇ녀결 ]
public class NoteEntity {
    @Id // no필드를 pk필드 선정 // @GenerateValue( strategy = ) pk 사용하는 방법 정의 auto_increment로 사용
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int no; // 게시물 번호
    private String title; // 게시물내용
    private String writer; // 작성자
    private String password; // 패스워드
    private LocalDateTime date; // 작성일
}
