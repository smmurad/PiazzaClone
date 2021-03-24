package net.Gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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

public class SearchForWord extends JFrame {

	private JPanel contentPane;
	private JTextField SearchWordField;

	/**
	 * Launch the application.
	 */
	public static void runSearchForWord(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchForWord frame = new SearchForWord();
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
	public SearchForWord() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel PostTitleLabel = new JLabel("Type your search word:");
		PostTitleLabel.setBounds(6, 6, 144, 16);
		contentPane.add(PostTitleLabel);
		
		SearchWordField = new JTextField();
		SearchWordField.setBounds(162, 1, 271, 26);
		contentPane.add(SearchWordField);
		SearchWordField.setColumns(10);
		
		JEditorPane TextField = new JEditorPane();
		TextField.setBounds(162, 39, 271, 116);
		contentPane.add(TextField);
	
		
		JButton btnExcecutePost = new JButton("Search");
		btnExcecutePost.setBounds(241, 207, 117, 29);
		contentPane.add(btnExcecutePost);
		
		
		
		btnExcecutePost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					String searchWord = SearchWordField.getText();
					
					List<Integer> ids = PostManager.getInstance().SearchForPost(searchWord);
					//for loop view results
					if(ids.isEmpty())
					{
						System.out.println("No post containing searchword: " + searchWord + " ...\n\n");
						return;
					}
					System.out.println("Found "  + ids.size() + " matching rows with searchword: "  + searchWord + "\n");
					for (int id:ids) {
						System.out.println("postID: " + id);
					}

				}
				catch(Exception e)
				{
		            System.err.println("[UIManager] Exeption: " + e.getMessage());
				}
				
				
			}
		}); 
	}
}