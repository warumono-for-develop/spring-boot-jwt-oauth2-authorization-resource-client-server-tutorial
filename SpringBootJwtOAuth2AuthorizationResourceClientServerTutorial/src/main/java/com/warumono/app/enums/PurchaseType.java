package com.warumono.app.enums;

import java.util.Arrays;

import com.warumono.app.enums.converters.AbstractEnumeratable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PurchaseType implements AbstractEnumeratable<PurchaseType>
{
	/**
	 * 백화점: DEP
	 */
	DEPARTMENT_STORE("DEP"), 
	
	/**
	 * 면세점: DFR
	 */
	DURY_FREE_SHOP("DFR"), 

	/**
	 * 온라인 매장: ONL
	 */
	ONLINE_SHOP("ONL"), 

	/**
	 * 오프라인 매장: OFL
	 */
	OFFFLINE_SHOP("OFL"), 
	
	/**
	 * 선물: GFT
	 */
	GIFT("GFT"), 
	
	/**
	 * 기타: ETC
	 */
	ETC("ETC");

	private String dbData;

	@Override
	public String getToDatabaseColumn(PurchaseType attribute)
	{
		return dbData;
	}

	@Override
	public PurchaseType getToEntityAttribute(String dbData)
	{
		return Arrays.stream(PurchaseType.values()).filter(e -> e.getDbData().equals(dbData)).findFirst().orElseThrow(null);
	}
}
