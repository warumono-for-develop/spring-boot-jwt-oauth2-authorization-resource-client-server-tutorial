package com.warumono.app.enums.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.warumono.app.enums.PlatformApplication;

@Converter
public class PlatformApplicationConverter extends AbstractEnumConverter<PlatformApplication> implements AttributeConverter<PlatformApplication, String>
{
	@Override
	public String convertToDatabaseColumn(PlatformApplication attribute)
	{
		return toDatabaseColumn(attribute);
	}

	@Override
	public PlatformApplication convertToEntityAttribute(String dbData)
	{
		return toEntityAttribute(PlatformApplication.APNS, dbData);
	}
}
