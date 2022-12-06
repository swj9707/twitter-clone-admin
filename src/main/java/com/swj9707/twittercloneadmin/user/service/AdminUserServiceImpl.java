package com.swj9707.twittercloneadmin.user.service;

import com.swj9707.twittercloneadmin.constant.dto.PageResultDTO;
import com.swj9707.twittercloneadmin.constant.enums.ErrorCode;
import com.swj9707.twittercloneadmin.constant.enums.Role;
import com.swj9707.twittercloneadmin.constant.exception.CustomException;
import com.swj9707.twittercloneadmin.mail.service.MailService;
import com.swj9707.twittercloneadmin.user.dto.AdminUserDTO;
import com.swj9707.twittercloneadmin.user.dto.AdminUserFormDTO;
import com.swj9707.twittercloneadmin.user.dto.AdminUserPageRequestDTO;
import com.swj9707.twittercloneadmin.user.entity.AdminUserEntity;
import com.swj9707.twittercloneadmin.user.repository.AdminUserRepository;
import com.swj9707.twittercloneadmin.user.service.inter.AdminUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {
    private final MailService mailService;
    private final AdminUserRepository adminUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Long addUser(AdminUserFormDTO dto){
        AdminUserEntity adminUser = AdminUserEntity.createUser(dto, passwordEncoder);
        AdminUserEntity result = adminUserRepository.save(adminUser);
        mailService.sendMailAboutNewAdmin(AdminUserDTO.formToDTO(dto));
        return result.getUserId();
    }

    @Override
    public Page<AdminUserDTO> getList(String userName, Pageable pageable) {
        Page<AdminUserEntity> result = adminUserRepository.findByUserNameStartsWith(userName, pageable);
        return AdminUserDTO.pageEntityToDTO(result);
    }

    @Override
    public AdminUserDTO get(Long userID) {
        AdminUserEntity result = adminUserRepository.findAdminUserEntityByUserId(userID)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOTFOUND));
        return AdminUserDTO.entityToDTO(result);
    }

    @Override
    public Long modifyRole(Long userId) {
        AdminUserEntity entity = adminUserRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOTFOUND));
        if(entity.getRole().equals(Role.ADMIN)){
            entity.setRole(Role.OPER);
        } else if (entity.getRole().equals(Role.OPER)){
            entity.setRole(Role.ADMIN);
        }
        adminUserRepository.save(entity);
        return userId;
    }

    @Override
    public void delete(Long userID) {
        adminUserRepository.deleteById(userID);
    }

}
