package grame.service;

import grame.dao.PlayerDAO;
import grame.dao.PlayerDAOImpl;
import grame.pojo.Player;

public class AuthenticationService {
	public static Player isValidUser(String username, String password) {
		PlayerDAO ed = new PlayerDAOImpl();
		if( username != null) {
			return ed.getPlayerByLogin(username, password);
		}
		else {
			return null;
		}
	}
}
