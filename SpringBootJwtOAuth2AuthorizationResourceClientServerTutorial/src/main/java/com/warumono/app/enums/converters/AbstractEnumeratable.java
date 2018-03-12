package com.warumono.app.enums.converters;

public interface AbstractEnumeratable<E>
{
	String getToDatabaseColumn(E attribute);
	E getToEntityAttribute(String dbData);
}
