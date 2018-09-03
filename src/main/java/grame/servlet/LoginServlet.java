package grame.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import grame.dao.PlayerDAO;
import grame.dao.PlayerDAOImpl;
import grame.pojo.Player;
import grame.service.AdminService;
import grame.service.AuthenticationService;
import grame.service.EmailService;



public class LoginServlet extends HttpServlet{
	PlayerDAO ed = new PlayerDAOImpl();
	/**
	 * 
	 */
	EmailService Em = new EmailService();
	private static final long serialVersionUID = 817105812389880890L;
	
	//return login page for GET request
	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		req.getRequestDispatcher("Login.html").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("email").toLowerCase();
		String password = req.getParameter("password");
		String tablename = req.getParameter("tablename");
		PlayerDAO pd = new PlayerDAOImpl();
		Player E = AuthenticationService.isValidUser(username,password);
		if(E != null) {
			//if(E.getTempPass() == 0) {
				HttpSession session = req.getSession(true);
				session.setAttribute("username", E.getUsername());
				resp.sendRedirect("grameboard");
			//}else {
				//resp.sendRedirect("login");
			//}
		}
		else {
			int temppass = -1;
			try {
				temppass = Integer.parseInt(password);
				E = ed.getPlayerByTempLogin(username, temppass);
				if(E!=null) {
					HttpSession session = req.getSession(true);
					session.setAttribute("username", username);
					resp.sendRedirect("PasswordUpdate.html");
				}else {
					resp.sendRedirect("register");
				}
			}catch(NumberFormatException e) {
				resp.sendRedirect("register");
			}
			
		}
		
	}	

}
