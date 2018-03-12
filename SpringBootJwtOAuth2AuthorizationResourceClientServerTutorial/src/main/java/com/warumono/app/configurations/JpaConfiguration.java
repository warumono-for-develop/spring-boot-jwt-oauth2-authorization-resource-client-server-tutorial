package com.warumono.app.configurations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.warumono.app.entities.AuditorAwareEntity;
import com.warumono.app.entities.User;
import com.warumono.app.helpers.AppConstant.Package;

@EnableJpaAuditing//(auditorAwareRef = "auditor")
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories(basePackages = { Package.REPOSITORIES })
@EntityScan
(
	basePackageClasses = { SpringApplication.class, Jsr310JpaConverters.class }, 
	basePackages = { Package.ENTITIES }
)
@Configuration
public class JpaConfiguration
{
	@Bean
	public AuditorAware<String> auditor()
	{
		return new AuditorAwareEntity();
	}
}
