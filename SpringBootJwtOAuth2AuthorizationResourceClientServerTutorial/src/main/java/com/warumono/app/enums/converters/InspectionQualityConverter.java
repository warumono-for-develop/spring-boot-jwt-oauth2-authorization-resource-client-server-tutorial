package com.warumono.app.enums.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.warumono.app.enums.InspectionQuality;

@Converter
public class InspectionQualityConverter extends AbstractEnumConverter<InspectionQuality> implements AttributeConverter<InspectionQuality, String>
{
	@Override
	public String convertToDatabaseColumn(InspectionQuality attribute)
	{
		return toDatabaseColumn(attribute);
	}

	@Override
	public InspectionQuality convertToEntityAttribute(String dbData)
	{
		return toEntityAttribute(InspectionQuality.N, dbData);
	}
}
