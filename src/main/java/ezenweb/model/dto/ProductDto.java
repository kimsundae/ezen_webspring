package ezenweb.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    // + 첨부파일이 여러개일때 [vs 첨부파일 하나일 때 = boardDto ]
    private List<MultipartFile> fileList;
        // <input type="file" name="fileList" multiple>
        private int pcno; // 카테고리 번호

    // ==== 출력용 === //
    private ProductCategoryDto categoryDto;
    private List<ProductImgDto> imgDtoList;

}
