package com.swj9707.twittercloneadmin.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserFormDTO {

    @NotEmpty(message = "유저 ID를 입력하세요")
    @Length(min=4, max=20, message="유저 ID는 4자 이상 20자 이하로 입력해주세요")
    private String userName;

    @NotEmpty(message = "이메일은 필수 입력값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min=4, max=20, message="비밀번호는 4자 이상 20자 이하로 입력해주세요")
    private String password;

    private String createBy;

}
