package com.warumono.app.enums.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.warumono.app.enums.Authority;

@Converter
public class AuthorityConverter extends AbstractEnumConverter<Authority> implements AttributeConverter<Authority, String>
{
	@Override
	public String convertToDatabaseColumn(Authority attribute)
	{
		return toDatabaseColumn(attribute);
	}

	@Override
	public Authority convertToEntityAttribute(String dbData)
	{
		return toEntityAttribute(Authority.USER, dbData);
	}
}
