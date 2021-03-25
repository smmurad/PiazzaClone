package net.Gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.DBResults.UserStats;
import net.codejava.DatabaseAPI;
import net.codejava.InputHandler;
import net.codejava.UserHandler;
import net.codejava.statsManager;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Statistics extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void runStatistics(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Statistics frame = new Statistics();
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
	public Statistics() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JTextArea textArea = new JTextArea();
		//Add text to texfield
		textArea.setText(getStatistics());
		textArea.setBounds(6, 6, 403, 226);
		contentPane.add(textArea);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(24, 6, 385, 228);
		contentPane.add(scrollPane);
		
		JButton btnMenu = new JButton("Back to menu");
		btnMenu.setBounds(139, 236, 117, 29);
		contentPane.add(btnMenu);
		
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
	
	private String getStatistics() {
		if(!UserHandler.getInstance().IsInstructor)
		{
			return "Cannot view stats unless you are instructor";
		}
		try
		{
			List<UserStats> datas = statsManager.getInstance().getStats();
			if(datas.size() > 0)
			{
				String StatString ="USER STATS: \n\n ------------------------- \n";
				for(UserStats stats : datas)
				{
					StatString += "Username: " + stats.username + "\nPosts viewed: " + stats.postsViewed + "\nPosts created: " + stats.postsCreated + "\n\n";
				}
				return StatString;
			}
			// Print data for loop
			else
				return "No statistics to show...";
		}
		catch(Exception e)
		{
            System.err.println("[UIManager] Exeption: " + e.getMessage());
            return "[UIManager] Exeption: " + e.getMessage();
		}
	}

}
