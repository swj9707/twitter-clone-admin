package com.swj9707.twittercloneadmin.user.dto;

import com.swj9707.twittercloneadmin.constant.enums.Role;
import com.swj9707.twittercloneadmin.user.entity.AdminUserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminUserDTO {
    private Long userId;
    private String userName;
    private String email;
    private Role role;
    private String createdBy;
    LocalDateTime lastLogin;

    public static AdminUserDTO entityToDTO(AdminUserEntity entity){
        return AdminUserDTO.builder()
                .userId(entity.getUserId())
                .userName(entity.getUserName())
                .email(entity.getEmail())
                .role(entity.getRole())
                .createdBy(entity.getCreatedBy())
                .lastLogin(entity.getLastLogin())
                .build();
    }

    public static AdminUserDTO formToDTO(AdminUserFormDTO formDTO){
        return AdminUserDTO.builder()
                .userName(formDTO.getUserName())
                .email(formDTO.getEmail())
                .createdBy(formDTO.getCreateBy())
                .build();
    }

    public static Page<AdminUserDTO> pageEntityToDTO(Page<AdminUserEntity> pageEntity){
        return pageEntity.map(AdminUserDTO::entityToDTO);
    }
}
