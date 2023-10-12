package example.day05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        // 1. 모든 엔티티 호출[ select 대체 ]
        List<TodoEntity> todoEntities = todoEntityRepository.findAll();
        // 2. List<TodoEntity> -> List<TodoDto> 변환
        List<TodoDto> list = new ArrayList<>();
        // 엔티티 리스트에서 엔티티 하나씩 꺼내기
        todoEntities.forEach((entity) -> {
            // 3. 엔티티를 dto로 변환
            TodoDto todoDto = TodoDto.builder()
                    .tno( entity.getTno())
                    .tcontent(entity.getTcontent())
                    .tstate(entity.isTstate()).build();
            // 4. 변환된 dto를 리스트에 저장
            list.add(todoDto);
        });
        return list;
    }
    public boolean doDelete( int tno ){
        // 1. 삭제할 엔티티 찾기 [ 삭제할pk번호 ]
        todoEntityRepository.deleteById( tno );

        return false;
    }
    @Transactional
    public boolean doPut( TodoDto todoDto ){
        // 1. 수정할 엔티티 찾기 []
        Optional<TodoEntity> todoEntity = todoEntityRepository.findById( todoDto.getTno() );
        // 2. Optinal 객체에 엔티티 존재여부 확인 [ 안전성 보장 ]
        if( todoEntity.isPresent() ){
            // 3. Optinal 객체에 엔티티 꺼내기
            TodoEntity updateEntity = todoEntity.get();
            // 4. 엔티티 찾았으니 필드 수정
            updateEntity.setTstate(todoDto.isTstate());
        }
        return false;
    }
}
