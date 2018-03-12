package com.warumono.app.enums;

import java.util.Arrays;
import java.util.Collection;

import com.google.common.collect.Sets;
import com.warumono.app.enums.converters.AbstractEnumeratable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PlatformApplication implements AbstractEnumeratable<PlatformApplication>
{
	APNS("APN", "iOS"), // Apple Push Notification Service
	
	APNS_SANDBOX("APS", "iOS_sandbox"), // Sandbox version of Apple Push Notification Service
	
	@Deprecated
	ADM("ADM", "amazon"), // Amazon Device Messaging
	
	FCM("FCM", "android"), // Google Cloud Messaging
	
	@Deprecated
	BAIDU("BDU", "baidu"), // Baidu CloudMessaging Service
	
	@Deprecated
	WNS("WIN", "windows"), // Windows Notification Service
	
	@Deprecated
	MPNS("MPN", "microsoft") // Microsoft Push Notificaion Service
	;
	
	public static final Collection<PlatformApplication> ENABLED = Sets.newHashSet(FCM);
	
	public static final DeviceType switchBy(PlatformApplication platform)
	{
		switch(platform)
		{
			case APNS: 
			case APNS_SANDBOX: return DeviceType.IOS;
			case FCM: return DeviceType.ANDROID;
			default: return null;
		}
	}
	
	private String dbData;
	private String name;

	@Override
	public String getToDatabaseColumn(PlatformApplication attribute)
	{
		return dbData;
	}

	@Override
	public PlatformApplication getToEntityAttribute(String dbData)
	{
		return Arrays.stream(PlatformApplication.values()).filter(e -> e.getDbData().equals(dbData)).findFirst().orElseThrow(null);
	}
}
