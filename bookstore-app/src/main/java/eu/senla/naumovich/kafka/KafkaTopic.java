package eu.senla.naumovich.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopic {

    @Value("${kafka.topic.address-update}")
    private String nameOfTopic;

    @Bean
    public NewTopic topic() {
        return new NewTopic(nameOfTopic, 1, (short) 1);
    }
}
