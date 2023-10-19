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
@Table(name = "pproduct" )
public class Product {
    // 제품 번호
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int pno;
    // 제품 이름
    private String pname;
    // 카테고리 FK
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "cno_fk")
    Category category;
    // 재고 FK
    @OneToOne(mappedBy = "product")
    Order order;
}
