package com.csquard.mregister;
import java.util.Arrays;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.context.ApplicationContext;

import com.csquard.mregister.model.FileStorageProperties;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class MRegisterApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(MRegisterApplication.class, args);
		
		 System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
	}
}
