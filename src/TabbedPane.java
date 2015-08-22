package helpDeskPanel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class TabbedPane extends JFrame
{
	private JTabbedPane tab;
	private String username, password;
	
	public TabbedPane()
	{
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		getContentPane().add(topPanel);
		tab = new JTabbedPane();
		tab.addTab("Student", new StudentPanel());
		tab.addTab("Help Desk", new JPanel());
		topPanel.add(tab, BorderLayout.CENTER);
	}
	
	public static void main(String[]args)
	{
		//username = JOptionPane.showInputDialog("Enter Username: ");
		//password = JOptionPane.showInputDialog("Enter Password: ");
		TabbedPane mainFrame = new TabbedPane();
		mainFrame.setVisible(true);
		mainFrame.pack();
	}
	
}
