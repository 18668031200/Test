package com.ygdxd.utils.String;

public class RandomData {
	
	public static String getSMSverification(){
		StringBuilder code=new StringBuilder();
		for(int i=0;i<6;i++){
			code.append(String.valueOf((int)(Math.random()*10)));
		}
		return code.toString();
	}

}
