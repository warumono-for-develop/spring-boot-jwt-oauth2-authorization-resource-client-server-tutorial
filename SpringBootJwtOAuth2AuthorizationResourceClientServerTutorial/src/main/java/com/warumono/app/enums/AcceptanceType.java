package com.warumono.app.enums;

import java.util.Arrays;

import com.warumono.app.enums.converters.AbstractEnumeratable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AcceptanceType implements AbstractEnumeratable<AcceptanceType>
{
	/**
	 * 이용약관: TER
	 */
	TERMS_OF_POLICY("TER"), 
	
	/**
	 * 개인정보취급방침: PRI
	 */
	PRIVACY_OF_POLICY("PRI"), 
	
	/**
	 * 14세 이상 확인: AGE
	 */
	AGE_VERIFICATION("AGE"), 
	
	/**
	 * 단문메시지 서비스: SMS
	 */
	SMS("SMS"), 
	
	/**
	 * 이메일 수신동의: EMA
	 */
	EMAIL("EMA"), 
	
	/**
	 * 푸쉬 알림: NOF
	 */
	NOTIFICATION("NOF");

	private String dbData;

	@Override
	public String getToDatabaseColumn(AcceptanceType attribute)
	{
		return dbData;
	}

	@Override
	public AcceptanceType getToEntityAttribute(String dbData)
	{
		return Arrays.stream(AcceptanceType.values()).filter(e -> e.getDbData().equals(dbData)).findFirst().orElseThrow(null);
	}
}
