package com.bridgelabz.copier;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Converter {

	public <T, U> U convert(T dto, Class<U> clazz) {

		Class<?> dtoClazz = dto.getClass();

		Field[] fields = dtoClazz.getDeclaredFields();

		U object = null;

		try {
			object = clazz.getConstructor().newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		}

		for (Field field : fields) {
			try {
				Field objectField = clazz.getDeclaredField(field.getName());
				objectField.setAccessible(true);
				field.setAccessible(true);
				objectField.set(object, field.get(dto));
			} catch (NoSuchFieldException e) {
				
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		return object;
	}

}
