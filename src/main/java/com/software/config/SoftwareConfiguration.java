package com.software.config;

import com.software.service.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import com.software.repository.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.*;
import javax.sql.DataSource;
import java.util.Properties;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Configuration
public class SoftwareConfiguration {
	
}
