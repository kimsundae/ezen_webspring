package ezenweb.model.dto;

import ezenweb.model.entity.MemberEntity;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor@NoArgsConstructor
@Getter@Setter@ToString@Builder
public class MemberDto {

    private int mno;            // 1.회원번호
    private String memail;      // 2.이메일[회원아이디]
    private String mpassword;   // 3.비밀번호
    private String mname;       // 4.이름
    private String mphone;      // 5.연락처
    @Builder.Default
    private String mrole = "user";       // 6.회원등급( 일반회원 = user, 관리자 회원 = admin)

    private LocalDateTime cdate;
    private LocalDateTime udate;
    // dto --> entity 변환 함수
    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .memail(this.memail)
                .mpassword(this.mpassword)
                .mname(this.mname)
                .mphone(this.mphone)
                .build();
    }
}
