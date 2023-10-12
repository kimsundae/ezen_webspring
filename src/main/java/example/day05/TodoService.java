package example.day05;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    public boolean doPost( TodoDto todoDto ){
        return false;
    }

    public List<TodoDto> doGet(){
        return null;
    }
    public boolean doDelete( int tno ){
        return false;
    }
    public boolean doPut( TodoDto todoDto ){
        return false;
    }
}
