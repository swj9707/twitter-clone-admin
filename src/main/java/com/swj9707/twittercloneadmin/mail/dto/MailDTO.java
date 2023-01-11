package com.swj9707.twittercloneadmin.mail.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MailDTO {
    private String address;
    private String title;
    private String message;
}
