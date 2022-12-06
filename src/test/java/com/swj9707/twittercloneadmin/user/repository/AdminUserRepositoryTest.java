package com.swj9707.twittercloneadmin.user.repository;

import com.swj9707.twittercloneadmin.constant.dto.PageResultDTO;
import com.swj9707.twittercloneadmin.constant.enums.Role;
import com.swj9707.twittercloneadmin.user.dto.AdminUserDTO;
import com.swj9707.twittercloneadmin.user.dto.AdminUserFormDTO;
import com.swj9707.twittercloneadmin.user.dto.AdminUserPageRequestDTO;
import com.swj9707.twittercloneadmin.user.entity.AdminUserEntity;
import com.swj9707.twittercloneadmin.user.service.inter.AdminUserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AdminUserRepositoryTest {

    @Autowired
    AdminUserService adminUserService;

    @Test
    @DisplayName("유저 페이징 처리 테스트")
    public void pagingTest() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<AdminUserDTO> result = adminUserService.getList("", pageable);
        System.out.println("Result=================");
        result.get().forEach(element -> {
            System.out.println(element.toString());
        });
    }

    @Test
    @DisplayName("유저 생성 테스트")
    public void addAdminUser(){
        AdminUserFormDTO adminUserFormDTO = new AdminUserFormDTO();
        adminUserFormDTO.setUserName("wassup");
        adminUserFormDTO.setPassword("wassup");
        adminUserFormDTO.setEmail("swj9707@gmail.com");
        adminUserFormDTO.setCreateBy("root");
        adminUserService.addUser(adminUserFormDTO);
    }

}