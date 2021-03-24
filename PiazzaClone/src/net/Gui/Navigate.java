package net.Gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.codejava.PostManager;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Navigate extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void runNavigate(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Navigate frame = new Navigate();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Navigate() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(143, 45, 117, 29);
		contentPane.add(btnLogin);
		
		JButton btnAddQuestion = new JButton("Add question");
		btnAddQuestion.setBounds(143, 86, 117, 29);
		contentPane.add(btnAddQuestion);
		
		JButton btnReply = new JButton("Reply to post");
		btnReply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnReply.setBounds(143, 127, 117, 29);
		contentPane.add(btnReply);
		
		JButton btnSearch = new JButton("Search in posts");
		btnSearch.setBounds(125, 168, 157, 29);
		contentPane.add(btnSearch);
		
		
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					net.Gui.LogIn.runLogin(null);
				}
				catch(Exception e)
				{
		            System.err.println("[UIManager] Exeption: " + e.getMessage());
				}
			}
		}); 
		
		btnAddQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					net.Gui.AddPost.runAddPost(null);
				}
				catch(Exception e)
				{
		            System.err.println("[UIManager] Exeption: " + e.getMessage());
				}
			}
		});
		
		btnReply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					net.Gui.reply.runReply(null);
				}
				catch(Exception e)
				{
		            System.err.println("[UIManager] Exeption: " + e.getMessage());
				}
			}
		});
		
		
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					net.Gui.SearchForWord.runSearchForWord(null);
				}
				catch(Exception e)
				{
		            System.err.println("[UIManager] Exeption: " + e.getMessage());
				}
			}
		});
	}

}
