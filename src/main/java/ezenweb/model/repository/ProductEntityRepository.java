package ezenweb.model.repository;

import ezenweb.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, String> {
}
