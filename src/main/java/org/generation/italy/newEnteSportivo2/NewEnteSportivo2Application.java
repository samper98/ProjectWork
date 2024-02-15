package org.generation.italy.newEnteSportivo2;

import org.generation.italy.testspring.servlet.DatasourceProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(DatasourceProperties.class)
public class NewEnteSportivo2Application {

	public static void main(String[] args) {
		SpringApplication.run(NewEnteSportivo2Application.class, args);
	}

}
