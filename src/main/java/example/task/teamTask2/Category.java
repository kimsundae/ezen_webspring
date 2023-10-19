package example.task.teamTask2;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Category {
    // 카테고리 번호
    private int cno;
    // 카테고리 이름
    private String cname;
    // 제품리스트
    @Builder.Default
    private List<Product> productList = new ArrayList<>();
}
