package day02.servlet;

import lombok.*;

import java.time.LocalDate;

// 롬복 라이브러리
@Getter@Setter
@ToString                // 객체의 필드정보
@NoArgsConstructor      // 빈생성자
@AllArgsConstructor     // 풀 생성자
@Builder                // 빌더패턴
public class TodoDto {
    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;


}
