package com.warumono.app.enums.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.warumono.app.enums.DeviceType;

@Converter
public class DeviceTypeConverter extends AbstractEnumConverter<DeviceType> implements AttributeConverter<DeviceType, String>
{
	@Override
	public String convertToDatabaseColumn(DeviceType attribute)
	{
		return toDatabaseColumn(attribute);
	}

	@Override
	public DeviceType convertToEntityAttribute(String dbData)
	{
		return toEntityAttribute(DeviceType.IOS, dbData);
	}
}
