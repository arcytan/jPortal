package cn.arcy.jportal.user.domain.entity;

import cn.arcy.jportal.jpa.entity.BaseEntity;
import cn.arcy.jportal.jpa.entity.SoftDeleteEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    String username;

    String nickname;

    String email;

    String password;

    Boolean disabled = false;

    @Column(updatable = false)
    LocalDateTime createdAt = LocalDateTime.now();

    LocalDateTime updatedAt = LocalDateTime.now();
}
