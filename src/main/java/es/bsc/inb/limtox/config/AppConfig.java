package es.bsc.inb.limtox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import es.bsc.inb.limtox.services.MainServiceImpl;

@Configuration
@ComponentScan("es.bsc.inb.limtox")
@EnableJpaRepositories(basePackages="es.bsc.inb.limtox.daos", entityManagerFactoryRef="modelEntityManagerFactory")
public class AppConfig {

	@Bean
	MainServiceImpl mainServiceImpl() {
        return new MainServiceImpl();
    }
	
	
}
