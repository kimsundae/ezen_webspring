package day03.mapping;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/day03/put")
public class PutMappingController {
    // 1.
    @PutMapping("/method1")
    public ParamDto method1( @RequestBody ParamDto paramDto ){
        return paramDto;
    }
    // 2.
    @PutMapping("/method2")
    public Map<String,String> method2( @RequestBody Map<String, String > map ){
        return map;
    }
}
