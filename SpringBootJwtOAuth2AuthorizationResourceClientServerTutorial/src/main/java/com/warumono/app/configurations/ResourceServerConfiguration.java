package com.warumono.app.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableResourceServer
@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter
{
	@Value("${security.oauth2.resource.id}")
	private String RESOURCE_ID;
	
	@Value("${security.remember-me.key}")
	private String REMEMBER_ME_KEY;
	
	@Value("${security.remember-me.parameter}")
	private String REMEMBER_ME_PARAMETER;
	
	@Value("${security.remember-me.token-validity-seconds}")
	private Integer REMEMBER_ME_TOKEN_VALIDITY_SECONDS;
	
	private static final String[] AUTH_WHITELIST = 
	{
		"/v2/api-docs", 
		"/configuration/ui", 
		"/configuration/security", 
		"/swagger-resources", 
		"/swagger-resources/configuration/ui", 
		"/swagger-resources/configuration/security", 
		"/swagger-ui.html", 
		"/webjars/**",
		
		"/favicon.ico"
	};
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources)
	{
		resources
			.resourceId(RESOURCE_ID);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception
	{
		http
			.headers()
				.frameOptions().disable();

		http
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http
			.rememberMe()
				.key(REMEMBER_ME_KEY)
				.rememberMeParameter(REMEMBER_ME_PARAMETER)
				.tokenValiditySeconds(REMEMBER_ME_TOKEN_VALIDITY_SECONDS);

		http
			.authorizeRequests()
				.antMatchers(AUTH_WHITELIST).permitAll()
				.antMatchers("/oauth/token").permitAll()
				.antMatchers("/auth/*").permitAll()
//				.antMatchers("/").permitAll()
				.anyRequest().authenticated();
	}
}
