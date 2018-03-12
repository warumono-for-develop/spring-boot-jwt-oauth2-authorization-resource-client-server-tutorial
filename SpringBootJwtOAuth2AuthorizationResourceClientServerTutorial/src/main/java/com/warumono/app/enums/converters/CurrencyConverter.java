package com.warumono.app.enums.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.warumono.app.enums.Currency;

@Converter
public class CurrencyConverter extends AbstractEnumConverter<Currency> implements AttributeConverter<Currency, String>
{
	@Override
	public String convertToDatabaseColumn(Currency attribute)
	{
		return toDatabaseColumn(attribute);
	}

	@Override
	public Currency convertToEntityAttribute(String dbData)
	{
		return toEntityAttribute(Currency.WON, dbData);
	}
}
