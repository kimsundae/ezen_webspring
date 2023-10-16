package ezenweb.model.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
@MappedSuperclass // 엔티티X [ 여러 엔티티가 공통으로 사용하는 필드에 대해 구성할 때]
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
*       4. @EntityListeners( AuditingEntityListener.class )
* */