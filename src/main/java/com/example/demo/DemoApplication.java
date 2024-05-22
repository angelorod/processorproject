package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.services.UserService;

@SpringBootApplication
public class DemoApplication {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Component
    class ApplicationStarter implements ApplicationListener<ApplicationReadyEvent> {

        private final UserService userService;

        public ApplicationStarter(UserService userService) {
            this.userService = userService;
        }

        @Override
        public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
            try {
                userService.registerUser("testuser", "testuser@email.com", "password");
                System.out.println("\n----------------------\n");
                System.out.println("registered user testuser");
                System.out.println("\n----------------------\n");
            } catch (ProcessorException e) {
                System.out.println("unable to register user");
                e.printStackTrace();
            }
        }
    }
}
