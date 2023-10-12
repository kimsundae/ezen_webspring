package example.day04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
// MVC 3티어 계층에서 사용되는 Service계층[ 비즈니스 로직 : 기능 처리 공간 ]
@Service // Spring MVC 중 해당 클래스를 Service로 사용
public class TodoService {

    @Autowired private TodoDao todoDao;

    // 1. [C]
    public boolean doPost( TodoDto todoDto){
        System.out.println("TodoService.doPost");
        return todoDao.doPost(todoDto);
    }
    // 2. [R]
    public List<TodoDto> doGet(){
        System.out.println("TodoService.doGet");
        return todoDao.doGet();
    }
    // 3. [U]
    public boolean doPut( TodoDto todoDto){
        System.out.println("TodoService.doPut");
        return todoDao.doPut(todoDto);
    }
    // 4. [D]
    public boolean doDelete( int tno ){
        System.out.println("TodoService.doDelete");
        return todoDao.doDelete(tno);
    }
}
