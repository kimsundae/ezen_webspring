package ezenweb.controller;

import ezenweb.model.dto.ProductCategoryDto;
import ezenweb.model.dto.ProductDto;
import ezenweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    // 1. 카테고리 등록
    @PostMapping("/category")
    public boolean addCategory(@RequestBody ProductCategoryDto productCategoryDto){
        System.out.println("productCategoryDto = " + productCategoryDto);
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

    // ========== 제품등록 ============= //
    @PostMapping("")
    public boolean onProductAdd( ProductDto productDto ){
        System.out.println("productDto = " + productDto);
        return productService.onProductAdd(productDto);
    }
    @GetMapping("")
    public List<ProductDto> onProductAll(){
        return productService.onProductAll();
    }

    @PutMapping("")
    public boolean onProductUpdate( @RequestBody ProductDto productDto){
        return productService.onProductUpdate(productDto);
    }
    @DeleteMapping("")
    public boolean onProductDelete( @RequestParam String pno ){
        return productService.onProductDelete(pno);
    }

    // ===== 차트 데이터 =========//

    @GetMapping("/barchart")
    public List<Map<Object,Object>> getBarChart(){
        return productService.getBarChart();
    }
    @GetMapping("/piechart")
    public List<Map<Object,Object>> getPieChart(){
        return productService.getPieChart();
    }
}
