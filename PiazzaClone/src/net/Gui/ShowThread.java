package net.Gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.codejava.DatabaseAPI;

import javax.swing.JTextArea;

import javax.swing.JScrollPane;
import javax.swing.JButton;
public class ShowThread extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void runShowThread(String ThreadID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowThread frame = new ShowThread(ThreadID);
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
	public ShowThread(String ThreadID) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		//Add text to texfield
		textArea.setText(DatabaseAPI.getInstance().getPostFromThread(ThreadID));
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
}
