package eu.senla.naumovich.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

@Setter
@Getter
@ConfigurationProperties(prefix = "spring.email")
public class MailServiceProperties {
    private String hostname = "smtp.yandex.ru";
    private Integer port = 465;
    private String username;
    private String password;
    private String protocol = "smtp";
    private String senderAddress = "no-reply@mail.com";
    private String ssl = "false";
    private String tsl = "false";
    private String debug = "true";
}
