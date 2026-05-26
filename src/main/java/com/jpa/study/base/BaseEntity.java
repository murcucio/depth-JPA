package main.java.com.jpa.study.base;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 공통 매핑 정보를 모으는 클래스
 * - 테이블과 매핑되지 않음 (엔티티 아님)
 * - em.find(BaseEntity.class, id) 불가
 * - 등록일, 수정일 같이 모든 엔티티에 공통으로 필요한 필드를 관리
 * - 직접 생성할 일이 없으므로 추상 클래스로 선언
 */
@MappedSuperclass
public abstract class BaseEntity {

    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
