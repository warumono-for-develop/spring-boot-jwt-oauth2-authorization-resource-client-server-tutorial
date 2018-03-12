package com.warumono.app.enums;

import java.util.Arrays;

import com.warumono.app.enums.converters.AbstractEnumeratable;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <pre>
 * 이메일 EMAIL("EMA"),
 * 계좌번호 ACCOUNT("ACC"),
 * 비밀번호 재설정 ASSWORD_RESET("SET"),
 * </pre>
 * 
 * @author may
 *
 */
@AllArgsConstructor
@Getter
public enum CertificateType implements AbstractEnumeratable<CertificateType>
{
	/**
	 * 이메일: EMA
	 */
	EMAIL("EMA"), 
	
	/**
	 * 계좌번호: ACC
	 */
	ACCOUNT("ACC"), 
	
	/**
	 * 비밀번호 재설정: PWD
	 */
	PASSWORD_RESET("PWD");
	
	private String dbData;

	@Override
	public String getToDatabaseColumn(CertificateType attribute)
	{
		return dbData;
	}

	@Override
	public CertificateType getToEntityAttribute(String dbData)
	{
		return Arrays.stream(CertificateType.values()).filter(e -> e.getDbData().equals(dbData)).findFirst().orElseThrow(null);
	}
}
