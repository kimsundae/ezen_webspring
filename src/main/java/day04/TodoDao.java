package day04;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
public class TodoDao {


    public boolean doPost(TodoDto todoDto){
        System.out.println("TodoDao.doPost");
        return false;
    }

    public List<TodoDto> doGet(){
        System.out.println("TodoDao.doGet");
        return null;
    }

    // 3. [U]
    @PutMapping("")
    public boolean doPut(TodoDto todoDto){
        System.out.println("TodoDao.doPut");
        return false;
    }

    // 4. [D]
    @DeleteMapping("")
    public boolean doDelete(int tno ){
        System.out.println("TodoDao.doDelete");
        return false;
    }
}