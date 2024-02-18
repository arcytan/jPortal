package cn.arcy.jportal.permission.domain.entity;

import cn.arcy.jportal.permission.enums.MenuType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "permission_menus")
public class PermissionMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "parent_id", nullable = false)
    private Long parentId;

    @Column(name = "type", nullable = false)
    private MenuType type = MenuType.PAGE;

    @Column(name = "url")
    private String url;

    @Column(name = "level", nullable = false)
    private Byte level;

    @Column(name = "disabled", nullable = false)
    private Boolean disabled = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}