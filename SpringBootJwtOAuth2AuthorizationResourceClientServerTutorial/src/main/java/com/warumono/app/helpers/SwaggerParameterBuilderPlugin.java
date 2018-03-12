package com.warumono.app.helpers;

import java.lang.annotation.Annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.fasterxml.classmate.TypeResolver;
import com.warumono.app.helpers.annotations.CurrentUsername;

import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ParameterBuilderPlugin;
import springfox.documentation.spi.service.contexts.ParameterContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

@Order(value = SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER)
@Component
public class SwaggerParameterBuilderPlugin implements ParameterBuilderPlugin
{
	@SuppressWarnings("unused")
	private final TypeResolver typeResolver;

	@Autowired
	public SwaggerParameterBuilderPlugin(TypeResolver typeResolver)
	{
		this.typeResolver = typeResolver;
	}
	
	@Autowired
	public SwaggerParameterBuilderPlugin swaggerDefaultParameterConfiguration;
	
	@Override
	public boolean supports(DocumentationType delimiter)
	{
//		return false;
//		return SwaggerPluginSupport.pluginDoesApply(delimiter);
		return DocumentationType.SWAGGER_2.equals(delimiter);
	}
	
	@Override
	public void apply(ParameterContext parameterContext)
	{
		ResolvedMethodParameter methodParameter = parameterContext.resolvedMethodParameter();
		
		// always hide with @CurrentClientId or @CurrentAuthorities or @CurrentUsername
		for(Annotation annotation : methodParameter.getAnnotations())
		{
			if
			(
//				annotation.annotationType().equals(CurrentClientId.class) || 
//				annotation.annotationType().equals(CurrentAuthorities.class) || 
				annotation.annotationType().equals(CurrentUsername.class)
			)
			{
				parameterContext.parameterBuilder().hidden(true);
			}
		}
	}
}
