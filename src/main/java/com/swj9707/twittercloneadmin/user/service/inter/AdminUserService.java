package com.swj9707.twittercloneadmin.user.service.inter;

import com.swj9707.twittercloneadmin.constant.dto.PageResultDTO;
import com.swj9707.twittercloneadmin.user.dto.AdminUserDTO;
import com.swj9707.twittercloneadmin.user.dto.AdminUserFormDTO;
import com.swj9707.twittercloneadmin.user.dto.AdminUserPageRequestDTO;
import com.swj9707.twittercloneadmin.user.entity.AdminUserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminUserService {
    Long addUser(AdminUserFormDTO dto);
    Page<AdminUserDTO> getList(String userName, Pageable pageable);
    AdminUserDTO get(Long userID);
    Long modifyRole(Long userId);
    void delete(Long userID);
}
