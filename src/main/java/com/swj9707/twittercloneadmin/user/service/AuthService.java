package com.swj9707.twittercloneadmin.user.service;

import com.swj9707.twittercloneadmin.constant.enums.ErrorCode;
import com.swj9707.twittercloneadmin.constant.exception.CustomException;
import com.swj9707.twittercloneadmin.user.entity.AdminUserEntity;
import com.swj9707.twittercloneadmin.user.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    AdminUserRepository adminUserRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws CustomException {
        AdminUserEntity user = adminUserRepository.findAdminUserEntityByUserName(userName)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
        return User.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .roles(user.getRole().toString())
                .build();
    }
}
