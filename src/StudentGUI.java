package helpDeskPanel;

import javax.swing.*;

public class StudentGUI 
{

	public static void main(String[] args) 
	{
		JFrame frame = new JFrame("Student");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(new TabbedPane());
		
		frame.pack();
		frame.setVisible(true);
	}

}
