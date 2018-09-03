package grame.dao;

import java.util.List;

import grame.pojo.Player;

public interface PlayerDAO {
	public Player getPlayerById(int PlayerId);
	public List<Player> getManagedPlayers(int PlayerId);
	public Player getPlayerByLogin(String Username,String Password);
	public Player getPlayerByUsername(String username);
	public boolean newPlayer(String email, String username, String color, String password);
	public boolean updatePlayerName(int Playerid,String firstname, String lastname)  ;
	public boolean updatePlayerPassword(int Playerid, String password);
	public boolean updatePlayerEmail(int Playerid,String email);
	public Player getExpenseManager(int expenseid);
	public boolean updatePlayerTempPassword(int Playerid, int password);
	public Player getPlayerByTempLogin(String name, int Password);
	public boolean setPlayerLogin(int playerid,String val);
	public List<Player> getLoggedInPlayers(String tablename);
}
