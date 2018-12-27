package com.example.demo.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

/**
 * Configures Spring Cloud Stream support.
 * This works out-of-the-box if you use the Docker Compose configuration at "src/main/docker/kafka.yml".
 */
@EnableBinding(value = { Source.class })
public class MessagingConfiguration {

    private String applicationName;

    /**
     * This sends a test message at regular intervals set as fixedRate (in ms)
     */
    @Bean
    @InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedRate = "60000"))
    public MessageSource<String> timerMessageSource() {
        return () -> new GenericMessage<>("Test message from " + applicationName
            + " sent at " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
}
