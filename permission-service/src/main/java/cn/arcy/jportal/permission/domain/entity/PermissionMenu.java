package cn.arcy.jportal.permission.domain.entity;

import cn.arcy.jportal.jpa.entity.BaseEntity;
import cn.arcy.jportal.permission.enums.MenuType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "permission_menus")
public class PermissionMenu extends BaseEntity {
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

    @Column(name = "sort", nullable = false)
    private Integer sort = 1;

    @Column(name = "disabled", nullable = false)
    private Boolean disabled = false;

}