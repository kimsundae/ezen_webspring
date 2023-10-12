package example.day05;

import lombok.*;

@NoArgsConstructor      // 빈생성자 자동 생성
@AllArgsConstructor     // 풀 생성자 자동 생성
@Getter                  // 필드의 getter메소드 자동 생성
@Setter                 // 필드의 setter메소드 자동 생성
@ToString               // 필드의 toString메소드 자동 생성
@Builder                // 빌더 패턴[ 유연하게 객체 생성 도와주는 함수 제공 ]
public class TodoDto {
    private int tno;
    private String tcontent;
    private boolean tstate;
}
