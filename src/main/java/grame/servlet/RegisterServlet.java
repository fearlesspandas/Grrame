package grame.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import grame.dao.PlayerDAO;
import grame.dao.PlayerDAOImpl;
import grame.pojo.Player;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("Register.html").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PlayerDAO ed = new PlayerDAOImpl();
		HttpSession session = request.getSession(true);	
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String color = request.getParameter("color");
		String pass = request.getParameter("password");
		if(ed.newPlayer(email,username,color,pass)) {
				session.setAttribute("username", username);
				response.sendRedirect("login");
			}
		else {
			response.sendRedirect("register");
		}
		
	}

}
