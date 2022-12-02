package com.swj9707.twittercloneadmin.user.dto;

import com.swj9707.twittercloneadmin.constant.enums.Role;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
@Data
@Builder
public class AdminUserFormDTO {

    @NotEmpty(message = "유저 ID를 입력하세요")
    @Length(min=4, max=20, message="유저 ID는 4자 이상 20자 이하로 입력해주세요")
    private String userName;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min=8, max=20, message="비밀번호는 8자 이상 20자 이하로 입력해주세요")
    private String password;

    private String createBy;

    @NotEmpty(message = "역할은 필수 입력값입니다.")
    private Role role;

}
