package cn.arcy.jportal.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "created_at", insertable = false, updatable = false)
    LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", insertable = false, updatable = false)
    LocalDateTime updatedAt = LocalDateTime.now();
}
