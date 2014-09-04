package com.snailark.userregistration.util;

public class ValidationHelper {

	public static boolean nameValidation(String uiname){
		if(uiname == null){
			return false;
		}
		char name[]=uiname.toCharArray();
		if(name.length == 0){
			return false;
		}
		for(int i = 0; i < name.length ; i++){
			if(!((name[i] >= 'a' && name[i] <= 'z')	
					|| (name[i] >= 'A' && name[i] <= 'Z'))){
				return false;
			}	
		}					
		return true;
	}
	
	public static boolean phoneNumberValidation(String uiPhoneNumber){
		char phonenumber[] = uiPhoneNumber.toCharArray();
		if(!(phonenumber.length==10)){
			return false;
		}
		for(int i = 0 ; i < phonenumber.length ; i++){
			if(!( (int) phonenumber[i] >= 48 && (int) phonenumber[i] <= 57)){
					return false;
			}	
		}
		return true;
	}
	
	public static boolean dateOfBirthValidation(String date, String month, String year){
		if(date == null || "DD".equals(date) || "".equals(date) 
				|| month == null || "MM".equals(month) || "".equals(month) 
				|| year == null || "YYYY".equals(year) || "".equals(year)) {

			
			return false;
		} else {
			return true;
		}
	}
}
