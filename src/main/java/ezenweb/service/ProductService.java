package ezenweb.service;

import ezenweb.model.dto.ProductCategoryDto;
import ezenweb.model.dto.ProductDto;
import ezenweb.model.dto.ProductImgDto;
import ezenweb.model.entity.ProductCategoryEntity;
import ezenweb.model.entity.ProductEntity;
import ezenweb.model.entity.ProductImgEntity;
import ezenweb.model.repository.ProductCategoryEntityRepository;
import ezenweb.model.repository.ProductEntityRepository;
import ezenweb.model.repository.ProductImgEntityRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    // ========== 제품등록 ============= //
    @Transactional
    public boolean onProductAdd( ProductDto productDto ){
        // 1. 카테고리 엔티티 준비
        ProductCategoryEntity productCategoryEntity = productCategoryEntityRepository.findById( productDto.getPcno() ).get();
        // 2. 제품 엔티티를 생성
            // fk 방향: 제품 엔티티에 카테고리 엔티티 넣어주기
            // pk 방향: 제품 엔티티에 이미지 엔티티 리스트 넣어주기
            // 2-1 제품 엔티티 생성
        ProductEntity productEntity = ProductEntity.builder()
                .pno( productCategoryEntity.getPcno() + "-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))) // auto_increment가 아니므로 pk 만들어주기 1.UUID 2. 날짜조합 3. 중복없는 데이터 등등
                    // 카테고리번호 - 등록날짜
                .pname( productDto.getPname() )
                .pcomment( productDto.getComment() )
                .pprice( productDto.getPprice())
                .pstock( productDto.getPstock())
                .productCategoryEntity( productCategoryEntity )
                .productImgEntities( new ArrayList<>())
                .build();
        // 2-2 제품 이미지 등록 [ 첨부파일 여러개 ]
        productDto.getFileList().stream().map( file-> fileService.fileUpload( file ) ).collect(Collectors.toList())
                .forEach( uuidFile ->
                        productEntity.getProductImgEntities().add(
                                ProductImgEntity.builder()
                                        .uuidFileName( uuidFile )
                                        .realFileName( uuidFile.split("_")[1] )
                                        .productEntity(productEntity)
                                        .build()
                        )
                ) ;
        // 3. 제품등록
        productEntityRepository.save( productEntity );
        return true;
    }
    // 2.제품 출력
    @Transactional
    public List<ProductDto> onProductAll(){

        // 1.모든 제품 엔티티 호출
        List<ProductEntity> productEntityList = productEntityRepository.findAll(Sort.by(Sort.Direction.DESC, "cdate"));
        // 2. 모든 제품의 entity -> dto로 변환해서 반환하기

        return productEntityList.stream().map( (p) ->
            ProductDto.builder()
                    .pno( p.getPno())
                    .pname(  p.getPname())
                    .pstock(p.getPstock())
                    .pstate(p.getPstate())
                    .pprice(p.getPprice())
                    .comment(p.getPcomment())
                    .categoryDto(
                            ProductCategoryDto.builder()
                                    .pcno( p.getProductCategoryEntity().getPcno() )
                                    .pcname(p.getProductCategoryEntity().getPcname())
                                    .build()
                    )
                    .imgDtoList(
                            p.getProductImgEntities().stream().map( (img) ->
                                    ProductImgDto.builder()
                                            .realFileName(img.getRealFileName())
                                            .uuidFileName(img.getUuidFileName())
                                            .build()
                                    ).collect(Collectors.toList()))
                    .build()
                    ).collect(Collectors.toList());

    }
    @Transactional
    public boolean onProductUpdate( ProductDto productDto){
        return false;
    }
    @Transactional
    public boolean onProductDelete(  String pno ){
        return false;
    }

}
