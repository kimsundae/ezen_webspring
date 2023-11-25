package ezenweb.service;

import ezenweb.model.dto.ProductCategoryDto;
import ezenweb.model.entity.ProductCategoryEntity;
import ezenweb.model.repository.ProductCategoryEntityRepository;
import ezenweb.model.repository.ProductEntityRepository;
import ezenweb.model.repository.ProductImgEntityRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    // 0.
    @Autowired
    private ProductCategoryEntityRepository productCategoryEntityRepository;
    @Autowired
    private ProductEntityRepository productEntityRepository;
    @Autowired
    private ProductImgEntityRepository productImgEntityRepository;

    @Autowired
    private FileService fileService;

    // 1. 카테고리 등록
    @Transactional
    public boolean addCategory(ProductCategoryDto productCategoryDto){
        return productCategoryEntityRepository.save(
                ProductCategoryEntity.builder().pcname(productCategoryDto.getPcname()).build()
        ).getPcno() >= 1;
    }
    // 2. 출력
    @Transactional
    public List<ProductCategoryDto> printCategory(){
        return productCategoryEntityRepository.findAll().stream().map(
                e ->  ProductCategoryDto.builder().pcno(e.getPcno()).pcname( e.getPcname()).build()
        ).collect(Collectors.toList());
    }
    // 3. 수정
    @Transactional
    public boolean updateCategory( ProductCategoryDto productCategoryDto){
        ProductCategoryEntity productCategoryEntity = toEntity(productCategoryDto.getPcno());
        if (productCategoryEntity != null) {
            productCategoryEntity.setPcname(productCategoryDto.getPcname());
            return true;
        }
        return false;
    }
    @Transactional
    // 4. 삭제
    public boolean deleteCategory( int pcno ){
        ProductCategoryEntity productCategoryEntity = toEntity(pcno);
        if( productCategoryEntity != null) {
            productCategoryEntityRepository.delete(productCategoryEntity);
            return true;
        }
        return false;
    }
    public ProductCategoryEntity toEntity(int pcno){
        Optional<ProductCategoryEntity> productCategoryEntityOptional =
                productCategoryEntityRepository.findById(pcno);
        return productCategoryEntityOptional.isPresent() ? productCategoryEntityOptional.get() : null;
    }

}
