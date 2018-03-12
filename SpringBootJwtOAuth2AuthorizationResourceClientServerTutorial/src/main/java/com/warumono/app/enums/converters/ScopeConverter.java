package com.warumono.app.enums.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.warumono.app.enums.Scope;

@Converter
public class ScopeConverter extends AbstractEnumConverter<Scope> implements AttributeConverter<Scope, String>
{
	@Override
	public String convertToDatabaseColumn(Scope attribute)
	{
		return toDatabaseColumn(attribute);
	}

	@Override
	public Scope convertToEntityAttribute(String dbData)
	{
		return toEntityAttribute(Scope.BUY, dbData);
	}
}
