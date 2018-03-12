package com.warumono.app.enums.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.warumono.app.enums.Gender;

@Converter
public class GenderConverter extends AbstractEnumConverter<Gender> implements AttributeConverter<Gender, String>
{
	@Override
	public String convertToDatabaseColumn(Gender attribute)
	{
		return toDatabaseColumn(attribute);
	}

	@Override
	public Gender convertToEntityAttribute(String dbData)
	{
		return toEntityAttribute(Gender.NOT_SELECTED, dbData);
	}
}
