package com.warumono.app.enums.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.warumono.app.enums.Shipping;

@Converter
public class ShippingConverter extends AbstractEnumConverter<Shipping> implements AttributeConverter<Shipping, String>
{
	@Override
	public String convertToDatabaseColumn(Shipping attribute)
	{
		return toDatabaseColumn(attribute);
	}

	@Override
	public Shipping convertToEntityAttribute(String dbData)
	{
		return toEntityAttribute(Shipping.COLLECTION, dbData);
	}
}
