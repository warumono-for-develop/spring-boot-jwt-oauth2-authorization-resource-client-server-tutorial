package com.warumono.app.configurations;

import java.security.KeyPair;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter
{
	@Value("${security.oauth2.client.access-token-validity-seconds}")
	private Integer ACCESS_TOKEN_VALIDITY_SECONDS;
	
	//@Value("${security.oauth2.resource.jwt.keystore.name}")
	@Value("${security.oauth2.keystore.jks}")
	private String KEYSTORE;
	
	//@Value("${security.oauth2.resource.jwt.keystore.pass}")
	@Value("${security.oauth2.keystore.storepass}")
	private String KEYSTORE_STOREPASS;
	
	//@Value("${security.oauth2.resource.jwt.key-pair}")
	@Value("${security.oauth2.keystore.key-pair}")
	private String KEYSTORE_KEY_PAIR;
	
	@Value("${security.oauth2.resource.id}")
	private String RESOURCE_ID;
	
	@Value("${security.oauth2.client.client-id}")
	private String CLIENT_ID;
	
	@Value("${security.oauth2.client.client-secret}")
	private String CLIENT_SECRET;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private TokenStore tokenStore = new InMemoryTokenStore();

	@Bean
	@Primary
	public DefaultTokenServices tokenServices()
	{
		DefaultTokenServices tokenServices = new DefaultTokenServices();
		tokenServices.setTokenStore(tokenStore);
		tokenServices.setSupportRefreshToken(Boolean.TRUE);
		
		return tokenServices;
	}

	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter()
	{
		//// $ keytool -genkey -keyalg RSA -alias app -keystore keystore.jks -storepass keystorepass

		KeyPair keyPair = new KeyStoreKeyFactory
		(
			new ClassPathResource(KEYSTORE), 
			KEYSTORE_STOREPASS.toCharArray()
		).getKeyPair(KEYSTORE_KEY_PAIR);

		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setKeyPair(keyPair);
		
		return jwtAccessTokenConverter;
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception
	{
		security
//			//// will be applied to     /oauth/token_key
//			.tokenKeyAccess("isAnonymous() || hasRole('ROLE_TRUSTED_CLIENT')") // permitAll()
//			//// will be application to /oauth/check_token
//			.checkTokenAccess("hasRole('TRUSTED_CLIENT')") // isAuthenticated()
//			.allowFormAuthenticationForClients()
			.passwordEncoder(passwordEncoder);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception
	{
		endpoints
			.tokenStore(tokenStore)
			.accessTokenConverter(jwtAccessTokenConverter())
			.authenticationManager(authenticationManager)
			.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception
	{
		clients
			//*
			.jdbc(dataSource)
			.passwordEncoder(passwordEncoder);
			/*/
			.inMemory()
				.withClient(CLIENT_ID).secret(CLIENT_SECRET).authorizedGrantTypes("password", "refresh_token").authorities("USER").scopes("read", "write").resourceIds(RESOURCE_ID);
			//*/
	}
}
