package example.day05;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// * 엔티티 조작하는 (인터페이스)리모컨 만들기
@Repository // JPA(ORM매핑) MYSQL 테이블과 매핑
public interface TodoEntityRepository extends JpaRepository< TodoEntity , Integer > {


}
