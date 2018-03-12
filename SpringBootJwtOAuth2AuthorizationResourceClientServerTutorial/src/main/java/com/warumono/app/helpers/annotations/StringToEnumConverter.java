package com.warumono.app.helpers.annotations;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import lombok.AllArgsConstructor;

@SuppressWarnings("rawtypes")
@EnumAttributeConverter
public class StringToEnumConverter implements ConverterFactory<String, Enum>
{
	@SuppressWarnings("unchecked")
	public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType)
	{
		return new StringToEnum(targetType);
	}

	@AllArgsConstructor
	private final class StringToEnum<T extends Enum> implements Converter<String, T>
	{
		private Class<T> enumeration;

		@SuppressWarnings("unchecked")
		public T convert(String source)
		{
			return (T)StringToEnumConverter.valueOfIgnoreCase(enumeration, source.trim());
		}
	}
	
	private static final <T extends Enum<T>> T valueOfIgnoreCase(Class<T> enumeration, String name)
	{
		return valueOfCase(enumeration, name, Boolean.TRUE);
	}
	
	@SuppressWarnings("unused")
	private static final <T extends Enum<T>> T valueOfProtectCase(Class<T> enumeration, String name)
	{
		return valueOfCase(enumeration, name, Boolean.FALSE);
	}
	
	private static final <T extends Enum<T>> T valueOfCase(Class<T> enumeration, String name, Boolean ignoreCase)
	{
		for(T e : enumeration.getEnumConstants())
		{
			if(ignoreCase)
			{
				if(e.name().equalsIgnoreCase(name))
				{
					return e;
				}
			}
			else
			{
				if(e.name().equals(name))
				{
					return e;
				}
			}
		}
		
		throw new IllegalArgumentException(String.format("There is no value with name '%s' in Enum %s", name, enumeration.getName()));
	}
}
