package eu.senla.naumovich.config;

import eu.senla.naumovich.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
@EnableConfigurationProperties(MailServiceProperties.class)
@RequiredArgsConstructor
public class MailAutoConfiguration {
    private final MailServiceProperties properties;

    @Bean
    @ConditionalOnClass(JavaMailSender.class)
    public MailService mailService(){
        return new MailService(properties.getSenderAddress());
    }
}
