package grame.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import grame.dao.PlayerDAO;
import grame.dao.PlayerDAOImpl;
import grame.dao.RectDAO;
import grame.dao.RectDAOImpl;
import grame.pojo.Player;

public class GrameBoardServlet extends HttpServlet{
	RectDAO rd = new RectDAOImpl();
	PlayerDAO pd = new PlayerDAOImpl();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		req.getRequestDispatcher("GramePicker.html").forward(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if(session!= null) {
			String tablename = req.getParameter("tablename");
			String op = req.getParameter("op");
			String username = (String) session.getAttribute("username");
			Player E = pd.getPlayerByUsername(username);
			if(op != null && op.equals("new")) {
				if(rd.newRectTable(tablename)) {
					session.setAttribute("table", tablename);
				}else {
					resp.sendRedirect("grameboard");
				}
			}
			session.setAttribute("table", tablename);
			pd.setPlayerLogin(E.getPlayerId(),tablename);
			resp.sendRedirect("grame");
			
		}
	}
}
