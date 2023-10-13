package example.day06;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// Dto 사용처 :
    // AJAX(JSON/TEXT/FORM) --DTO--> Controller
        // JSON --> Dto -->
@Data
public class NoteDto {

    private int no;
    private String title;
    private String writer;
    private String password;
    private LocalDateTime date;

}
