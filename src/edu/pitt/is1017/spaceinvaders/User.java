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
		String sql = "SELECT * FROM users";
		sql = sql + " WHERE userID="+ userID +"";
	}
	
	public User(String email, String password){
		db = new DbUtilities();
		String sql = "SELECT COUNT(*), userID FROM users ";
		sql = sql + " WHERE email= '"+ email +"' AND password=md5('"+ password +"');";
		ResultSet rsLogIn = db.getResultSet(sql);
		
		boolean found= false;
		try {
			while(rsLogIn.next()){
				userID = rsLogIn.getInt("userID");
				//check if login info is correct/exists
				int count = rsLogIn.getInt("COUNT(*)");
				if(count > 0){
					found = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JFrame frame = new JFrame();
		if(found){
			//login successful, start game
			loggedIn = true;
		}else{
			//login failed, show msg
			JOptionPane.showMessageDialog(frame, "Invalid login information.");
			loggedIn = false;
		}
	}
	
	public User(String last, String first, String em, String pw){
		db = new DbUtilities();	
		String sql = "INSERT INTO alieninvasion.users ";
		sql = sql + "( lastName , firstName, email, password) ";
		sql = sql + "VALUES ";
		sql = sql + "('"+ last +"','"+ first +"','"+ em +"',md5('"+ pw +"'));";
		db.executeQuery(sql);
		
		lastName = last;
		firstName = first;
		email = em;
		password = pw;
	}
	
	public void saveUserInfo(){
		db = new DbUtilities();
		
		String sql = "UPDATE users";
		sql = sql + " SET lastName='"+ lastName +"', firstName='"+ firstName +"',";
		sql = sql + " email='"+ email +"', password=md5('"+ password +"')";
		sql = sql + " WHERE userID='"+ userID +"'";
		db.executeQuery(sql);
		showMsg("Your information was saved.");
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
	
	public boolean ifLoggedIn(){
		return loggedIn;
	}
	
	public void setPassword(String pw){
		password = pw;
	}
	
	
	
	public void showMsg(String msg){
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, msg);
	}
}
