package com.warumono.app.enums;

import java.util.Arrays;

import com.warumono.app.enums.converters.AbstractEnumeratable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DealState implements AbstractEnumeratable<DealState>
{
	/**
	 * 진행중: PRO
	 */
	PROCEEDING("PRO"), 
	
	/**
	 * 완료: CMP
	 */
	COMPLETE("CMP");

	private String dbData;

	@Override
	public String getToDatabaseColumn(DealState attribute)
	{
		return dbData;
	}

	@Override
	public DealState getToEntityAttribute(String dbData)
	{
		return Arrays.stream(DealState.values()).filter(e -> e.getDbData().equals(dbData)).findFirst().orElseThrow(null);
	}
}
