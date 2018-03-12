package com.warumono.app.enums;

import java.util.Arrays;

import com.warumono.app.enums.converters.AbstractEnumeratable;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <pre>
 * 카카오톡 KAKAO("KKO"), 
 * 페이스북 FACEBOOK("FCB"), 
 * 네이버 NAVER("NVR"), 
 * 체리피카 CHERRYPICA("CRY"), 
 * </pre>
 * 
 * @author warumono
 *
 */
@AllArgsConstructor
@Getter
public enum Provider implements AbstractEnumeratable<Provider>
{
	/**
	 * 카카오톡: KKO
	 */
	KAKAO("KKO"), 
	
	/**
	 * 페이스북: FCB
	 */
	FACEBOOK("FCB"), 
	
	/**
	 * 네이버: NVR
	 */
	NAVER("NVR"), 
	
	/**
	 * 체리피카: CRY
	 */
	CHERRYPICA("CRY");
	
	private String dbData;

	@Override
	public String getToDatabaseColumn(Provider attribute)
	{
		return dbData;
	}

	@Override
	public Provider getToEntityAttribute(String dbData)
	{
		return Arrays.stream(Provider.values()).filter(e -> e.getDbData().equals(dbData)).findFirst().orElseThrow(null);
	}
}
