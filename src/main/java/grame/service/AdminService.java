package grame.service;

public class AdminService {

	public static boolean getEmployees(String username) { // change return type to Employee[]
		if(username != null) {
			//JDBCquery for employee with username
			return true;
		}
		else {
			return false;
		}
	}
	public AdminService() {
		// TODO Auto-generated constructor stub
	}

}
