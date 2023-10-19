package ezenweb.model.entity;

import ezenweb.model.dto.MemberDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity // 해당 클래스를 db테이블과 매핑 [ 엔티티클래스가 곧 테이블과 매핑 ( 엔티티 1개 객체 <--> 테이블 내 레코드 1개)]
@Table(name = "member") // db테이블명 정의 가능 [ 생략시 해당 클래스명이 곧 db테이블 명으로 자동 생성 ]
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class MemberEntity extends BaseTime{
    @Id // 해당 필드를 pk로 선정
    @GeneratedValue( strategy = GenerationType.IDENTITY ) // auto_increment
    private int mno;            // 1.회원번호
    @Column(name = "memail", length = 50, nullable = false, unique = true) // 해당 필드 선정 [ 속성 ) name="필드명"]
    // name = "필드명" , length=글자수 , nullable = false -> not null null 불가, unique = true 중복불가 ]
    private String memail;      // 2.이메일[ 회원아이디 대체 ]

    @Column( length = 30, nullable = false) // 해당 필드 선정 [ 최대 30글자, not null ]
    private String mpassword;   // 3.비밀번호
    @Column( length = 20, nullable = false ) // 해당 필드 선정 [ 최대 20글자, not null]
    private String mname;       // 4.이름
    @Column( length = 13, nullable = false, unique = true ) // 해당 필드 선정 [ 최대 3글자, not null, unique ]
    private String mphone;      // 5.연락처
    @Column // 해당 필드 선정
    @ColumnDefault( "'user'" ) // ColumDefault("초기값") ColumnDefault("'문자열일 경우'")
    private String mrole;       // 6.회원등급( 일반회원 = user, 관리자 회원 = admin)
    @Builder.Default
    @OneToMany( mappedBy = "memberEntity" ) // 하나가 다수에게 [ PK ] // 실제 DB에는 엔티티의 ID만 저장
    // 게시물목록 = 내가 쓴 게시물
    private List<BoardEntity> boardEntityList = new ArrayList<>();

    // entity --> dto 변환 함수
    public MemberDto toDto(){
        return MemberDto.builder()
                .mno(this.mno)
                .memail(this.memail)
                .mpassword(this.mpassword)
                .mname(this.mname)
                .mphone(this.mphone)
                .mrole(this.mrole)
                .cdate(this.getCdate())
                .udate(this.getUdate())
                .build();
    }
}
