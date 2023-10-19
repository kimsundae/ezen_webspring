package example.객체연관관계;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
@Builder
// @ToString : 객체 호출 시 기본값으로 객체 참조값 호출 // @ToString 사용 시 객체 참조값 아닌 객체 참조값의 필드 호출
public class 회원 {
    private int 회원번호;
    private String 회원아이디;
    private String 회원이름;
    // 리스트 : 1명 회원(객체)는 여러개의 게시물 가질 수 있음
    @Builder.Default // 빌더패턴 사용 시 해당 필드는 기본값으로 사용.
    @ToString.Exclude // 해당 필드는 @ToString.Exclude 제외
    private List<게시물> 내가쓴글 = new ArrayList<>();
}
