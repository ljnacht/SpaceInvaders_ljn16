package edu.pitt.is1017.spaceinvaders;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.BoxLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Login {
	private JFrame SpaceInvadersLogin;
	private JTextField tfLoginEmail;
	private JTextField tfLoginPassword;
	private JTextField tfRegisterFirstName;
	private JTextField tfRegisterLastName;
	private JTextField tfRegisterEmail;
	private JTextField tfRegisterPassword;
	private JTextField tfRegisterConfPassword;
	private DbUtilities db;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.SpaceInvadersLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public Login() {
		db = new DbUtilities();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		SpaceInvadersLogin = new JFrame();
		SpaceInvadersLogin.setTitle("Space Invaders - Login");
		SpaceInvadersLogin.setBounds(100, 100, 450, 300);
		SpaceInvadersLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		JPanel panelLogin = new JPanel();
		SpaceInvadersLogin.getContentPane().add(panelLogin, BorderLayout.CENTER);
		panelLogin.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelLogin.add(lblEmail, "4, 4, right, default");
		
		tfLoginEmail = new JTextField();
		panelLogin.add(tfLoginEmail, "6, 4, left, default");
		tfLoginEmail.setColumns(15);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelLogin.add(lblPassword, "4, 6, right, default");
		
		tfLoginPassword = new JTextField();
		tfLoginPassword.setColumns(15);
		panelLogin.add(tfLoginPassword, "6, 6, left, default");
		
		//REGISTER
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panelLogin.add(btnRegister, "4, 10");
		
		//LOGIN
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = new User(tfLoginEmail.getText(), tfLoginPassword.getText());

				if(user.ifLoggedIn()){
					Thread t = new Thread("Game Launch"){
						public void run(){
							Game g = new Game(user);
							g.gameLoop();
						}
					};
					
					t.start();
				}else{
					System.out.println("login fail");
				}
			}
		});
		panelLogin.add(btnLogin, "6, 10");
		
		//CANCEL
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SpaceInvadersLogin.dispose();
			}
		});
		panelLogin.add(btnCancel, "8, 10");
		
	}//end initialize
	
	private static boolean checkLogin(String email, String pw){
		DbUtilities db = new DbUtilities();
		//String sql = "SELECT userID FROM users";
		String sql = "SELECT COUNT(*) FROM users ";
		sql = sql + " WHERE email= '"+ email +"' AND password=md5('"+ pw +"');";
		
		ResultSet rs = db.getResultSet(sql);
		
		try {
			while(rs.next()){
				int count = rs.getInt(1);
				if(count == 0){
					return false;
				}else{
					return true;
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
