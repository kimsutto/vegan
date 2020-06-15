package vegan.domain;

import java.time.LocalDateTime;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass //JPA entity클래스들이 BaseTimeEntity 상속할 경우 필드들도 컬럼을 인식하게함
@EntityListeners(AuditingEntityListener.class) // Auditing 기능 포함시킹
public abstract class BaseTimeEntity {
    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

}

//이 클래스는 모든 Entity의 상위 클래스가 되어 Entity들의 createdDate,modifiedDate를 자동관리한다.