package com.warumono.app.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestOperations;

import com.warumono.app.helpers.JwtAuthenticationFilter;

@Configuration
public class FilterConfiguration
{
	@Autowired
	private OAuth2RestOperations restTemplate;
	
	@Bean
	public FilterRegistrationBean getFilterRegistrationBean()
	{
		FilterRegistrationBean registrationBean = new FilterRegistrationBean(new JwtAuthenticationFilter(restTemplate));
//		registrationBean.addUrlPatterns("/*"); // 서블릿 등록 빈 처럼 패턴을 지정해 줄 수 있다.
		
		return registrationBean;
	}
}
