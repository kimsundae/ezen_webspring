package ezenweb.model.dto;

import ezenweb.model.entity.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor@NoArgsConstructor
@Setter@Getter@ToString@Builder
public class BoardDto {
    private int bno;
    private String btitle;
    private String bcontent;
    private int bview;
    private String bfile;
    private int mno;
    // +
    private LocalDateTime cdate;
    private LocalDateTime udate;

    // dto -> entity
    // 1. entity 저장할 때
    public BoardEntity saveToEntity(){
        return BoardEntity.builder()
                .btitle(this.btitle)
                .bcontent(this.bcontent)
                .bfile(this.bfile)
                .mno(this.mno)
                .build();
    }

}
