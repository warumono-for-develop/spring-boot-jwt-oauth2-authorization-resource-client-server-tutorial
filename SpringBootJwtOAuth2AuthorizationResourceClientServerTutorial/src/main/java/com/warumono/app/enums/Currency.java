package com.warumono.app.enums;

import java.util.Arrays;

import com.warumono.app.enums.converters.AbstractEnumeratable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Currency implements AbstractEnumeratable<Currency>
{
	/**
	 * 원: WON
	 */
	WON("WON"), 
	
	/**
	 * 달러: DOL
	 */
	DOLLAR("DOL");
	
	private String dbData;

	@Override
	public String getToDatabaseColumn(Currency attribute)
	{
		return dbData;
	}

	@Override
	public Currency getToEntityAttribute(String dbData)
	{
		return Arrays.stream(Currency.values()).filter(e -> e.getDbData().equals(dbData)).findFirst().orElseThrow(null);
	}
}
