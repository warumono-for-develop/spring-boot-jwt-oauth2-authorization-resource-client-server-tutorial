package com.warumono.app.enums.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.warumono.app.enums.Price;

@Converter
public class PriceConverter extends AbstractEnumConverter<Price> implements AttributeConverter<Price, String>
{
	@Override
	public String convertToDatabaseColumn(Price attribute)
	{
		return toDatabaseColumn(attribute);
	}

	@Override
	public Price convertToEntityAttribute(String dbData)
	{
		return toEntityAttribute(Price.FLUCTUTATION, dbData);
	}
}
