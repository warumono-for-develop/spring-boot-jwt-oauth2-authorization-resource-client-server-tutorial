package com.warumono.app.helpers;

import java.awt.print.Pageable;
import java.lang.reflect.WildcardType;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;

@Component
public class SwaggerParameter
{
	@Autowired
	private TypeResolver typeResolver;
	
	public Docket docket(Boolean enabled, String groupName, String path)
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.enable(enabled)
				.groupName(groupName)
				.apiInfo(apiInfo())
				.produces(produces())
				.select()
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
				.paths(PathSelectors.ant(path))
				.build()
				.pathMapping("/")
				.useDefaultResponseMessages(Boolean.TRUE)
				.directModelSubstitute(LocalDate.class, String.class)
				.genericModelSubstitutes(ResponseEntity.class)
				.alternateTypeRules(new AlternateTypeRule(typeResolver.resolve(DeferredResult.class, typeResolver.resolve(ResponseEntity.class, WildcardType.class)), typeResolver.resolve(WildcardType.class)))
				/*
				.globalOperationParameters(defaultParameters())
				/*/
				.securityContexts(Lists.newArrayList(securityContext(path)))
				.securitySchemes(Lists.newArrayList(apiKey()))
				//*/
				.ignoredParameterTypes
				(
					OAuth2Authentication.class, 
					Pageable.class
				);
	}
	
	private ApiInfo apiInfo()
	{
		return new ApiInfoBuilder()
				.title("RESOURCE")
				.description("<strong>OAuth2 Server</strong> documents for <strong>Spring Boot</strong>.")
				.contact(new Contact("warumono", "https://github.com/warumono-for-develop", "warumono.for.develop@gmail.com"))
				.version("1.0.0")
				.build();
	}
	
	private Set<String> produces()
	{
		return Sets.newHashSet
				(
//					MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE
				);
	}
	
	// for authorization
	/*
	private List<Parameter> defaultParameters()
	{
		return Lists.newArrayList
				(
					new ParameterBuilder()
						.name("Authorization")
						.description("Access Token")
						.modelRef(new ModelRef("string"))
						.parameterType("header")
						.defaultValue("Bearer <access_token>")
						.required(Boolean.TRUE)
						.build()
				);
	}
	/*/
	private SecurityContext securityContext(String path)
	{
		return SecurityContext.builder()
				.securityReferences(defaultScopes())
//				.forPaths(PathSelectors.regex("/anyPath.*"))
				.forPaths(PathSelectors.ant(path))
				.build();
	}

	private List<SecurityReference> defaultScopes()
	{
		AuthorizationScope authorizationScope = new AuthorizationScope("read,write", "Readable and Writable");
		
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		
		return Lists.newArrayList(new SecurityReference("Authorization", authorizationScopes));
	}
	
	private ApiKey apiKey()
	{
		return new ApiKey("Authorization", "Authorization", ApiKeyVehicle.HEADER.getValue());
	}
	//*/
}
