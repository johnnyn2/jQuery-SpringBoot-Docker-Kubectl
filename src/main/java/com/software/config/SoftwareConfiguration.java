package com.software.config;

import com.software.service.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class SoftwareConfiguration {
	@Bean
	public SoftwareService softwareService() {
		return new SoftwareImpl();
	}
}
