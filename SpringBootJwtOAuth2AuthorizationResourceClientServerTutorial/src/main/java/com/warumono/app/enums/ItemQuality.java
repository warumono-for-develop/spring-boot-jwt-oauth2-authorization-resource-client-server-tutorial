package com.warumono.app.enums;

import java.util.Arrays;

import com.warumono.app.enums.converters.AbstractEnumeratable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ItemQuality implements AbstractEnumeratable<ItemQuality>
{
	/**
	 * 신상품: N
	 */
	N("N"), 
	
	/**
	 * 미사용품: NS
	 */
	NS("NS"), 
	
	/**
	 * 1,2회 사용하고 흠집없는 거의 새 중고품: S
	 */
	S("S"), 
	
	/**
	 * 상태가 상당히 좋은 중고품: A
	 */
	A("A"), 
	
	/**
	 * 상태가 좋은 중고품: AB
	 */
	AB("AB"), 
	
	/**
	 * 사용한 흔적이 있는 중고품: B
	 */
	B("B"), 
	
	/**
	 * 사용한 흔적이 꽤 보이는 중고품: C
	 */
	C("C"), 
	
	/**
	 * 사용에 어려움이 있는 중고품: D
	 */
	D("D");
	
	private String dbData;

	@Override
	public String getToDatabaseColumn(ItemQuality attribute)
	{
		return dbData;
	}

	@Override
	public ItemQuality getToEntityAttribute(String dbData)
	{
		return Arrays.stream(ItemQuality.values()).filter(e -> e.getDbData().equals(dbData)).findFirst().orElseThrow(null);
	}
}
