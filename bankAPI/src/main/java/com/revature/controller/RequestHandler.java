package com.revature.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.delegates.AccountDelegate;
import com.revature.delegates.FrontControllerDelegate;
import com.revature.delegates.LoginDelegate;
import com.revature.delegates.LogoutDelegate;
import com.revature.delegates.RegisterDelegate;
import com.revature.delegates.UserDelegate;

public class RequestHandler {
	private Map<String, FrontControllerDelegate> delegateMap;
	
	{
			delegateMap = new HashMap<String, FrontControllerDelegate>();
			
			delegateMap.put("accounts", new AccountDelegate());
			delegateMap.put("users", new UserDelegate());
			delegateMap.put("login", new LoginDelegate());
			delegateMap.put("logout", new LogoutDelegate());
			delegateMap.put("register", new RegisterDelegate());
			
	}
	
	public FrontControllerDelegate handle(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{
		// If request is OPTIONS returns empty delegate;
		if("OPTIONS".equals(req.getMethod())) {
			return (r1, r2) -> {};
		}
		
		StringBuilder uriString = new StringBuilder(req.getRequestURI());
		
		// localhost:8080/CatApp/cat/4
		
		uriString.replace(0, req.getContextPath().length()+ 1, "");
		
		// cat/4
		if(uriString.indexOf("/") != -1) {
			req.setAttribute("path", uriString.substring( uriString.indexOf("/")+ 1) );
			uriString.replace(uriString.indexOf("/"), uriString.length(), "");
		}
		// request -> path -> 4

		// uriString = cat
		
		return delegateMap.get(uriString.toString());
		
	}
	
	
}
