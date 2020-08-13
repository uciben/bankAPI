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
	/register POST
	 * ADMIN ONLY ALLOWED
	 * USER ID should be 0, All fields included
	 * Response should have updated user id
*/
public class RegisterDelegate implements FrontControllerDelegate{
	private UserService uServ = new UserService(new UserPostGres(), new RolePostGres(), new AccountPostGres());
	
	private ObjectMapper om = new ObjectMapper();
	
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		if ("POST".equals(req.getMethod())) {
			User u = (User)req.getSession().getAttribute("user");
			
			//Checks if user is Admin
			if(u.getRole().getRole().equals("Admin")) {
				// Checks if user exists
				User newUser = (User) om.readValue(req.getInputStream(), User.class);
				User foundUser = uServ.findUserbyEmail(newUser.getEmail());
				User foundUser2 = uServ.findUserbyName(newUser.getUserName());
				if(foundUser == null && newUser.getId() == 0 && foundUser2 == null) {
			
					newUser.setID(uServ.registerUser(newUser));
					resp.getWriter().write(om.writeValueAsString(newUser));
					resp.setStatus(201);	
				}
				else {
					resp.sendError(400, "Invalid fields");
				}
			}
			else {
				resp.sendError(401, "The request action is not permitted");
			}
		}
			
		else {
			resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		}
	}
}
