package grame.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import grame.dao.PlayerDAO;
import grame.dao.PlayerDAOImpl;
import grame.pojo.Player;

public class PlayerInfoService {
	private static PlayerDAO pd = new PlayerDAOImpl();
	private static ObjectMapper om = new ObjectMapper();
	public static String getLoggedInPlayers(String tablename) throws JsonProcessingException {	
		List<Player> Pl = pd.getLoggedInPlayers(tablename);
		return om.writeValueAsString(Pl);
	}
}
