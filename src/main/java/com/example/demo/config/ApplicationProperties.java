package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Application specific properties
 *	configured in application.yaml file
 */

@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

}
