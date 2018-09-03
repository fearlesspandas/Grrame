package grame.pojo;

public class Player {
	private int PlayerId;
	private String Email;
	private String username;
	private String color;
	private int password;
	private int tempPass;
	public Player(int PlayerId,String Email, String username, String color, int password){
		this.setPlayerId(PlayerId); 
		this.setEmail(Email);
		this.setPassword(password);
		this.setTempPass(0);
		this.setColor(color);
		this.setUsername(username);
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getPassword() {
		return password;
	}
	public void setPassword(int password) {
		this.password = password;
	}
	public int getTempPass() {
		return tempPass;
	}
	public void setTempPass(int tempPass) {
		this.tempPass = tempPass;
	}
	public int getPlayerId() {
		return PlayerId;
	}
	public void setPlayerId(int playerId) {
		PlayerId = playerId;
	}
	
	
	

}
