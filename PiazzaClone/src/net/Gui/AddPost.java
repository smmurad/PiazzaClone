package net.Gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.codejava.PostManager;
import net.codejava.UserHandler;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JButton;

public class AddPost extends JFrame {

	private JPanel contentPane;
	private JTextField TitleField;
	private JTextField FolderField;

	/**
	 * Launch the application.
	 */
	public static void runAddPost(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPost frame = new AddPost();
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
	public AddPost() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel PostTitleLabel = new JLabel("Type your post title:");
		PostTitleLabel.setBounds(6, 6, 144, 16);
		contentPane.add(PostTitleLabel);
		
		JLabel PostTextLabel = new JLabel("Type your post text:");
		PostTextLabel.setBounds(6, 34, 144, 16);
		contentPane.add(PostTextLabel);
		
		TitleField = new JTextField();
		TitleField.setBounds(162, 1, 271, 26);
		contentPane.add(TitleField);
		TitleField.setColumns(10);
		
		JEditorPane TextField = new JEditorPane();
		TextField.setBounds(162, 39, 271, 116);
		contentPane.add(TextField);
		
		JLabel FolderLabel = new JLabel("Choose folder:");
		FolderLabel.setBounds(6, 164, 144, 16);
		contentPane.add(FolderLabel);
		
		FolderField = new JTextField();
		FolderField.setBounds(162, 159, 271, 26);
		contentPane.add(FolderField);
		FolderField.setColumns(10);
		
		JButton btnExcecutePost = new JButton("Post");
		btnExcecutePost.setBounds(241, 207, 117, 29);
		contentPane.add(btnExcecutePost);
		
		JButton btnMenu = new JButton("Back to menu");
		btnMenu.setBounds(241, 237, 117, 29);
		contentPane.add(btnMenu);
		
		
		
		btnExcecutePost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					String postTitle = TitleField.getText();
					String postText = TextField.getText();
					String folderName = FolderField.getText();
					
					if(PostManager.getInstance().AddQuestionPost(postTitle, postText, folderName))
						System.out.println("Post can be created");
					else
						System.out.println("Failed to create post...");
				}
				catch(Exception e)
				{
		            System.err.println("[UIManager] Exeption: " + e.getMessage());
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
