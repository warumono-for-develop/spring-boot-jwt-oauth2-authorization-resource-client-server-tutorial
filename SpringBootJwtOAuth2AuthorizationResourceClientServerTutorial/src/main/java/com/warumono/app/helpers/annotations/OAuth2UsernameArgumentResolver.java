package com.warumono.app.helpers.annotations;

import java.util.Objects;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.warumono.app.exceptions.ExceptionReason;
import com.warumono.app.exceptions.RestException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OAuth2UsernameArgumentResolver implements HandlerMethodArgumentResolver
{
	@Override
	public boolean supportsParameter(MethodParameter parameter)
	{
		log.debug("[aaa]parameter : {}", parameter);
		return Objects.nonNull(parameter.getParameterAnnotation(CurrentUsername.class)) && parameter.getParameterType().equals(String.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		log.debug("[aaa]authentication : {}", authentication);
		
		if(Objects.isNull(authentication))
		{
			throw new RestException(ExceptionReason.NOT_YET_AUTHORIZED);
		}
		
		String username = null;

		if(authentication.getClass().isAssignableFrom(OAuth2Authentication.class))
		{
			username = ((OAuth2Authentication)authentication).getUserAuthentication().getName();
		}
		
		return username;
	}
}
