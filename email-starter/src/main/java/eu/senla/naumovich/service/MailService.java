package eu.senla.naumovich.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;
    private final String fromWho;

    public void sendMsg(String to, String subject, String text){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(fromWho);
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(text);
        mailSender.send(msg);
    }
}
