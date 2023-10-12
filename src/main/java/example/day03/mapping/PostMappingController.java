package example.day03.mapping;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/day03/post")
public class PostMappingController {
    /*
        PathVeriable 가능하지만 생략
        ModelAttribute 요청 가능하지만 생략
    *  HttpServletRequest 요청 가능하지만 생략
    * */
    @PostMapping("/method1")
   // public String method1(@RequestParam String param1 ){ // form
    public String method1( @RequestBody String param1 ){ // json
        System.out.println("PostMappingController.method1");
        System.out.println("param1 = " + param1);
        return "정상응답";
    }
    // 2.
    @PostMapping("/method2")
//    public String method2(@RequestParam Map<String, Integer> map ){ // form
    public Map<String,String> method2( @RequestBody Map<String, String> map ){
        System.out.println("PostMappingController.method2");   System.out.println("map = " + map);
        return map;
    }
    // 3.
    @PostMapping("/method3")
    public String method3( @RequestBody ParamDto paramdto ){
        System.out.println("PostMappingController.method3");
        System.out.println("paramdto = " + paramdto);
        return "정상응답";
    }
}
