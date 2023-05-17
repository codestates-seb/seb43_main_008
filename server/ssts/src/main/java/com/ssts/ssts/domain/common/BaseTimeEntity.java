package com.ssts.ssts.domain.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
//JPA 엔티티 클래스에서 공통적으로 사용되는 필드나 매핑정보를 정의하는 클래스에 부여되는 애너테이션
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
//엔티티의 라이프사이클 이벤트를 수신한다.
public class BaseTimeEntity {
    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    //FIXME S3에 올라가면 timeZone이 바뀐다. 이부분 로컬 타임을 서울로 바꿔줘야한다.

    @LastModifiedDate
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name= "deleted_at")
    private LocalDateTime deletedAt;
}
