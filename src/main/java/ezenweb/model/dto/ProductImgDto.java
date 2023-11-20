package ezenweb.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ProductImgDto {
    private String uuidFileName;    // 이미지 식별
    private String realFileName;    // 이미지 실제
}
