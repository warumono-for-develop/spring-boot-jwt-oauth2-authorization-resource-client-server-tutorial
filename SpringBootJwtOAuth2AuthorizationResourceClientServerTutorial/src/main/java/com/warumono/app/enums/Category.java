package com.warumono.app.enums;

import java.util.Arrays;

import com.warumono.app.enums.converters.AbstractEnumeratable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Category implements AbstractEnumeratable<Category>
{
	/**
	 * 명품 브랜드: BRA
	 */
	BRAND("BRA"), 
	
	/**
	 * 유아용품: KID
	 */
	KIDS("KID"), 
	
	/**
	 * 티켓: TIC
	 */
	TICKET("TIC");

	private String dbData;

	@Override
	public String getToDatabaseColumn(Category attribute)
	{
		return dbData;
	}

	@Override
	public Category getToEntityAttribute(String dbData)
	{
		return Arrays.stream(Category.values()).filter(e -> e.getDbData().equals(dbData)).findFirst().orElseThrow(null);
	}
}
