package net.Gui;

import net.codejava.DatabaseAPI;
import java.util.List;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;

import javax.swing.DefaultListModel;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;

public class ThreadOverview extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void runThreadOverview(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThreadOverview frame = new ThreadOverview();
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
	public ThreadOverview() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		List<String> tempList = DatabaseAPI.getInstance().getAllThreads();
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (int i=0; i<tempList.size(); i++) {
			listModel.addElement(tempList.get(i));
		}
		
		JList list = new JList(listModel);
		list.setBounds(47, 6, 323, 233);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		contentPane.add(list);
		
		JButton btnMenu = new JButton("Back to menu");
		btnMenu.setBounds(139, 243, 117, 29);
		contentPane.add(btnMenu);
		
		list.addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent e)
		    {
		        if(!e.getValueIsAdjusting()) {
		            final List<String> selectedValuesList = list.getSelectedValuesList();
		            String ThreadID = selectedValuesList.get(0);
		            net.Gui.ShowThread.runShowThread(ThreadID);
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
