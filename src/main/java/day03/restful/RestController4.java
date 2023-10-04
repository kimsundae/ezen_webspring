package day03.restful;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController // 해당 클래스를 스프링 MVC 중 컨트롤러 객체로 사용 // 스프링 컨트롤러 객체를 빈에 등록
public class RestController3 {
    // 1. GET
    @RequestMapping( value = "/day03/red" , method = RequestMethod.GET )
    public String getOrange(HttpServletRequest request) throws IOException {
        // 1.요청
        String param1 = request.getParameter( "param1");
        System.out.println("param1 = " + param1); // soutv
        // 2. 응답
        return "정상응답";

    }
    // 2. POST
    @RequestMapping( value = "/day03/red" , method = RequestMethod.POST )
    public String postOrange(HttpServletRequest request) throws IOException {
        // 1.요청
        String param1 = request.getParameter( "param1");
        System.out.println("param1 = " + param1); // soutv
        // 2. 응답
        return "정상응답";
    }

    // 3. PUT
    @RequestMapping( value = "/day03/red" , method = RequestMethod.PUT )
    public String putOrange(HttpServletRequest request) throws IOException {
        // 1.요청
        String param1 = request.getParameter( "param1");
        System.out.println("param1 = " + param1); // soutv
        // 2. 응답
        return "정상응답";
    }

    // 4. DELETE
    @RequestMapping( value = "/day03/red" , method = RequestMethod.DELETE )
    public String deleteOrange(HttpServletRequest request) throws IOException {
        // 1.요청
        String param1 = request.getParameter( "param1");
        System.out.println("param1 = " + param1); // soutv
        // 2. 응답
        return "정상응답";
    }
}
