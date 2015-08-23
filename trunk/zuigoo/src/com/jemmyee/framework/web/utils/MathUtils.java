package com.jemmyee.framework.web.utils;

import java.util.Random;

public class MathUtils {
       
	public static Double getRandomDouble()
	{
		Random random=new Random();
		return random.nextDouble();
	}
	
	public static void main(String[] args) {
		System.out.println(getRandomDouble());
	}
}
