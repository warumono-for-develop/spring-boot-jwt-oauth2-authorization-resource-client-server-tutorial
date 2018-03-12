package com.warumono.app.enums;

import java.util.Arrays;

import com.warumono.app.enums.converters.AbstractEnumeratable;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <pre>
 * 완료 COMPLETE("COMPLETE"),
 * 미완료 INCOMPLETE("INC"),
 * 진행중 PROCEEDING("PRO"),
 * 에러 ERR("ERR"),
 * </pre>
 * 
 * @author may
 *
 */
@AllArgsConstructor
@Getter
public enum CertificateState implements AbstractEnumeratable<CertificateState>
{
	/**
	 * 진행중: PRO
	 */
	PROCEEDING("PRO"), 

	/**
	 * 미완료(시간초과): INC
	 */
	INCOMPLETE("INC"), 
	
	/**
	 * 완료: CMP
	 */
	COMPLETE("CMP"), 

	/**
	 * 에러: ERR
	 */
	ERR("ERR");

	private String dbData;

	@Override
	public String getToDatabaseColumn(CertificateState attribute)
	{
		return dbData;
	}

	@Override
	public CertificateState getToEntityAttribute(String dbData)
	{
		return Arrays.stream(CertificateState.values()).filter(e -> e.getDbData().equals(dbData)).findFirst().orElseThrow(null);
	}
}
