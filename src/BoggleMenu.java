import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;


public class BoggleMenu extends JFrame{
	
	JFrame frame = new JFrame("Boggle"); 
	JButton play; 
	JButton help; 
	JButton playBig; 
	public BoggleMenu()
	{
		play = new JButton("Play Boggle"); 
		help = new JButton("Help"); 
		playBig = new JButton("Play Big Boggle"); 
		
		setLayout(new GridLayout()); 
		 
		frame.getContentPane().add(inst());
		frame.setSize(400, 300);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE); 

		frame.setVisible(true);
		
		play.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.getContentPane().removeAll();
				frame.getContentPane().repaint(); 
				frame.getContentPane().add(new Board(4)); 
				frame.setSize(500, 400);
			//	frame.pack();
				frame.setVisible(true);
			}
		}); 
		
		help.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.remove(inst());
				
			}
		});
		playBig.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				frame.getContentPane().removeAll();
				frame.getContentPane().repaint(); 
				frame.getContentPane().add(new Board(5)); 
				frame.setSize(500, 400);
				frame.setVisible(true);
			}
		});
		
	}
	
	public JPanel inst()
	{
		JPanel n = new JPanel(new BorderLayout()); 
		n.setOpaque(true);
		JLabel ja = new JLabel("Boggle!");
		JTextArea instructions = new JTextArea("Find the most words as the timer ticks away!"); 
		//Find the most words as the Boggle Timer ticks away!
		Font newFont = new Font("Serif", Font.BOLD, 40);
		ja.setFont(newFont);
		ja.setHorizontalAlignment(WIDTH/2);
		JLabel background = new JLabel(new ImageIcon("boggle.jpg"));
		background.setHorizontalAlignment(JLabel.CENTER);
		background.setVerticalAlignment(JLabel.CENTER);
		background.setBorder(new LineBorder(Color.BLACK, 3)); 
		background.setBackground(Color.CYAN);
		n.add(background, BorderLayout.CENTER);
		JPanel buttons = new JPanel(new GridLayout(3,1));
		buttons.add(play); 
		buttons.add(playBig); 
		buttons.add(help); 
		n.add(buttons, BorderLayout.SOUTH); 
		
		return n; 
	}
	
	public void helpScreen()
	{
		
	}
	public static void main(String[] args)
	{
		try {     
            // Set cross-platform Java L&F (also called "Metal")
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} 
		catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
       // handle exception
		}
		new BoggleMenu();
	}
	
	

}
