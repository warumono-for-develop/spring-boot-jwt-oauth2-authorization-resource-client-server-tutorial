package com.warumono.app.enums.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.warumono.app.enums.PurchaseType;

@Converter
public class PurchaseTypeConverter extends AbstractEnumConverter<PurchaseType> implements AttributeConverter<PurchaseType, String>
{
	@Override
	public String convertToDatabaseColumn(PurchaseType attribute)
	{
		return toDatabaseColumn(attribute);
	}

	@Override
	public PurchaseType convertToEntityAttribute(String dbData)
	{
		return toEntityAttribute(PurchaseType.DEPARTMENT_STORE, dbData);
	}
}
