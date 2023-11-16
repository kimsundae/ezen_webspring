package ezenweb.model.entity;

import lombok.Builder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity@Table( name = "productimg" )
public class ProductImgEntity { /* 제품이미지파일 */
    @Id private String uuidFileName; // 이미지 식별 이름 [ PK ]
    @Column private String realFileName; // 이미지 실제 이름

    @ManyToOne
    @JoinColumn(name="pno")
    private ProductEntity productCategoryEntities;

}
