package com.swj9707.twittercloneadmin.mail.service;

import com.swj9707.twittercloneadmin.mail.dto.MailDTO;
import com.swj9707.twittercloneadmin.user.dto.AdminUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;
    public void sendMailAboutNewAdmin(AdminUserDTO dto){
        MailDTO mailDTO = MailDTO.builder()
                .address("wujins58@gmail.com")
                .title("어드민 유저 생성 메시지")
                .message("새로운 어드민 유저" + dto.getUserName() + " 이 생성되었습니다.\n" +
                        "유저 이메일 : " + dto.getEmail() + "\n" +
                        "생성자 : " + dto.getCreatedBy() + "\n")
                .build();
        mailSend(mailDTO);
        sendGreetingMail(dto);
    }

    public void sendGreetingMail(AdminUserDTO dto){
        MailDTO mailDTO = MailDTO.builder()
                .address(dto.getEmail())
                .title("가입을 환영합니다.")
                .message("Twitter Clone Project 어드민으로 가입하신 것을 환영합니다.\n이제부터 서비스 운영에 참여할 수 있습니다.\n문의 사항은 관리자에게 연락해주세요.\n")
                .build();
        mailSend(mailDTO);
    }

    public void mailSend(MailDTO mailDTO){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDTO.getAddress());
        message.setFrom("wujins58@gmail.com");
        message.setSubject(mailDTO.getTitle());
        message.setText(mailDTO.getMessage());
        mailSender.send(message);
    }
}
