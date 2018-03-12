package com.warumono.app.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.warumono.app.controllers.interfaces.AppControllerInterface;
import com.warumono.app.enums.Provider;
import com.warumono.app.helpers.annotations.CurrentUsername;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AppController implements AppControllerInterface
{
	// http://localhost:8082/user/me
	@Value("${security.oauth2.resource.user-info-uri}")
	private String USER_INFO_URI;
	
	@Autowired
	private OAuth2RestOperations restTemplate;
	
	@Override
	public Principal me(Principal principal)
	{
		log.debug("principal : {}", principal);
		
		return principal;
	}
 	
	@Override
	public ResponseEntity<Object> login
	(
		@PathVariable Provider provider_id, 
		String username, String password
	)
	{
		log.debug("provider_id : {}", provider_id);
		
		log.debug("restTemplate : {}", restTemplate);
		log.debug("USER_INFO_URI : {}", USER_INFO_URI);
		
		restTemplate.getOAuth2ClientContext().getAccessTokenRequest().set("username", username);
		restTemplate.getOAuth2ClientContext().getAccessTokenRequest().set("password", password);
		
		OAuth2AccessToken accessToken = restTemplate.getAccessToken();
		
		return ResponseEntity.ok(accessToken);
	}

	@Override
	public ResponseEntity<Object> testRole(@CurrentUsername String username, OAuth2Authentication authentication)
	{
		log.debug("username : {}", username);
		log.debug("authentication : {}", authentication);
		
		Authentication authentication2 = SecurityContextHolder.getContext().getAuthentication();
		
		log.debug("authentication : {}", authentication2);
		
		ResponseEntity<Object> response = restTemplate.getForEntity("http://localhost:8080/user/me", Object.class);
		
		log.debug("response : {}", response);
		
		return response;
	}
}
