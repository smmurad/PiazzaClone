package net.Gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.codejava.PostManager;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JButton;

public class reply extends JFrame {

	private JPanel contentPane;
	private JTextField postID;
	private JButton btnReplyPost;

	/**
	 * Launch the application.
	 */
	public static void runReply(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					reply frame = new reply();
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
	public reply() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel replyIDLabel = new JLabel("Reply to post:");
		replyIDLabel.setBounds(37, 28, 93, 16);
		contentPane.add(replyIDLabel);
		
		postID = new JTextField();
		postID.setText("postID");
		postID.setBounds(142, 23, 130, 26);
		contentPane.add(postID);
		postID.setColumns(10);
		
		JEditorPane replyText = new JEditorPane();
		replyText.setText("Write your reply here.");
		replyText.setBounds(78, 76, 329, 119);
		contentPane.add(replyText);
		
		btnReplyPost = new JButton("Post");
		btnReplyPost.setBounds(155, 219, 117, 29);
		contentPane.add(btnReplyPost);
		
		
		

		btnReplyPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					String replyID = postID.getText();
					String replyTxt = replyText.getText();
					
					if(PostManager.getInstance().AddReplyPost(replyID, replyTxt))
						System.out.println("Replied to Post");
					else
						System.out.println("Failed to reply to post...");
				}
				catch(Exception e)
				{
		            System.err.println("[UIManager] Exeption: " + e.getMessage());
				}
				
				
			}
		});
	}
}
