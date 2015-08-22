package helpDeskPanel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class TabbedPane extends StudentPanel
{
	private TabbedPane tab;
	
	public TabbedPane()
	{
		tab = new TabbedPane();
		tab.add("Student", getRootPane().add(new StudentPanel()));
	}
	
}
