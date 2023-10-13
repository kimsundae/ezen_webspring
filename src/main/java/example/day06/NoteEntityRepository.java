package example.day06;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 스프링 컨테이너에 등록 [ 왜? ]
public interface NoteEntityRepository extends JpaRepository< NoteEntity , Integer> {

}
