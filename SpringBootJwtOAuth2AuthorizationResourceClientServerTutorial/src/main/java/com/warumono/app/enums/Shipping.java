package com.warumono.app.enums;

import java.util.Arrays;

import com.warumono.app.enums.converters.AbstractEnumeratable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Shipping implements AbstractEnumeratable<Shipping>
{
	/**
	 * 판매물품 수거: COL
	 */
	COLLECTION("COL"), 
	
	/**
	 * 구매물품 배송: DEL
	 */
	DELIVERY("DEL"), 
	
	/**
	 * 무료 배송: FRE
	 */
	FREE("FRE");

	private String dbData;

	@Override
	public String getToDatabaseColumn(Shipping attribute)
	{
		return dbData;
	}

	@Override
	public Shipping getToEntityAttribute(String dbData)
	{
		return Arrays.stream(Shipping.values()).filter(e -> e.getDbData().equals(dbData)).findFirst().orElseThrow(null);
	}
}
