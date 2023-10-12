package example.day01.webMvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
@RestController // 해당 클래스는 restful로 사용하겠다는 선언
public class WebController {
    @GetMapping("/day01/doget") // HTTP로부터 GET 요청을 했을 때.
    public List<WebDto> doGet(){
        WebDao consoleDao = new WebDao();
        List<WebDto> result = consoleDao.doGet();
        return result;
    }
    @PostMapping("/day01/dopost") // HTTP로부터 POST 요청을 했을 때.
    public boolean doPost( String title ){
        // 1. 인수 받아서 Dto 생성
            // LocalDate.now() : 현재날짜 호출
        WebDto dto = new WebDto( 0 , title , LocalDate.now() , true);
        WebDao dao = new WebDao();
        boolean result = dao.doPost( dto );
        // 2.
        return result;
    }
}
