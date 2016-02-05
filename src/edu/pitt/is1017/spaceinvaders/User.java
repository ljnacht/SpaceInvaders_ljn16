package edu.pitt.is1017.spaceinvaders;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class User {
	private int userID;
	private String lastName;
	private String firstName;
	private String email;
	private String password;
	private boolean loggedIn;
	private DbUtilities db;
	
	public User(int userID){
		db = new DbUtilities();
		String sql = "UPDATE users";
		sql = sql + " SET lastName='"+ lastName +"', firstName='"+ firstName +"',";
		sql = sql + " email='"+ email +"', password=md5('"+ password +"')";
		sql = sql + " WHERE userID='"+ userID +"'";
		db.executeQuery(sql);
		showMsg("Your information was updated.");
		db.closeConnection();
	}
	public User(String email, String password){
		db = new DbUtilities();
		String sql = "SELECT COUNT(*) FROM users ";
		sql = sql + " WHERE email= '"+ email +"' AND password=md5('"+ password +"');";
		
		ResultSet rs = db.getResultSet(sql);
		boolean found= false;
		
		try {
			while(rs.next()){
				int count = rs.getInt(1);
				if(count > 0){
					found = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JFrame frame = new JFrame();
		if(found){
			JOptionPane.showMessageDialog(frame, "You are logged in!");
			loggedIn = true;
		}else{
			JOptionPane.showMessageDialog(frame, "Invalid login information.");
			loggedIn = false;
		}
		
		db.closeConnection();
	}
	
	public User(String lastname, String firstname, String email, String password){
		db = new DbUtilities();	
		String sql = "INSERT INTO alieninvasion.users ";
		sql = sql + "( lastname , firstname, email, password) ";
		sql = sql + "VALUES ";
		sql = sql + "('"+ lastName +"','"+ firstName +"','"+ email +"',md5('"+ password +"'));";
		
		db.executeQuery(sql);
		db.closeConnection();
	}
	
	public void saveUserInfo(){
		db = new DbUtilities();
		
		String sql = "UPDATE users";
		sql = sql + " SET lastName='"+ lastName +"', firstName='"+ firstName +"',";
		sql = sql + " email='"+ email +"', password=md5('"+ password +"')";
		sql = sql + " WHERE userID='"+ userID +"'";
		db.executeQuery(sql);
		showMsg("Your information was saved.");
		
		db.closeConnection();
	}
	
	public int getUserID(){
		return userID;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public void setLastName(String ln){
		lastName = ln;
	}

	public String getFirstName(){
		return firstName;
	}
	
	public void setFirstName(String fn){
		firstName = fn;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String e){
		email = e;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String pw){
		password = pw;
	}
	
	public void showMsg(String msg){
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, msg);
	}
}
