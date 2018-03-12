package com.warumono.app.enums.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.warumono.app.enums.CertificateType;

@Converter
public class CertificateTypeConverter extends AbstractEnumConverter<CertificateType> implements AttributeConverter<CertificateType, String>
{
	@Override
	public String convertToDatabaseColumn(CertificateType attribute)
	{
		return toDatabaseColumn(attribute);
	}

	@Override
	public CertificateType convertToEntityAttribute(String dbData)
	{
		return toEntityAttribute(CertificateType.EMAIL, dbData);
	}
}
