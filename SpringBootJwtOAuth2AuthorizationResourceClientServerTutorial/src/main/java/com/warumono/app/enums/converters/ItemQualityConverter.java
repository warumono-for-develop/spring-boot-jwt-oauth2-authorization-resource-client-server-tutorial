package com.warumono.app.enums.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.warumono.app.enums.ItemQuality;

@Converter
public class ItemQualityConverter extends AbstractEnumConverter<ItemQuality> implements AttributeConverter<ItemQuality, String>
{
	@Override
	public String convertToDatabaseColumn(ItemQuality attribute)
	{
		return toDatabaseColumn(attribute);
	}

	@Override
	public ItemQuality convertToEntityAttribute(String dbData)
	{
		return toEntityAttribute(ItemQuality.N, dbData);
	}
}
