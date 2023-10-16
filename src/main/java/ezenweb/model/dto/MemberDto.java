package ezenweb.model.dto;

import ezenweb.model.entity.MemberEntity;
import lombok.*;

@AllArgsConstructor@NoArgsConstructor
@Getter@Setter@ToString@Builder
public class MemberDto {

    private int mno;            // 1.회원번호
    private String memail;      // 2.이메일[회원아이디]
    private String mpassword;   // 3.비밀번호
    private String mname;       // 4.이름
    private String mphone;      // 5.연락처
    private String mrole;       // 6.회원등급( 일반회원 = user, 관리자 회원 = admin)

    // dto --> entity 변환 함수
    public MemberEntity toEntity(){
        return new MemberEntity(
                this.mno
                ,this.memail
                ,this.mpassword
                ,this.mname
                ,this.mphone
                ,this.mrole);
    }
}
