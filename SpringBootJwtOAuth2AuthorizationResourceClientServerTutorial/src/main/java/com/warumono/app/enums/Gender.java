package com.warumono.app.enums;

import java.util.Arrays;

import com.warumono.app.enums.converters.AbstractEnumeratable;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <pre>
 * 선택 안함 NOT_SELECTED("ETC"), 
 * 남자 MALE("MAL"), 
 * 여자 FEMALE("FEM"), 
 * </pre>
 * 
 * @author warumono
 *
 */
@AllArgsConstructor
@Getter
public enum Gender implements AbstractEnumeratable<Gender>
{
	/**
	 * 선택 안함: ETC
	 */
	NOT_SELECTED("ETC"), 
	
	/**
	 * 남자: MAL
	 */
	MALE("MAL"), 
	
	/**
	 * 여자: FEM
	 */
	FEMALE("FEM");
	
	private String dbData;

	@Override
	public String getToDatabaseColumn(Gender attribute)
	{
		return dbData;
	}

	@Override
	public Gender getToEntityAttribute(String dbData)
	{
		return Arrays.stream(Gender.values()).filter(e -> e.getDbData().equals(dbData)).findFirst().orElseThrow(null);
	}
}
