package lv.spoti.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lv.spoti.models.EmailDetails;
import lv.spoti.services.IEmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EmailServiceImpl implements IEmailService {
 
    @Autowired private JavaMailSender javaMailSender;
 
    @Value("${spring.mail.username}") private String sender;
 
    public String sendSimpleMail(EmailDetails details)
    {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
 
        mailMessage.setFrom(sender);
        mailMessage.setTo(details.getRecipient());
        mailMessage.setText(details.getMsgBody());
        mailMessage.setSubject(details.getSubject());
 
        javaMailSender.send(mailMessage);
        return "Mail Sent Successfully...";
    
    }
}