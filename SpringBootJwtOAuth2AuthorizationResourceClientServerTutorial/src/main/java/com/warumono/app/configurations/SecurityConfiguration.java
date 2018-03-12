package com.warumono.app.configurations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

import com.warumono.app.services.AppUserDetailsService;

import lombok.extern.slf4j.Slf4j;

@EnableWebSecurity//(debug = true)
@Configuration
@Slf4j
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
	@Value("${security.oauth2.resource.id}")
	private String RESOURCE_ID;
	
	@Value("${security.oauth2.client.client-id}")
	private String CLIENT_ID;
	
	@Value("${security.oauth2.client.client-secret}")
	private String CLIENT_SECRET;
	
	@Value("${security.oauth2.client.access-token-uri}")
	private String ACCESS_TOKEN_URI;
	
	@Value("${security.oauth2.client.grant-type}")
	private String GRANT_TYPE;
	
	@Value("#{'${security.oauth2.client.scope}'.split(',')}")
	private List<String> SCOPE;

	@Value("${security.oauth2.client.user-authorization-uri}")
	private String USER_AUTHORIZATION_URI;
	
	@Autowired
	private AppUserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth
			//*
			.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
		
//			.jdbcAuthentication()
//				.dataSource(dataSource)
//				.passwordEncoder(passwordEncoder());
			/*/
			.inMemoryAuthentication()
				.withUser("user").password("password").roles("USER");
			//*/
	}
	
	@Bean
	public OAuth2RestOperations restTemplate(OAuth2ClientContext oauth2ClientContext)
	{
		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resource(), oauth2ClientContext);
		restTemplate.setAccessTokenProvider(new ResourceOwnerPasswordAccessTokenProvider());

		return restTemplate;
	}

	private OAuth2ProtectedResourceDetails resource()
	{
		log.debug("RESOURCE_ID : {}", RESOURCE_ID);
		log.debug("CLIENT_ID : {}", CLIENT_ID);
		log.debug("CLIENT_SECRET : {}", CLIENT_SECRET);
		log.debug("ACCESS_TOKEN_URI : {}", ACCESS_TOKEN_URI);
		log.debug("GRANT_TYPE : {}", GRANT_TYPE);
		log.debug("SCOPE : {}", SCOPE);
		
		ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
		resourceDetails.setId(RESOURCE_ID);
		resourceDetails.setClientId(CLIENT_ID);
		resourceDetails.setClientSecret(CLIENT_SECRET);
		resourceDetails.setAccessTokenUri(ACCESS_TOKEN_URI);
		resourceDetails.setGrantType(GRANT_TYPE);
		resourceDetails.setScope(SCOPE);
		// resourceDetails.setUsername("tutorial"); // 파라메터로 받게 됨.
		// resourceDetails.setPassword("secret"); // 파라메터로 받게 됨.

		return resourceDetails;
	}
}
