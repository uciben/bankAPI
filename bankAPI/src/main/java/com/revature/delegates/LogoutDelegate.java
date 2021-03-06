package com.revature.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.data.AccountPostGres;
import com.revature.data.RolePostGres;
import com.revature.data.UserPostGres;
import com.revature.humans.User;
import com.revature.services.UserService;

/* ENDPOINTS
	/logout POST
*/
public class LogoutDelegate implements FrontControllerDelegate{
	//private UserService uServ = new UserService(new UserPostGres(), new RolePostGres(), new AccountPostGres());
	
	//private ObjectMapper om = new ObjectMapper();
	
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		if ("POST".equals(req.getMethod())) {
			if(req.getSession().getAttribute("user") == null) {
				resp.sendError(400, "There was no user logged into the session");
			}
			else {
				User person = (User) req.getSession().getAttribute("user");
				resp.getWriter().write("You have sucessfully logged out "+person.getUserName());
				resp.setStatus(200);
				req.getSession().invalidate();
			}
		}
		else {
			resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		}
	}
}
