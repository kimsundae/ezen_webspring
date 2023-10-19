package example.task.teamTask2;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Order {
    // 주문 번호
    // 주문 가격
    private int ono;
    private int oprice;
    // 제품 FK
    @ToString.Exclude
    Product product;
}
