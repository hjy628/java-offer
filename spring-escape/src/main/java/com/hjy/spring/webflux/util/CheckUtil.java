package com.hjy.spring.webflux.util;

import com.hjy.spring.webflux.exceptions.CheckException;

import java.util.stream.Stream;

public class CheckUtil {

	private static final String[] INVALID_NAMES = { "admin", "guanliyuan" };

	/**
	 * 校验名字, 不成功抛出校验异常
	 * 
	 * @param name
	 */
	public static void checkName(String value) {
		Stream.of(INVALID_NAMES).filter(name -> name.equalsIgnoreCase(value))
				.findAny().ifPresent(name -> {
					throw new CheckException("name", value);
				});
	}

}
