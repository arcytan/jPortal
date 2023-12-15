package cn.arcy.jportal.portal.vo;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserVo {

    Long id;

    String username;

    String nickname;

    String email;

    Boolean disabled;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;
}
