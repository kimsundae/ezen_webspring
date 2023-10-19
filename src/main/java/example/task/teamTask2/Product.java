package example.task.teamTask2;

import lombok.*;
import org.aspectj.weaver.ast.Or;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Product {
    // 제품 번호
    private int pno;
    // 제품 이름
    private String pname;
    // 카테고리 FK
    @ToString.Exclude
    Category category;
    // 재고 FK
    Order order;
}
