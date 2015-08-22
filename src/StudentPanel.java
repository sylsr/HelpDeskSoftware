package helpDeskPanel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class StudentPanel extends JPanel
{
	private JButton addFunds, chargeAccount, rental, back;
	private JPanel studentPanel;
	private JTabbedPane tab;
	private JPanel buttonPanel, infoPanel, namePanel, blankPanel;
	private JLabel student, currentFunds, rentalNum, banned;
	String numString, bannedYesOrNo = "No";
	double funds, balance, subtotal;
	int amountConfirmation, rentNum;
	
	public StudentPanel()
	{
		
		addFunds = new JButton("Add");
		addFunds.setBackground(Color.white);
		addFunds.setForeground(Color.blue);
		addFunds.setFont(new Font("Serif", Font.BOLD, 21));
		
		chargeAccount = new JButton("Charge");
		chargeAccount.setBackground(Color.white);
		chargeAccount.setForeground(Color.blue);
		chargeAccount.setFont(new Font("Serif", Font.BOLD, 21));
		
		rental = new JButton("Rental");
		rental.setBackground(Color.white);
		rental.setForeground(Color.blue);
		rental.setFont(new Font("Serif", Font.BOLD, 21));
		
		student = new JLabel("Tyler Manning");
		student.setFont(new Font("Serif", Font.BOLD, 69));
		student.setForeground(Color.BLUE);
		student.setHorizontalAlignment(SwingConstants.CENTER);
		
		currentFunds = new JLabel("Current Balance: $" + balance);
		currentFunds.setFont(new Font("Serif", Font.BOLD, 35));
		currentFunds.setForeground(Color.BLUE);
		currentFunds.setHorizontalAlignment(SwingConstants.CENTER);
		
		rentalNum = new JLabel("Number of Rentals: " + rentNum);
		rentalNum.setFont(new Font("Serif", Font.BOLD, 35));
		rentalNum.setForeground(Color.BLUE);
		rentalNum.setHorizontalAlignment(SwingConstants.CENTER);
		
		banned = new JLabel("Banned: " + bannedYesOrNo);
		banned.setFont(new Font("Serif", Font.BOLD, 35));
		banned.setForeground(Color.BLUE);
		banned.setHorizontalAlignment(SwingConstants.CENTER);
		
		ImageIcon backPic = new ImageIcon("left-arrow-icon.jpg");
		back = new JButton(backPic);
		back.setPreferredSize(new Dimension(100,100));
		back.setBackground(Color.white);
		
		ButtonListener listener = new ButtonListener();
		addFunds.addActionListener(listener); 
		chargeAccount.addActionListener(listener);
		rental.addActionListener(listener);
		back.addActionListener(listener);
		
		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(100,100));
		buttonPanel.setLayout(new GridLayout(3,1));
		buttonPanel.add(addFunds);
		buttonPanel.add(chargeAccount);
		buttonPanel.add(rental);
		
		infoPanel = new JPanel();
		infoPanel.setPreferredSize(new Dimension(800,500));
		infoPanel.setLayout(new GridLayout(3,1));
		infoPanel.add(currentFunds);
		infoPanel.add(rentalNum);
		infoPanel.add(banned);
		
		namePanel = new JPanel();
		namePanel.setPreferredSize(new Dimension(800,100));
		namePanel.setLayout(new BorderLayout());
		namePanel.add(back, BorderLayout.WEST);
		namePanel.add(student);
		
		blankPanel = new JPanel();
		blankPanel.setPreferredSize(new Dimension(100,100));
		blankPanel.setVisible(false);
		
		setPreferredSize(new Dimension(800,700));
		setBackground(Color.gray);
		setLayout(new BorderLayout());
		add(namePanel, BorderLayout.NORTH);
		add(infoPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.WEST);
		add(blankPanel, BorderLayout.EAST);
		
	}
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource() == addFunds)
			{
				numString = JOptionPane.showInputDialog("Enter amount of funds being added: ");
				funds = Double.parseDouble(numString);
				amountConfirmation = JOptionPane.showConfirmDialog(null, "Is $" + funds + " the correct amount?");
				if(amountConfirmation == JOptionPane.YES_OPTION)
				{
					balance = balance + funds;
					JOptionPane.showMessageDialog(null, "The funds have been successfully added to the account.");
					currentFunds.setText("Current Funds: $" + balance);
				}
				else
				{
					
				}
			}
			else if(e.getSource() == chargeAccount)
			{
					numString = JOptionPane.showInputDialog("Enter price of items: ");
					funds = Double.parseDouble(numString);
					amountConfirmation = JOptionPane.showConfirmDialog(null, "Is $" + funds + " the correct amount?");
					if(amountConfirmation == JOptionPane.YES_OPTION)
					{
						
						if(funds <= balance)
						{
							balance = balance - funds;
							JOptionPane.showMessageDialog(null, "The account has been successfully charged.");
							currentFunds.setText("Current Funds: $" + balance);
						}
						else
						{
							
							JOptionPane.showMessageDialog(null, "Insufficient Funds");
						}
					}
					else
					{
						
					}
				}
			else if(e.getSource() == rental)
				{
					amountConfirmation = JOptionPane.showConfirmDialog(null, "Do you want to add a rental to this account?");
					if(amountConfirmation == JOptionPane.YES_OPTION)
					{
						rentNum++;
						rentalNum.setText("Number of Rentals: " + rentNum);
					}
					else
					{
						
					}
				}
			else if(e.getSource() == back)
			{
				
			}
		}
						
	}
		
}

