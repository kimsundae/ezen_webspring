package example.day05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    TodoEntityRepository todoEntityRepository;

    public boolean doPost( TodoDto todoDto ){

        // 1. DTO를 Entity 벼놘
        TodoEntity todoEntity = TodoEntity.builder().
                tcontent( todoDto.getTcontent() )
                .tstate(todoDto.isTstate())
                .build();
        // 2. JPARepository 를 이용한 엔티티 저장
        todoEntityRepository.save( todoEntity );

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
