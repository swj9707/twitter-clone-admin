package com.swj9707.twittercloneadmin.mail.service;

import com.swj9707.twittercloneadmin.mail.dto.MailDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MailServiceTest {

    @Autowired
    MailService mailService;

    @Test
    public void sendMailTest(){
        MailDTO mailDTO = MailDTO.builder()
                .title("Wassup")
                .address("wujins58@gmail.com")
                .message("Wassup")
                .build();

        mailService.mailSend(mailDTO);

    }

}