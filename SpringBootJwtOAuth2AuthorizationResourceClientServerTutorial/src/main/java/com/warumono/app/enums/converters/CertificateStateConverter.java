package com.warumono.app.enums.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.warumono.app.enums.CertificateState;

@Converter
public class CertificateStateConverter extends AbstractEnumConverter<CertificateState> implements AttributeConverter<CertificateState, String>
{
	@Override
	public String convertToDatabaseColumn(CertificateState attribute)
	{
		return toDatabaseColumn(attribute);
	}

	@Override
	public CertificateState convertToEntityAttribute(String dbData)
	{
		return toEntityAttribute(CertificateState.PROCEEDING, dbData);
	}
}
