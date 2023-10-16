package ezenweb.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
@Getter@Setter
@MappedSuperclass // 엔티티X [ 여러 엔티티가 공통으로 사용하는 필드에 대해 구성할 때]
@EntityListeners(AuditingEntityListener.class) // JPA Auditing 이벤트 발생 [ @CreateDate(insert) , @LastModifieDate ]
public class BaseTime {
    @CreatedDate // 엔티티가 생성될 때 시간이 자동 저장/주입
    private LocalDateTime cdate; // 레코드/엔티티 생성날짜
    @LastModifiedDate // 엔티티가 변경될 때 시간이 자동 저장/주입
    private LocalDateTime udate; // 레코드/엔티티 수정날짜

}
/*
*   엔티티의 생성/수정 일시를 감지해서 업데이트 해주는 클래스
*   어노테이션
*       1. @CreatedDate
*       2. @LastModifiedDate
*       3. @MappedSuperclass
*       4. @EntityListeners( AuditingEntityListener.class ) : 해당 클래스에서 엔티티 감지 기능
*           - @EntityListeners : 엔티티에서 특정 이벤트가 발생 할 때마다 특정 로직 실행
*           - AuditingEntityListener.class : 감지 이벤트 실행
*               - @CreateDate와 @
*               - insert[@CreateDate]와 update[@LastModifieDate] 할 때
*           + @EnableJpaAuditing :
*               - @SpringBootApplication 어노테이션 같은 위치에서 선언
*
* */