package com.swj9707.twittercloneadmin.user.service;

import com.swj9707.twittercloneadmin.constant.enums.ErrorCode;
import com.swj9707.twittercloneadmin.constant.exception.CustomException;
import com.swj9707.twittercloneadmin.user.entity.AdminUserEntity;
import com.swj9707.twittercloneadmin.user.repository.AdminUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements UserDetailsService {
    private final AdminUserRepository adminUserRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws CustomException {
        AdminUserEntity user = adminUserRepository.findAdminUserEntityByUserName(userName)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
        LocalDateTime now = LocalDateTime.now();
        user.setLastLogin(now);
        adminUserRepository.save(user);
        return User.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .roles(user.getRole().toString())
                .build();
    }
}
