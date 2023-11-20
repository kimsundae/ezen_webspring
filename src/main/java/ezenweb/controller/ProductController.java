package ezenweb.controller;

import ezenweb.model.dto.ProductCategoryDto;
import ezenweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    // 1. 카테고리 등록
    @PostMapping("/category")
    public boolean addCategory(@RequestBody ProductCategoryDto productCategoryDto){
        return productService.addCategory(productCategoryDto);
    }
    // 2. 출력
    @GetMapping("/category")
    public List<ProductCategoryDto> printcategory( ){
        return productService.printCategory();
    }
    // 3. 수정
    @PutMapping("/category")
    public boolean updateCategory(@RequestBody ProductCategoryDto productCategoryDto){
        return productService.updateCategory(productCategoryDto);
    }
    // 4. 삭제
    @DeleteMapping("/category")
    public boolean deleteCategory(@RequestParam int pcno ){
        return productService.deleteCategory(pcno);
    }
}
