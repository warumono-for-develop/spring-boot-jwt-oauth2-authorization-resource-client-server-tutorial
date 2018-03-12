package com.warumono.app.enums.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.warumono.app.enums.Grade;

@Converter
public class GradeConverter extends AbstractEnumConverter<Grade> implements AttributeConverter<Grade, String>
{
	@Override
	public String convertToDatabaseColumn(Grade attribute)
	{
		return toDatabaseColumn(attribute);
	}

	@Override
	public Grade convertToEntityAttribute(String dbData)
	{
		return toEntityAttribute(Grade.HIGH, dbData);
	}
}
