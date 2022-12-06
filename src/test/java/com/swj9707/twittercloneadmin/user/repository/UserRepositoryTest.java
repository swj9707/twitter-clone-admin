package com.swj9707.twittercloneadmin.user.repository;

import com.swj9707.twittercloneadmin.constant.enums.Role;
import com.swj9707.twittercloneadmin.mail.service.MailService;
import com.swj9707.twittercloneadmin.user.dto.AdminUserDTO;
import com.swj9707.twittercloneadmin.user.dto.AdminUserFormDTO;
import com.swj9707.twittercloneadmin.user.entity.AdminUserEntity;
import com.swj9707.twittercloneadmin.user.service.AdminUserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

@SpringBootTest
@AutoConfigureMockMvc
class UserRepositoryTest {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private AdminUserServiceImpl adminUserServiceImpl;
    @Autowired
    private MailService mailService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("Insert Super Admin")
    void insertSuperAdmin(){

        AdminUserFormDTO dto = AdminUserFormDTO.builder()
                .userName("root")
                .password("wassup")
                .email("wujins58@gmail.com")
                .createBy("Super User")
                .build();

        AdminUserEntity adminUser = AdminUserEntity.createUser(dto, passwordEncoder);
        adminUser.setRole(Role.SUPERADMIN);
        adminUserRepository.save(adminUser);
        mailService.sendMailAboutNewAdmin(AdminUserDTO.formToDTO(dto));

        List<AdminUserEntity> list = adminUserRepository.findAll();
        list.forEach(element -> {
            System.out.println(element.toString());
        });
    }

    @Test
    @DisplayName("Login Test")
    void loginSuccessTest() throws Exception {
        mockMvc.perform(formLogin().userParameter("userName")
                        .loginProcessingUrl("/auth/login")
                        .user("root").password("wassup"))
                .andExpect(SecurityMockMvcResultMatchers.authenticated());

    }

}