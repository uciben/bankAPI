package com.revature.delegates;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.data.AccountPostGres;
import com.revature.data.RolePostGres;
import com.revature.data.UserPostGres;
import com.revature.humans.Role;
import com.revature.humans.User;
import com.revature.services.UserService;

/*
 * ENDPOINTS
 *	/users GET - ONLY EMPLOYEE OR ADMIN  return all users?
 *			PUT - ADMIN OR ONLY IF id matches, updates user
 *
 *	/users/:id
 *		GET - ONLY ADMIN OR EMPLOYEE, return user
 *
 */
public class UserDelegate implements FrontControllerDelegate{
	
	private UserService uServ = new UserService(new UserPostGres(), new RolePostGres(), new AccountPostGres());
	
	private ObjectMapper om = new ObjectMapper();
	
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = (String) req.getAttribute("path");
		
		if( path == null || path.equals("")) {
			switch(req.getMethod()) {
				case "PUT":
					// ADMIN or userID matches update user
					User updateUser = (User) om.readValue(req.getInputStream(), User.class);
					User currentUser = (User) req.getSession().getAttribute("user");
					
					if(currentUser.getRole().getRole().equals("Admin") ||
							updateUser.getId() == currentUser.getId()) {
						uServ.updateEmail(updateUser, updateUser.getEmail());
						uServ.updatePassword(updateUser, updateUser.getPassword());
						resp.getWriter().write(om.writeValueAsString(updateUser));
					}
					else {
						resp.sendError(401, "The request action is not permitted");
					}
					break;
				case "GET":
					// ADMIN OR EMPLOYEE ONLY RETURNS ALL USERS
					User currentUser2 = (User) req.getSession().getAttribute("user");

					if(currentUser2.getRole().getRole().equals("Admin") ||
							currentUser2.getRole().getRole().equals("Employee")){
						Set<User> allUsers = new HashSet<>();
						for(Role role: uServ.getRoles()) {
							allUsers.addAll(uServ.getUserbyRole(role));
						}
						resp.getWriter().write(om.writeValueAsString(allUsers));
					}
					else {
						resp.sendError(401, "The Request action is not permitted");
					}
					break;
				default:
					resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
					break;
			}
		}
		else {
			
			// ONLY ADMIN OR EMPLOYEE return user
			// /id
			User currentUser = (User) req.getSession().getAttribute("user");
			if(currentUser.getRole().getRole().equals("Admin") ||
				currentUser.getRole().getRole().equals("Employee")) {
					
				int userId = Integer.valueOf(path);
				User foundUser = null;
				
				switch(req.getMethod()) {
					case "GET":
						foundUser = uServ.findUserbyID(userId);
						if(foundUser != null){
							resp.getWriter().write(om.writeValueAsString(foundUser));
						}
						else {
							resp.sendError(HttpServletResponse.SC_NOT_FOUND);
						}
						break;
					default:
						resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
						break;
					}
				}
			else {
				resp.sendError(401, "The request action is not permitted");
				}
			}
		
		}

}
