package ezenweb.model.entity;

import lombok.Builder;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity@Table( name = "productcategory")
public class ProductCategoryEntity { /* 제품테이블 */
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY) private int pcno;       // 카테고리번호[PK]
    @Column private String pcname; // 카테고리명

    @OneToMany( fetch = FetchType.LAZY, mappedBy = "productEntity", cascade = CascadeType.ALL )
    @Builder.Default @ToString.Exclude
    private List<ProductEntity> productCategoryEntities = new ArrayList<>();
}
