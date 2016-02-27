package edu.pitt.is1017.spaceinvaders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ScoreTracker {
	private User user;
	String gameID;
	private int currentScore;
	private int highestScore;
	private DbUtilities db;

	public ScoreTracker(User u){		
		gameID = UUID.randomUUID().toString();
		user = u;
		currentScore = 0;
		
		db = new DbUtilities();
		
		//find highest score
		String sql = "SELECT MAX(scoreValue) FROM finalscores ";
		sql = sql + "JOIN users ON fk_userID = userID where userID ="+ user.getUserID() +";";
		
		ResultSet rs = db.getResultSet(sql);
		try{
			while(rs.next()){
				highestScore = rs.getInt("MAX(scoreValue)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void recordScore(int point){
		if(point == 1 || point == -1){
			currentScore += point;
			
			String sql = "UPDATE runningscores ";
			sql = sql + "SET scoreValue =" + currentScore + " ";
			sql = sql + "WHERE gameID = '"+ gameID +"' AND fk_userID ="+ user.getUserID() +";";
			
			db.executeQuery(sql);
		}else{
			System.out.println("Invalid point count.");
		}
	}
	
	public void recordFinalScore(){
		//record final score
		String sql = "INSERT INTO alieninvasion.finalscores (gameID, scoreValue, fk_userID, dateTimeEntered) ";
		sql = sql + "VALUES ( '"+ gameID +"' , "+ currentScore +" ,  "+ user.getUserID() +" , NOW());";
		db.executeQuery(sql);
		
		try {
			int userHighScore = 0;
			int leaderHighScore = 0;
			String leaderName="";
			
			//highest score for user
			sql = "SELECT scoreValue, gameID FROM finalscores WHERE fk_userID = " + user.getUserID();
			sql = sql + " AND gameID = '"+ gameID +"';";
			ResultSet rsUser = db.getResultSet(sql);
			
			//highest score for leader
			sql = "SELECT lastName, firstName, MAX(scoreValue) FROM finalscores JOIN users ON fk_userID = userID ";
			sql = sql + "GROUP BY lastName, firstName ORDER BY MAX(scoreValue) DESC LIMIT 1;";
			
			 
			ResultSet rsLeader = db.getResultSet(sql);
			
			while(rsUser.next()){
				userHighScore = rsUser.getInt("scoreValue");
			}
			while(rsLeader.next()){
				leaderHighScore = rsLeader.getInt(3);
				leaderName = rsLeader.getString("firstName");
				leaderName = leaderName + " " + rsLeader.getString("lastName");
			}
			
			//show end game msg
			String msg = "Your Highest Score: " + userHighScore + "\n";
			msg = msg + "Leader " + leaderName +"'s Highest Score: " + leaderHighScore;
			
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, msg);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public int getCurrentScore(){
		return currentScore;
	}
	
	public int getHighestScore(){
		return highestScore;
	}
	
}
