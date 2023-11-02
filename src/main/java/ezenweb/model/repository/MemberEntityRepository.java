package ezenweb.model.repository;

import ezenweb.model.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberEntityRepository extends JpaRepository< MemberEntity, Integer > {
    // 인터페이스 , 추상메소드
    // - 이메일 이용한  엔티티/레코드검색 select * from member where memail = 변수
    // 반환 자료형 추상메소드( 매개변수 )
    // 1. 동일한 이메일 있을 때 '엔티티'반환 없을 때 NULL 반환
    MemberEntity findByMemail( String memail ); // select * from member where memail = 변수
        // 2.동일한 이메일 있을 때 'Optinal' 반환 없을 때 'Optinal' 반환
    // Optional<MemberEntity> findByMemail(String memail); // select * from member where memail = 변수
        // 3. 동일한 이메일 있을 때 'TRUE'없을 때 FALSE 반환
    boolean existsByMemail(String memail);
        // 4. 조건에 and/or 있을 때 이메일과 이름이 같을 때
        // select * from member where mname = 변수 and memail = 변수 ;
        MemberEntity findByMnameAndMemail( String mname ,  String memail);
}
