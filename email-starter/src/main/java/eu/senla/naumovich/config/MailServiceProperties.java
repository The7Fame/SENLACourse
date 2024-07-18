package eu.senla.naumovich.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

@Setter
@Getter
@ConfigurationProperties(prefix = "spring.mail")
public class MailServiceProperties {
    private String senderAddress;
}
