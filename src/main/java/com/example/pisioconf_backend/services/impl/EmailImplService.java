package com.example.pisioconf_backend.services.impl;

import com.example.pisioconf_backend.services.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class EmailImplService implements EmailService {

    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;

    public EmailImplService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    @Async
    public void sendMailApproved(String to) throws  Exception{
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(to);
        String text = "Poštovani, \n\nVaš zahtjev za nalog je odobren.\nDobro došli u proEventConference tim!\n\nSrdačan pozdrav,\nproEventConference admin tim";
        message.setText(text);
        message.setSubject("Potvrda o registraciji");
        javaMailSender.send(message);
    }

    @Override
    @Async
    public void sendSimpleMailNotApproved(String to) throws Exception {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(to);
        String text="Poštovani, \n\nVaš zahtjev za nalog nije odobren.\nAko smatrate da se radi o grešci, obratite se administratorskom timu putem e-maila!\n\nSrdačan pozdrav,\nproEventConference admin tim";
        message.setText(text);
        message.setSubject("Odbijen zahtjev");
        javaMailSender.send(message);

    }
    @Override
    @Async
    public void sendSimpleMailBlocked(String to) throws Exception {

        SimpleMailMessage message= new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(to);
        String text="Poštovani, \n\nVaš nalog je blokiran.\nAko smatrate da se radi o grešci, obratite se administratorskom timu putem e-maila!\n\nSrdačan pozdrav,\nproEventConference admin tim";
        message.setText(text);
        message.setSubject("Brisanje naloga");
        javaMailSender.send(message);

    }
}
