package com.warumono.app.services;

import java.util.Collection;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.warumono.app.entities.Role;
import com.warumono.app.entities.User;
import com.warumono.app.enums.Authority;
import com.warumono.app.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AppUserDetailsService implements UserDetailsService
{
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		log.debug("========== loadUserByUsername ==========");
		log.debug("username : {}", username);
		
		User user = userRepository.findByUsername(username);
		
		if(Objects.isNull(user))
		{
			throw new UsernameNotFoundException(username);
		}
		
		log.debug("password : {}", user.getPassword());
		log.debug("authorities : {}", user.authorities());
		log.debug("========== loadUserByUsername ==========");
		
		return org.springframework.security.core.userdetails.User.withUsername(username).password(user.getPassword()).authorities(user.authorities()).build();
	}
	
	// -----
	
	public User store(String username, String password, Authority authority)
	{
		Collection<Role> _roles = Authority.roles(authority);
		
		User user = User.builder()
				.username(username)
				.password(password)
				.build();
		
		_roles.forEach(r -> { user.bind(r); });
		
		return userRepository.save(user);
	}
}
