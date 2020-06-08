package com.software.initializer;

import com.software.model.Role;
import com.software.model.User;

import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = { "com.software.*" }, exclude = HibernateJpaAutoConfiguration.class)
public class Initializer extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Initializer.class);
	}

	public static void main(String[] args) {
		Configuration configuration = new Configuration().configure().addAnnotatedClass(User.class).addAnnotatedClass(Role.class); // configure hibernate.cfg.xml
		SpringApplication.run(Initializer.class, args);
	}
}
