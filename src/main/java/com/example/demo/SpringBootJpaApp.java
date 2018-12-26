package com.example.demo;

import java.util.Collection;

import javax.annotation.PostConstruct;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.Environment;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.config.ApplicationProperties;
import com.example.demo.config.DefaultProfileUtil;

/**
 * Spring boot application
 * Boot app
 */
@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
@EnableDiscoveryClient
public class SpringBootJpaApp {

	private final Environment env;
	private static final Logger log = LoggerFactory.getLogger(SpringBootJpaApp.class);
	
	public SpringBootJpaApp(Environment env) {
		this.env = env;
	}
	
	/**
	 * Initialize boot app
	 */
	@PostConstruct
	public void initApplication() {
		Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
		
	}
	
	public static void main(String[] args) {
		
		SpringApplication app = new SpringApplication(SpringBootJpaApp.class);
		app.setAllowBeanDefinitionOverriding(true);
        DefaultProfileUtil.addDefaultProfile(app);
        Environment environment = app.run(args).getEnvironment();

		log.info("Hello World...!!!");
	}

	private static void logApplicationStartup(Environment env) {
		
		String protocol="http";
		if(env.getProperty("server.ssl.key-store") != null) {
			protocol="https";
		}

		String contextPath = env.getProperty("server.servlet.context-path");
		if(StringUtils.isBlank(contextPath))
			contextPath="/";
		
		try {
			String hostAddress = "localhost";
			hostAddress = InetAddress.getLocalHost().getHostAddress();
		}catch(UnknownHostException unhException) {
			
		}
		
		String configServerStatus = env.getProperty("configserver.status");
		if(configServerStatus == null) {
			configServerStatus = "Not found or not setup for this application";
		}
	}
}

