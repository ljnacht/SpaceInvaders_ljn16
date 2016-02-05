package edu.pitt.is1017.spaceinvaders;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class RegisterGUI {
	private JFrame SpaceInvadersRegister;
	private JTextField tfRegisterFirstName;
	private JTextField tfRegisterLastName;
	private JTextField tfRegisterEmail;
	private JTextField tfRegisterPassword;
	private JTextField tfRegisterConfPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI window = new RegisterGUI();
					window.SpaceInvadersRegister.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegisterGUI() {
		initialize();
	}
	private void initialize(){
		SpaceInvadersRegister = new JFrame();
		SpaceInvadersRegister.setTitle("Space Invaders - Register");
		SpaceInvadersRegister.setBounds(100, 100, 450, 300);
		SpaceInvadersRegister.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		SpaceInvadersRegister.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		//FIRST NAME
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblFirstName, "4, 4, right, default");
		
		tfRegisterFirstName = new JTextField();
		panel.add(tfRegisterFirstName, "6, 4, left, default");
		tfRegisterFirstName.setColumns(15);
		
		//LAST NAME
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblLastName, "4, 6, right, default");
		
		tfRegisterLastName = new JTextField();
		tfRegisterLastName.setColumns(15);
		panel.add(tfRegisterLastName, "6, 6, left, default");
		
		//EMAIL
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblEmail, "4, 8, right, default");
		
		tfRegisterEmail = new JTextField();
		tfRegisterEmail.setColumns(15);
		panel.add(tfRegisterEmail, "6, 8, left, default");
		
		//PASSWORD
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblPassword, "4, 10, right, default");
		
		tfRegisterPassword = new JTextField();
		tfRegisterPassword.setColumns(15);
		panel.add(tfRegisterPassword, "6, 10, left, default");
		
		//CONFIRM PASSWORD
		JLabel lblConfPassword = new JLabel("Confirm Password");
		lblConfPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblConfPassword, "4, 12, right, default");
		
		tfRegisterConfPassword = new JTextField();
		tfRegisterConfPassword.setColumns(15);
		panel.add(tfRegisterConfPassword, "6, 12, left, default");
		
		//REGISTER
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//to confirm, while loop until correct?
				String firstName = tfRegisterFirstName.getText();
				String lastName = tfRegisterLastName.getText();
				String email = tfRegisterEmail.getText();
				String password = tfRegisterPassword.getText();
				String confpassword = tfRegisterConfPassword.getText();
				
				User user = new User(lastName, firstName, email, password);
				
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, "Thank you for registering!");
				
			}
		});
		panel.add(btnRegister, "4, 14");
		
		//CANCEL
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SpaceInvadersRegister.dispose();
			}
		});
		panel.add(btnCancel, "6, 14");
	}
}
