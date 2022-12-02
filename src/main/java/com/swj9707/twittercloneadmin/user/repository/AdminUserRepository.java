package com.swj9707.twittercloneadmin.user.repository;

import com.swj9707.twittercloneadmin.user.entity.AdminUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminUserRepository extends JpaRepository<AdminUserEntity, Long> {
    Optional<AdminUserEntity> findAdminUserEntityByUserName(String username);
}
