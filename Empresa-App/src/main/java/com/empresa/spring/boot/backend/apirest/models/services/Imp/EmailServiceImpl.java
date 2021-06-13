package com.empresa.spring.boot.backend.apirest.models.services.Imp;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;

import com.empresa.spring.boot.backend.apirest.models.entity.Email;
import com.empresa.spring.boot.backend.apirest.models.services.IEmailService;



@Service
public class EmailServiceImpl implements IEmailService {
	
    private final JavaMailSender javaMailSender;

    
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(Email mail) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(mail.getDestinatario(), mail.getDestinatario());

        msg.setSubject(mail.getAsunto());
        msg.setText(mail.getMensaje());

        javaMailSender.send(msg);
    }	
	
}
