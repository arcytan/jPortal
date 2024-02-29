package cn.arcy.jportal.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class SoftDeleteEntity extends BaseEntity {

    @Column(name = "is_deleted", nullable = false)
    boolean isDeleted = false;

    @Column(name = "deleted_at")
    LocalDateTime deletedAt;
}
