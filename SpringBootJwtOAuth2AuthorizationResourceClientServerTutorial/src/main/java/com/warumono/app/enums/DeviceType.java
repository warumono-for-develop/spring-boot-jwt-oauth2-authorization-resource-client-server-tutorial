package com.warumono.app.enums;

import java.util.Arrays;

import com.warumono.app.enums.converters.AbstractEnumeratable;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <pre>
 * iOS IOS("IOS"), 
 * 안드로이드 ANDROID("ADR"),
 * </pre>
 * 
 * @author warumono
 *
 */
@AllArgsConstructor
@Getter
public enum DeviceType implements AbstractEnumeratable<DeviceType>
{
	/**
	 * iOS: IOS
	 */
	IOS("IOS"),

	/**
	 * 안드로이드: ADR
	 */
	ANDROID("ADR");

	private String dbData;

	@Override
	public String getToDatabaseColumn(DeviceType attribute)
	{
		return dbData;
	}

	@Override
	public DeviceType getToEntityAttribute(String dbData)
	{
		return Arrays.stream(DeviceType.values()).filter(e -> e.getDbData().equals(dbData)).findFirst().orElseThrow(null);
	}
}
