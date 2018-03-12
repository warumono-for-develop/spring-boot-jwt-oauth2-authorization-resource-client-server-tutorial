package com.warumono.app.enums;

import java.util.Arrays;

import com.warumono.app.enums.converters.AbstractEnumeratable;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <pre>
 * 티켓 TICKET("TIC"), 
 * 정상 NORMAL("NML"), 
 * 휴면 DORMANCY("DOC"), 
 * 탈퇴 WITHDRAWAL("WTH"), 
 * 차단 BLOCK("BLK"), 
 * </pre>
 * 
 * @author warumono
 *
 */
@AllArgsConstructor
@Getter
public enum ProfileState implements AbstractEnumeratable<ProfileState>
{
	/**
	 * 정상: NML
	 */
	NORMAL("NML"), 
	
	/**
	 * 휴면: DOC
	 */
	DORMANCY("DOC"), 
	
	/**
	 * 탈퇴: WTH
	 */
	WITHDRAWAL("WTH"), 
	
	/**
	 * 차단: BLK
	 */
	BLOCK("BLK");
	
	private String dbData;
	
	@Override
	public String getToDatabaseColumn(ProfileState attribute)
	{
		return dbData;
	}

	@Override
	public ProfileState getToEntityAttribute(String dbData)
	{
		return Arrays.stream(ProfileState.values()).filter(e -> e.getDbData().equals(dbData)).findFirst().orElseThrow(null);
	}
}
