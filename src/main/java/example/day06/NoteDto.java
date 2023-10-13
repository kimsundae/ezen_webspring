package example.day06;

import lombok.*;

import java.time.LocalDateTime;

// Dto 사용처 :
    // AJAX(JSON/TEXT/FORM) --DTO--> Controller
        // JSON --> Dto -->
@NoArgsConstructor@AllArgsConstructor
@Setter@Getter@ToString@Builder
public class NoteDto {

    private int no;
    private String title;
    private String writer;
    private String password;
    private LocalDateTime date;

    // + DB 없는지만 추가적으로 필요한 필드

    // * dto를 엔티티로 변환해주는 함수[service에서 사용]
    public NoteEntity toEntity(){
        return NoteEntity.builder()
                .no(this.no)
                .title(this.title)
                .writer(this.writer)
                .password(this.password)
                .date(this.date)
                .build();
    }
}
