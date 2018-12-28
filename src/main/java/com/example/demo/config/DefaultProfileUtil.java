package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;

/**
 * Utility class to load Spring profile as a default
 * when there is no spring.profiles.active set in the environment or as command line argument.
 * 'dev' is the default profile
 */

public final class DefaultProfileUtil {

	private static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";
	
	public DefaultProfileUtil() {
		
	}
	
    /**
     * Set a default to use when no profile is configured.
     *
     * @param app the Spring application
     */
    public static void addDefaultProfile(SpringApplication app) {
        Map<String, Object> defProperties = new HashMap<>();
        /*
        * The default profile to use when no other profiles are defined
        * This cannot be set in the <code>application.yml</code> file.
        * See https://github.com/spring-projects/spring-boot/issues/1219
        */
        defProperties.put(SPRING_PROFILE_DEFAULT, "dev");
        app.setDefaultProperties(defProperties);
    }
    
    /**
     * Get the profiles that are applied else get default profiles.
     *
     * @param env spring environment
     * @return profiles
     */
    public static String[] getActiveProfiles(Environment env) {
        String[] profiles = env.getActiveProfiles();
        if (profiles.length == 0) {
            return env.getDefaultProfiles();
        }
        return profiles;
    }
}
