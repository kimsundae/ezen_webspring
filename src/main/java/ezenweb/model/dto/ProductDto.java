package ezenweb.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ProductDto {
    private String pno;     // 제품번호
    private String pname;   // 제품명
    private String comment; // 제품설명
    private int pprice;     // 제품가격
    private byte pstate;    // 제품상태
    private int pstock;     // 제품 재고
}
