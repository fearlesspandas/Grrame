package grame.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import grame.dao.RectDAO;
import grame.dao.RectDAOImpl;

public class RectServlet extends HttpServlet {
	RectDAO rd = new RectDAOImpl();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//rd.newRectTable("RECT2");
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if(session!= null) {
			String tablename = (String)session.getAttribute("tablename");
			String op = req.getParameter("op");
			switch(op) {
				case "new": 
					int x = Integer.parseInt(req.getParameter("x"));
					int y = Integer.parseInt(req.getParameter("y"));
					int width = Integer.parseInt(req.getParameter("width"));
					int height = Integer.parseInt(req.getParameter("height"));
					rd.newRect(x, y, width, height,tablename);
					break;
				
			}
		}
			
	}
}
