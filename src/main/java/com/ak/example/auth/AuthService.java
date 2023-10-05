package com.ak.example.auth;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.hibernate.id.UUIDGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


@Component
public class AuthService {
	
	private static HashMap<String,List<String>> JWTTOKEN_PERMISSION=new HashMap<String, List<String>>();
	
	
	
	
	
	//
	
	//generate token
	//checkpermission of a token
	
	public static List<String> getJWTTOKEN_PERMISSION(String jwtToken) {
		return JWTTOKEN_PERMISSION.get(jwtToken);
	}





	public static void setJWTTOKEN_PERMISSION(String token , List<String> permission) {
		JWTTOKEN_PERMISSION.put(token, permission);
	}

	public static String setJWTTOKEN_PERMISSION(List<String> permission) {
		String jwttoken = generateJwtToken();
		JWTTOKEN_PERMISSION.put(jwttoken, permission);
		return jwttoken;
	}



	public static String generateJwtToken() {
		return UUID.randomUUID().toString();
	}

	
	
	
	//login and give DELETE,UPDATE permission
	public static String setPermission(List<String> permissions) {
		String jwttoken = generateJwtToken();
		JWTTOKEN_PERMISSION.put(jwttoken, permissions);
		return jwttoken;
	}
	
	
	public static boolean checkPermission(String token,String permission) {

	List<String> permissions=JWTTOKEN_PERMISSION.get(token);
	if(permissions==null) return false;
	if(permissions.size()<1)
       return false;
	
	return permissions.stream().filter(per->per.equalsIgnoreCase(permission)).count()>0;
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
