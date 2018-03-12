package com.warumono.app.enums.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.warumono.app.enums.Category;

@Converter
public class CategoryConverter extends AbstractEnumConverter<Category> implements AttributeConverter<Category, String>
{
	@Override
	public String convertToDatabaseColumn(Category attribute)
	{
		return toDatabaseColumn(attribute);
	}

	@Override
	public Category convertToEntityAttribute(String dbData)
	{
		return toEntityAttribute(Category.BRAND, dbData);
	}
}
