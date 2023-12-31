package example.day04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 스프링 빈 : 스프링 컨테이너(저장소)에 저장[]된 객체 | 이유 : 스프링이 대신 객체 관리한다. 왜?

   // Spring MVC 중에 해당 클래스를 Controller로 사용
@RestController // Controller + ResponseBody
@RequestMapping("/day04/todo")
public class TodoController {

    @Autowired // 미리 등록된 스프링 컨테이너에서 빈(bean/객체) 찾아서 객체를 주입
    private TodoService todoService; // 서비스 객체

    // REST : HTTP 기반으로 GET, POST, PUT, DELETE 메소드 이용한 웹 서비스

    // 1. [C]
    @PostMapping("")            // http://localhost:80/todo
    //@ResponseBody             // 응답 객체 자동 지원 ( +단 해당 클래스가 RestController 사용 했을 경우 생략 가능)
    public boolean doPost(@RequestBody TodoDto todoDto){    // 요청 매개변수 : 입력받은 정보들 [ Dto ]
        System.out.println("TodoController.doPost");
        System.out.println("todoDto = " + todoDto);
        return todoService.doPost(todoDto);
    }

    // 2. [R]
    @GetMapping("")
    public List<TodoDto> doGet(){     // 요청 매개변수 : 출력 필요한 정보들 [ x ]
        System.out.println("TodoController.doGet");
        return todoService.doGet();
    }

    // 3. [U]
    @PutMapping("")
    public boolean doPut(@RequestBody TodoDto todoDto){     // 요청 매개변수 : 수정 필요한 정보들 [ Dto ]
        System.out.println("TodoController.doPut");
        return todoService.doPut(todoDto);
    }

    // 4. [D]
    @DeleteMapping("")
    public boolean doDelete(@RequestParam int tno ){  // 요청 매개변수 : 삭제 필요한 정보들 [ tno ]
        // @RequestParam 쿼리스트링에서의 매개변수 요청할 때 사용 하는 어노테이션
        System.out.println("TodoController.doDelete");
        System.out.println("tno = " + tno);
        return todoService.doDelete( tno );
    }

    // 5. HTML 반환 매핑 주소 만들기
    @GetMapping("/index")
    public Resource  getIndex(){
        return new ClassPathResource("templates/todo.html");
    }
}