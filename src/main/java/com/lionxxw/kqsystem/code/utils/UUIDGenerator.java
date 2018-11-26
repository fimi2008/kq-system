package com.lionxxw.kqsystem.code.utils;

import java.util.UUID;

/**
 * UUID生成器
 */
public abstract class UUIDGenerator {

	public static String getUUID(){
		return UUID.randomUUID().toString();
	}
	
	public static String getUUID32Bit(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
}
