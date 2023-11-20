package ezenweb.model.repository;

import ezenweb.model.entity.ProductCategoryEntity;
import ezenweb.model.entity.ProductImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryEntityRepository extends JpaRepository<ProductCategoryEntity, String> {
}
