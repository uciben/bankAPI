package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.delegates.FrontControllerDelegate;

public class FrontController extends HttpServlet {
	private RequestHandler rh = new RequestHandler();
	
	//this is where we funnel all our requests
	//to the handler to get back the appropriate delegate 
	//then call delegate
	private void process(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		FrontControllerDelegate fcd = rh.handle(req, resp);
		
		if(fcd != null)
			fcd.process(req,resp);
		else
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		process(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		process(request, response);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		process(request, response);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		process(request, response);
	}

}
