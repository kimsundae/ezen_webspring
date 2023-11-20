package ezenweb.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity@Table( name = "productcategory")
public class ProductCategoryEntity extends BaseTime{ /* 제품테이블 */
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY) private int pcno;       // 카테고리번호[PK]
    @Column private String pcname; // 카테고리명

    @OneToMany( fetch = FetchType.LAZY, mappedBy = "productCategoryEntity", cascade = CascadeType.ALL )
    @Builder.Default @ToString.Exclude
    private List<ProductEntity> productCategoryEntities = new ArrayList<>();
}
