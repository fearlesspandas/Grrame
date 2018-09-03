package grame.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import grame.service.PlayerInfoService;
public class PlayerInfoServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		PrintWriter pw = resp.getWriter();
		HttpSession session = req.getSession(false);
		if(session != null) {
			String tablename = (String)session.getAttribute("table");
			pw.write(PlayerInfoService.getLoggedInPlayers(tablename));
		}
		
	}
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		doGet(req,resp);
	}

}
