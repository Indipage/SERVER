package indipage.org.indipage.domain;

import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class ModifiedCreatedTimeBaseEntity extends CreatedTimeBaseEntity {

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

}