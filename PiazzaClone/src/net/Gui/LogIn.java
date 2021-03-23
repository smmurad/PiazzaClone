package net.Gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.codejava.InputHandler;
import net.codejava.UserHandler;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LogIn extends JFrame {

	private JPanel contentPane;
	private JTextField LoginField;
	private JTextField PassworField;
	private JButton btnMenu;

	/**
	 * Launch the application.
	 */
	public static void runLogin(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					LogIn frame = new LogIn();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogIn() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel LoginLabel = new JLabel("Login:");
		LoginLabel.setBounds(41, 87, 61, 16);
		contentPane.add(LoginLabel);
		
		JLabel PasswordLabel = new JLabel("Password:");
		PasswordLabel.setBounds(41, 120, 61, 16);
		contentPane.add(PasswordLabel);
		
		LoginField = new JTextField();
		LoginField.setBounds(114, 82, 130, 26);
		contentPane.add(LoginField);
		LoginField.setColumns(10);
		
		PassworField = new JTextField();
		PassworField.setBounds(114, 115, 130, 26);
		contentPane.add(PassworField);
		PassworField.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(114, 157, 117, 29);
		contentPane.add(btnLogin);
		
		btnMenu = new JButton("Back to menu");
		btnMenu.setBounds(114, 204, 117, 29);
		contentPane.add(btnMenu);
		
		setTitle("Logg inn");
		
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					String username = LoginField.getText();
					
					String password = PassworField.getText();
					
					if(UserHandler.getInstance().Login(username, password))
					{
						JOptionPane.showMessageDialog(contentPane, "Login successfull!");
					}
					else
					{
						JOptionPane.showMessageDialog(contentPane, "Login failed");
					}
				}
				catch(Exception e)
				{
		            System.err.println("[UIManager] Input Exception");
				}
				
				
			}
		});
		
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					net.Gui.Navigate.runNavigate(null);
				}
				catch(Exception e)
				{
		            System.err.println("[UIManager] Input Exception");
				}
			}
		});
		
	}

}
