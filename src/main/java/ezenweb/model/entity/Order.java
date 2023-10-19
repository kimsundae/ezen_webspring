package ezenweb.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "porder" )
public class Order {
    // 주문 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ono;
    // 주문 가격
    private int oprice;
    // 제품 FK
    @ToString.Exclude
    @JoinColumn( name="pno")
    @OneToOne
    Product product;
}
