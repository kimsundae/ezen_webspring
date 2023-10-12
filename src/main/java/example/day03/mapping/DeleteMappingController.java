package example.day03.mapping;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/day03/delete")
public class DeleteMappingController {
    // 1.
    @DeleteMapping("/method1")
    public boolean method1(@RequestParam String param1 ){
        System.out.println("param1 = " + param1);
        return true; // application/json 자동으로 json 타입
    }
    @DeleteMapping("/method2")
    public boolean method2( ParamDto paramDto ){
        System.out.println("paramDto = " + paramDto);
        return false; // application/json 자동으로 json 타입
    }

}
