package com.warumono.app.helpers;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter
//public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter
{
	private OAuth2RestOperations restTemplate;

	public JwtAuthenticationFilter(OAuth2RestOperations restTemplate)
	{
//		super("/**");
		
		this.restTemplate = restTemplate;
	}
	
	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
//	@Override
//	protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response)
//	{
//		return Boolean.TRUE;
//	}
//	
//	@Override
//	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException
//	{
//		String header = request.getHeader("Authorization");
//
////		if(header == null || !header.startsWith("Bearer "))
//		if(Objects.isNull(header) || BooleanUtils.isFalse(header.startsWith("Bearer ")))
//		{
//			throw new JwtTokenMissingException("No JWT token found in request headers");
//		}
//
//		String authToken = header.substring(7);
//
//		JwtAuthenticationToken authRequest = new JwtAuthenticationToken(authToken);
//
//		return getAuthenticationManager().authenticate(authRequest);
//	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		if(request instanceof HttpServletRequest)
		{
			if(isAuthenticationRequired())
			{
				String username = ((HttpServletRequest)request).getParameter("username");
				String password = ((HttpServletRequest)request).getParameter("password");
				
				log.debug("username : {}", username);
				log.debug("password : {}", password);
				
				// extract token from header
//				OEWebToken token = extractToken(request);
				restTemplate.getOAuth2ClientContext().getAccessTokenRequest().set("username", username);
				restTemplate.getOAuth2ClientContext().getAccessTokenRequest().set("password", password);
				
				OAuth2AccessToken accessToken = restTemplate.getAccessToken();
				
				log.debug("accessToken : {}", accessToken);
				
//				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(principal, credentials)
//
//				// dump token into security context (for authentication-provider to pick up)
//				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			else
			{
				log.debug("session already contained valid Authentication - not checking again");
			}
		}

		chain.doFilter(request, response);
	}
	
	private Boolean isAuthenticationRequired()
	{
		// apparently filters have to check this themselves. So make sure they have a proper AuthenticatedAccount in their session.
		Authentication authenttication = SecurityContextHolder.getContext().getAuthentication();
		/*
		if(Objects.isNull(authenttication) || BooleanUtils.isFalse(authenttication.isAuthenticated()))
		{
			return Boolean.TRUE;
		}
		
		if(BooleanUtils.isFalse(authenttication instanceof User))
		{
			return Boolean.TRUE;
		}
		
		// current session already authenticated
		return Boolean.FALSE;
		/*/
		return (Objects.isNull(authenttication) || BooleanUtils.isFalse(authenttication.isAuthenticated()));
		//*/
	}
}
