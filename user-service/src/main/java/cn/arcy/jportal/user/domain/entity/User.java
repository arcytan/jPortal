package cn.arcy.jportal.user.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String username;

    String nickname;

    String email;

    String password;

    Boolean disabled;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;
}
