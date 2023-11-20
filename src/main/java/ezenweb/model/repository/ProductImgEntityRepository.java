package ezenweb.model.repository;

import ezenweb.model.entity.ProductEntity;
import ezenweb.model.entity.ProductImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImgEntityRepository extends JpaRepository<ProductImgEntity,String> {
}
