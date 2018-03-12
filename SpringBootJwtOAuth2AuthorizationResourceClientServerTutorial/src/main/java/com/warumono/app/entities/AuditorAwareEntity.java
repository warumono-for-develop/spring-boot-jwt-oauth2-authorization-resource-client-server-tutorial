package com.warumono.app.entities;

import java.util.Objects;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareEntity implements AuditorAware<String>
{
	@Override
	public String getCurrentAuditor()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(Objects.isNull(authentication) || BooleanUtils.isFalse(authentication.isAuthenticated()))
		{
			return null;
		}
		
		return ((User)authentication.getPrincipal()).getIdentity();
	}
}
