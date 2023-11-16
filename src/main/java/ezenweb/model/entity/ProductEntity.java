package ezenweb.model.entity;

import lombok.Builder;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity@Table(name = "product")
public class ProductEntity { /* 제품테이블 */
    @Id private String pno;         // 제품번호 [ PK ]
    @Column private String pname;       // 제품명
    @Column( columnDefinition = "TEXT") private String pcomment;    // 제품 설명
    @Column private int pprice;         // 제품 가격
    @Column@ColumnDefault("0") private byte pstate;        // 제품 상태 [ 0: 판매중, 1: 판매중지, 2:재고없음 3:판매 중지]
    @Column@ColumnDefault("0") private int pstock;         // 제품 재고

    @ManyToOne
    @JoinColumn( name="pcno" )
    private ProductCategoryEntity productEntity;

    @OneToMany(fetch = FetchType.LAZY , mappedBy = "productEntity" , cascade = CascadeType.ALL )
    @ToString.Exclude
    @Builder.Default
    private List<ProductImgEntity> productImgEntities = new ArrayList<>();
}
