package com.warumono.app.enums;

import java.util.Arrays;
import java.util.Collection;

import com.google.common.collect.Sets;
import com.warumono.app.entities.Role;
import com.warumono.app.enums.converters.AbstractEnumeratable;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <pre>
 * 사용자 USER("USER"), 
 * 관리자 STAFF("STAFF"), 
 * 최고관리자 ADMIN("ADMIN"), 
 * </pre>
 * 
 * @author warumono
 *
 */
@AllArgsConstructor
@Getter
public enum Authority implements AbstractEnumeratable<Authority>
{
	/**
	 * 클라이언트: TRUSTED_CLIENT
	 */
	TRUSTED_CLIENT("ROLE_TRUSTED_CLIENT"), 
	
	/**
	 * 사용자: USER
	 */
	USER("ROLE_USER"), 
	
	/**
	 * 관리자: STAFF
	 */
	STAFF("ROLE_STAFF"), 
	
	/**
	 * 최고관리자: ADMIN
	 */
	ADMIN("ROLE_ADMIN");
	
	private String dbData;

	@Override
	public String getToDatabaseColumn(Authority attribute)
	{
		return dbData;
	}

	@Override
	public Authority getToEntityAttribute(String dbData)
	{
		return Arrays.stream(Authority.values()).filter(e -> e.getDbData().equals(dbData)).findFirst().orElseThrow(null);
	}
	
	public static final Collection<Role> roles(Authority authority)
	{
		Collection<Role> roles = Sets.newHashSet();
		
		switch(authority)
		{
			case ADMIN: 
				roles.add(Role.on(Authority.ADMIN));
			case STAFF: 
				roles.add(Role.on(Authority.STAFF));
			default: // USER
				roles.add(Role.on(Authority.USER));
				break;
		}
		
		return roles;
	}
}
