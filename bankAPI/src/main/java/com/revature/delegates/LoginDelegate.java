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



/* 	End points
 * 
 *	/login POST log in user
 * 
 */
public class LoginDelegate implements FrontControllerDelegate {
	private UserService uServ = new UserService(new UserPostGres(), new RolePostGres(), new AccountPostGres());
	
	private ObjectMapper om = new ObjectMapper();
	
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		if("POST".equals(req.getMethod())) {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			
			User u = uServ.findUserbyName(username);
			
			if(u != null) {
				if(u.getPassword().equals(password)) {
					req.getSession().setAttribute("user", u);
					resp.getWriter().write(om.writeValueAsString(u));
				}
				else {
					resp.sendError(400, "Invalid Credentials");
				}
			}
			else {
				resp.sendError(400, "Invalid Credentials");
			}
		}
		else {
			resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		}
		
	}
	
	
}
