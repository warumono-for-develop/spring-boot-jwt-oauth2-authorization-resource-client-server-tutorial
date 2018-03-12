package com.warumono.app.enums.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.warumono.app.enums.DealState;

@Converter
public class DealStateConverter extends AbstractEnumConverter<DealState> implements AttributeConverter<DealState, String>
{
	@Override
	public String convertToDatabaseColumn(DealState attribute)
	{
		return toDatabaseColumn(attribute);
	}

	@Override
	public DealState convertToEntityAttribute(String dbData)
	{
		return toEntityAttribute(DealState.PROCEEDING, dbData);
	}
}
