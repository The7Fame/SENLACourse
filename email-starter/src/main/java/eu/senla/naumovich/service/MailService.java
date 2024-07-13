package eu.senla.naumovich.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {
    @Autowired
    private JavaMailSender mailSender;
    private final String fromWho;

    public void sendMsg(String to, String subject, String text){
        log.info("Send message to = {} with subject = {} with text = {}", to, subject, text);
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(fromWho);
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(text);
        mailSender.send(msg);
        log.info("Message sent");
    }
}
