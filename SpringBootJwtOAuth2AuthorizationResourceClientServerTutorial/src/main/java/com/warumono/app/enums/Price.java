package com.warumono.app.enums;

import java.util.Arrays;

import com.warumono.app.enums.converters.AbstractEnumeratable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Price implements AbstractEnumeratable<Price>
{
	/**
	 * 가격변동: FLU
	 */
	FLUCTUTATION("FLU"), 
	
	/**
	 * 특가세일: BAR
	 */
	BARGAIN_SALE("BAR");
	
	private String dbData;

	@Override
	public String getToDatabaseColumn(Price attribute)
	{
		return dbData;
	}

	@Override
	public Price getToEntityAttribute(String dbData)
	{
		return Arrays.stream(Price.values()).filter(e -> e.getDbData().equals(dbData)).findFirst().orElseThrow(null);
	}
}
