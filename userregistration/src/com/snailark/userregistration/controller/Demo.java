package com.snailark.userregistration.controller;

public class Demo {

	public static void main(String[] args) {
		int[] a = {11,22}; 
		fun(a);
		System.out.println(a[0] +" "+ a[1]);
		

	}
	
	public static void  fun(int[] a)
	{
		a[1] = 20;
	}

}
