package com.swj9707.twittercloneadmin.user.entity;

import com.swj9707.twittercloneadmin.constant.entities.BaseTimeEntity;
import com.swj9707.twittercloneadmin.constant.enums.Role;
import com.swj9707.twittercloneadmin.user.dto.AdminUserDTO;
import com.swj9707.twittercloneadmin.user.dto.AdminUserFormDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ADMIN_USER")
@Data
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminUserEntity extends BaseTimeEntity {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "user_name", unique = true)
    private String userName;

    @Column(name="user_email", unique = true)
    private String email;

    @Column(name ="password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", columnDefinition = "varchar(15) default 'OPER'")
    private Role role;

    @CreatedBy
    @Column(updatable = false, name = "created_by")
    private String createdBy;

    @Column(name = "last_login", nullable = true)
    LocalDateTime lastLogin;

    public static AdminUserEntity createUser(AdminUserFormDTO dto, PasswordEncoder passwordEncoder){
        return AdminUserEntity.builder()
                .userName(dto.getUserName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .createdBy(dto.getCreateBy())
                .build();
    }
}
