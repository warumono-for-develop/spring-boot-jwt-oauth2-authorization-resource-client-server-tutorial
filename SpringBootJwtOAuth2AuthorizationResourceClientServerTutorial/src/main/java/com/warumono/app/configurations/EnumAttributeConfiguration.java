package com.warumono.app.configurations;

import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.converter.ConverterRegistry;

import com.warumono.app.helpers.annotations.EnumAttributeConverter;

@Configuration
public class EnumAttributeConfiguration
{
	@Autowired(required = false)
	@EnumAttributeConverter
	private Set<Converter<?, ?>> autoRegisteredConverters;

	@Autowired(required = false)
	@EnumAttributeConverter
	private Set<ConverterFactory<?, ?>> autoRegisteredConverterFactories;

	@Autowired
	private ConverterRegistry converterRegistry;

	@PostConstruct
	public void conversionService()
	{
		if(autoRegisteredConverters != null)
		{
			for(Converter<?, ?> converter : autoRegisteredConverters)
			{
				converterRegistry.addConverter(converter);
			}
		}
		
		if(autoRegisteredConverterFactories != null)
		{
			for(ConverterFactory<?, ?> converterFactory : autoRegisteredConverterFactories)
			{
				converterRegistry.addConverterFactory(converterFactory);
			}
		}
	}
}
