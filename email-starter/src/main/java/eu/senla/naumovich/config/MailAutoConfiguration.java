package eu.senla.naumovich.config;

import eu.senla.naumovich.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@EnableConfigurationProperties(MailServiceProperties.class)
public class MailAutoConfiguration {
    @Autowired
    private MailServiceProperties properties;

    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(properties.getHostname());
        javaMailSender.setUsername(properties.getUsername());
        javaMailSender.setPassword(properties.getPassword());
        javaMailSender.setPort(properties.getPort());

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", properties.getTsl());
        props.put("mail.smtp.ssl.enable", properties.getSsl());
        props.put("mail.debug", properties.getDebug());
        javaMailSender.setJavaMailProperties(props);
        return javaMailSender;
    }

    @Bean
    public MailService mailService(){
        return new MailService(javaMailSender(), properties.getSenderAddress());
    }
}
