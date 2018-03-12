package com.warumono.app.enums;

import java.util.Arrays;

import com.warumono.app.enums.converters.AbstractEnumeratable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Grade implements AbstractEnumeratable<Grade>
{
	/**
	 * 고가: HIG
	 */
	HIGH("HIG"), 
	
	/**
	 * 중고가: HAM
	 */
	HIGHANDMID("HAM"), 
	
	/**
	 * 중가: MID
	 */
	MID("MID"), 
	
	/**
	 * 중저가: MAL
	 */
	MIDANDLOW("MAL"), 
	
	/**
	 * 저가: LOW
	 */
	LOW("LOW");
	
	private String dbData;

	@Override
	public String getToDatabaseColumn(Grade attribute)
	{
		return dbData;
	}

	@Override
	public Grade getToEntityAttribute(String dbData)
	{
		return Arrays.stream(Grade.values()).filter(e -> e.getDbData().equals(dbData)).findFirst().orElseThrow(null);
	}
}
