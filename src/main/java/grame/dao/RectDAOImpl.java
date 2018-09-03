package grame.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import grame.pojo.Rect;
import grame.util.ConnectionUtil;

public class RectDAOImpl implements RectDAO {
	private static String filename = "connection.properties";
	public Rect getRectById(int rectId,String tablename) {
		try(Connection con = ConnectionUtil.getConnectionFromFile(filename)){
			String sql = "SELECT * FROM " +tablename + " WHERE RECT_ID = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rectId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				int x = rs.getInt("X");
				int y = rs.getInt("Y");
				int width = rs.getInt("WIDTH");
				int height = rs.getInt("HEIGHT");
				return new Rect(x,y,width,height,rectId);
			}
			else {
				System.out.println("no rect with that id");
			}
		}catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean newRect(int x,int y, int width, int height,String tablename) {
		try(Connection con = ConnectionUtil.getConnectionFromFile(filename)){
			String sql = "INSERT INTO " + tablename + "(X,Y,WIDTH,HEIGHT) VALUES (?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, x);
			pstmt.setInt(2, y);
			pstmt.setInt(3, width);
			pstmt.setInt(4, height);
			if(pstmt.executeUpdate()>0) {
				return true;
			}
		}catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean newRect(Rect r,String tablename) {
		try(Connection con = ConnectionUtil.getConnectionFromFile(filename)){
			String sql = "INSERT INTO " + tablename + "(X,Y,WIDTH,HEIGHT) VALUES (?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, r.getX());
			pstmt.setInt(2, r.getY());
			pstmt.setInt(3, r.getWidth());
			pstmt.setInt(4, r.getHeight());
			if(pstmt.executeUpdate()>0) {
				return true;
			}
		}catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	public List<Rect> getBoundingRects(Rect r,String tablename) {
		try(Connection con = ConnectionUtil.getConnectionFromFile(filename)){
			String sql = "SELECT * FROM " + tablename + " WHERE X < ? && Y < ? && WIDTH < ? AND HEIGHT < ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, r.getX());
			pstmt.setInt(2, r.getY());
			pstmt.setInt(3, r.getWidth());
			pstmt.setInt(4, r.getHeight());
			ResultSet rs = pstmt.executeQuery();
			ArrayList<Rect> Rl = new ArrayList<Rect>();
			while(rs.next()) {
				int x = rs.getInt("X");
				int y = rs.getInt("Y");
				int width = rs.getInt("WIDTH");
				int height = rs.getInt("HEIGHT");
				int rectId = rs.getInt("RECT_ID");
				Rl.add(new Rect(x,y,width,height,rectId));
			}
			return Rl;
		}catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Rect getRectByParam(int x, int y, int width, int height,String tablename) {
		try(Connection con = ConnectionUtil.getConnectionFromFile(filename)){
			String sql = "SELECT * FROM " + tablename + " WHERE X = ? AND Y = ? AND WIDTH = ? AND HEIGHT = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, x);
			pstmt.setInt(2, y);
			pstmt.setInt(3, width);
			pstmt.setInt(4, height);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				int rectId = rs.getInt("RECT_ID");
				return new Rect(x,y,width,height,rectId);
			}
			else {
				System.out.println("no rect with that id");
			}
		}catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public boolean clearRect(String tablename) {
		try(Connection con = ConnectionUtil.getConnectionFromFile(filename)){
			String sql = "DELETE FROM " + tablename;
			PreparedStatement pstmt = con.prepareStatement(sql);
			System.out.println(tablename);
			//pstmt.setString(1, tablename);
			if(pstmt.execute()) {
				return true;
			}
		}catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean newRectTable(String tablename) {
		try(Connection con = ConnectionUtil.getConnectionFromFile(filename)){
			String tsql = "SELECT * FROM RECT_SPACE WHERE RECT_SPACE_ID = ?";
			PreparedStatement tpstmt = con.prepareStatement(tsql);
			tpstmt.setString(1, tablename);
			ResultSet rs = tpstmt.executeQuery();
			if(rs.next()) {
				System.out.println("table already exists");
				return false;
			}else {
				String sql = "CREATE TABLE " + tablename + "(RECT_ID INTEGER PRIMARY KEY,X INTEGER NOT NULL, Y INTEGER NOT NULL, WIDTH INTEGER NOT NULL, HEIGHT INTEGER NOT NULL)";
				PreparedStatement pstmt = con.prepareStatement(sql);
				System.out.println(tablename);
				//pstmt.setString(1, tablename);
				pstmt.execute();
				String sql2 = "INSERT INTO RECT_SPACE VALUES (?)";
				PreparedStatement pstmt2 = con.prepareStatement(sql2);
				System.out.println("new table in rect space");
				pstmt2.setString(1, tablename);
				if(pstmt2.executeUpdate()>0) {
					return true;
				}else {return false;}
				
			}
		}catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
