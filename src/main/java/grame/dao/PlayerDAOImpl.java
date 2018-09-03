package grame.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import grame.pojo.Player;
import grame.util.ConnectionUtil;

public class PlayerDAOImpl implements PlayerDAO {
	String filename = "connection.properties";
	public PlayerDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Player getPlayerById(int PlayerId) { 
		Player E = null;
		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
	        String sql = "SELECT * FROM PLAYER WHERE PLAYER_ID = ?";
	        PreparedStatement stmt = con.prepareStatement(sql);
	        stmt.setInt(1, PlayerId);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	        	int id = rs.getInt("Player_ID");
                String email = rs.getString("EMAIL");
                String fname = rs.getString("PLAYER_NAME");
                String lname = rs.getString("COLOR");
                int password = rs.getInt("PASSWORD");
                E = new Player(id, email,fname,lname, password);
	        } else {
	            System.out.println("No Players with that ID");
	        }
	        con.close();
	        return E;
	    } catch (SQLException | IOException e) {
	        e.printStackTrace();
	    }
	    
	    return null;
}

	@Override
	public List<Player> getManagedPlayers(int PlayerId) {
		ArrayList<Player> el = new ArrayList<Player>();
		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {	
            //using a Statement--beware injection
            String sql = "SELECT * FROM Player WHERE MANAGER_ID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, PlayerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	int id = rs.getInt("Player_ID");
                String email = rs.getString("EMAIL");
                String fname = rs.getString("FIRST_NAME");
                String lname = rs.getString("LAST_NAME");
                int password = rs.getInt("PASSWORD");
                int manager_id = rs.getInt("MANAGER_ID");
                el.add(new Player(id, email,fname,lname, password));
            }
            con.close();
            return el;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        
        return null;
	}
	public boolean newPlayer(String email, String username, String color, String password) {
        try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
            String sql = "INSERT INTO Player(EMAIL,PLAYER_NAME,COLOR,PASSWORD) VALUES (?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, username);
            pstmt.setString(3, color);
            pstmt.setInt(4, password.hashCode());
            if(pstmt.executeUpdate()>0) {
            	return true;
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

	@Override
	public Player getPlayerByLogin(String name, String Password) {
		Player E = null;
		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {	
            String sql = "SELECT * FROM PLAYER WHERE EMAIL = ? AND PASSWORD = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setInt(2, Password.hashCode());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	int id = rs.getInt("PLAYER_ID");
                String email = rs.getString("EMAIL");
                String fname = rs.getString("PLAYER_NAME");
                String lname = rs.getString("COLOR");
                int password = rs.getInt("PASSWORD");
                //int temppass = rs.getInt("TEMP_PASSWORD");
                E = new Player(id, email,fname,lname, password);
                //if(temppass != 0) {
                	//E.setTempPass(temppass);
                //}
            } else {
                System.out.println("No Players with that username/password combo");
            }
            con.close();
            return E;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        
        return null;
        
    }
	public Player getPlayerByTempLogin(String name, int Password) {
		Player E = null;
		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {	
            //using a Statement--beware injection
            String sql = "SELECT * FROM Player WHERE EMAIL = ? AND TEMP_PASSWORD = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setInt(2, Password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	int id = rs.getInt("Player_ID");
                String email = rs.getString("EMAIL");
                String fname = rs.getString("FIRST_NAME");
                String lname = rs.getString("LAST_NAME");
                int password = rs.getInt("PASSWORD");
                int manager_id = rs.getInt("MANAGER_ID");
                int temppass = rs.getInt("TEMP_PASSWORD");
                E = new Player(id, email,fname,lname, password);
                if(temppass != 0) {
                	E.setTempPass(temppass);
                }
            } else {
                System.out.println("No Players with that username/password combo");
            }
            con.close();
            return E;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        
        return null;
        
    }

	@Override
	public Player getPlayerByUsername(String pname) {
		Player E = null;
		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {	
            //using a Statement--beware injection
            String sql = "SELECT * FROM Player WHERE PLAYER_NAME = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, pname);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("Player_ID");
                String email = "";//rs.getString("EMAIL");
                String username = rs.getString("PLAYER_NAME");
                String color = rs.getString("COLOR");
                int password = rs.getInt("PASSWORD");
                E = new Player(id, email,username,color, password);
                //if(temppass != 0) {
                	//E.setTempPass(temppass);
                //}
            } else {
                System.out.println("No Players with that username");
            }
            con.close();
            return E;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        
        return null;
	}
	public boolean updatePlayerName(int Playerid,String firstname, String lastname)  {
		Player E = null;
		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {	
            //using a Statement--beware injection
            String sql = "UPDATE Player SET FIRST_NAME = ?, LAST_NAME = ? WHERE Player_ID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, firstname);
            stmt.setString(2, lastname);
            stmt.setInt(3, Playerid);
            if (stmt.executeUpdate() > 0) {
                return true;
            } 
            con.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
		return false;
     
	}
	public boolean updatePlayerPassword(int Playerid, String password)  {
		Player E = null;
		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {	
            //using a Statement--beware injection
            String sql = "UPDATE Player SET PASSWORD = ? WHERE Player_ID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, password.hashCode());
            stmt.setInt(2, Playerid);
            if (stmt.executeUpdate() > 0) {
                return true;
            } 
            con.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
		return false;
     
	}
	public boolean updatePlayerTempPassword(int Playerid, int password)  {
		Player E = null;
		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {	
            //using a Statement--beware injection
            String sql = "UPDATE Player SET TEMP_PASSWORD = ? WHERE Player_ID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, password);
            stmt.setInt(2, Playerid);
            if (stmt.executeUpdate() > 0) {
                return true;
            } 
            con.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
		return false;
     
	}
	public boolean updatePlayerEmail(int Playerid,String email)  {
		Player E = null;
		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {	
            //using a Statement--beware injection
            String sql = "UPDATE Player SET EMAIL = ? WHERE Player_ID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,email.toLowerCase());
            stmt.setInt(2, Playerid);
            if (stmt.executeUpdate() > 0) {
                return true;
            } 
            con.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
		return false;
     
	}
    public Player getExpenseManager(int expenseid) {
    	Player E = null;
		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {	
            //using a Statement--beware injection
            String sql = "SELECT MANAGER_ID FROM Player WHERE Player_ID IN (SELECT Player_ID FROM EXPENSEREQ WHERE EXPENSE_ID = ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, expenseid);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("MANAGER_ID");
                E = getPlayerById(id);
            } else {
                System.out.println("No Expenses with that ID");
            }
            con.close();
            return E;
		}catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return E;
    }
    public boolean setPlayerLogin(int playerid,String val) {
    	try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
	    	String sql = "UPDATE PLAYER SET LOGGEDIN = ? WHERE PLAYER_ID = ?";
	    	PreparedStatement pstmt = con.prepareStatement(sql);
	    	pstmt.setInt(2, playerid);
	    	pstmt.setString(1,val);
	    	if(pstmt.executeUpdate()>0) {
	    		return true;
	    	}
    	}catch(SQLException|IOException e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    public List<Player> getLoggedInPlayers(String tablename) {
		ArrayList<Player> el = new ArrayList<Player>();
		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {	
            //using a Statement--beware injection
            String sql = "SELECT * FROM Player WHERE LOGGEDIN = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, tablename);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	int id = rs.getInt("Player_ID");
                String email = "";//rs.getString("EMAIL");
                String username = rs.getString("PLAYER_NAME");
                String color = rs.getString("COLOR");
                int password = rs.getInt("PASSWORD");
                Player P = new Player(id,email,username,color, password);
                P.setPassword(0);
                P.setTempPass(0);
                el.add(P);
            }
            con.close();
            return el;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        
        return null;
	}
		
	
}
