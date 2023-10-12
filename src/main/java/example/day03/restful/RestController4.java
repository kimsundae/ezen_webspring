package example.day03.restful;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController // 해당 클래스를 스프링 MVC 중 컨트롤러 객체로 사용 // 스프링 컨트롤러 객체를 빈에 등록
public class RestController4 {
    // 1. GET
    @GetMapping("/day03/blue")
    public String getOrange(HttpServletRequest request) throws IOException {
        // 1.요청
        String param1 = request.getParameter( "param1");
        System.out.println("param1 = " + param1); // soutv
        // 2. 응답
        return "정상응답";

    }
    // 2. POST
    @PostMapping("/day03/blue")
    public String postOrange(HttpServletRequest request) throws IOException {
        // 1.요청
        String param1 = request.getParameter( "param1");
        System.out.println("param1 = " + param1); // soutv
        // 2. 응답
        return "정상응답";
    }

    // 3. PUT
    @PutMapping("/day03/blue")
    public String putOrange(HttpServletRequest request) throws IOException {
        // 1.요청
        String param1 = request.getParameter( "param1");
        System.out.println("param1 = " + param1); // soutv
        // 2. 응답
        return "정상응답";
    }

    // 4. DELETE
    @DeleteMapping("/day03/blue")
    public String deleteOrange(HttpServletRequest request) throws IOException {
        // 1.요청
        String param1 = request.getParameter( "param1");
        System.out.println("param1 = " + param1); // soutv
        // 2. 응답
        return "정상응답";
    }
}
