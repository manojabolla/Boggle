import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
//BUG: If same letter; messes up the adjacent thing
import javax.swing.Timer;

public class Board extends JPanel implements MouseListener{
	
	private int SIZE;// = 5; 
	private static final int gameLength = 60; //length of time in seconds
	private BoggleTile[][] grid;

	private BoggleTile clickedTile; 
	private BoggleGame game; 
	private BoggleDice dice; 
	private ArrayList<String> currWord;
	private ArrayList<String> guessedWords;
	private JTextArea words = new JTextArea(); 
	private JTextArea possWords = new JTextArea(); 
	private int counter; 
	private Trie trie; 
	private JPanel main; 
	private JPanel board; 
	private JLabel score; 
	private JLabel timer; 
	private Timer t;
	private int count = 0; 
	
	public Board(int size)
	{
		SIZE = size; 
		initVariables();
		setLayout(new BorderLayout());
		JLabel text = new JLabel("Boggle!"); 
		text.setHorizontalAlignment(getWidth()/2);
		main.add(text, BorderLayout.NORTH); 
		main.add(initTiles(), BorderLayout.CENTER); 
		main.add(inputPanel(), BorderLayout.SOUTH);
		
		add(main, BorderLayout.CENTER);
		add(userGuessed(), BorderLayout.WEST); 
		add(computerGuessed(), BorderLayout.EAST);
		
		t = new Timer(1000, new TimerListener()); 
		t.start(); 
	}
	public void initVariables()
	{
		main = new JPanel(new BorderLayout());
		board = new JPanel(new GridLayout(SIZE,SIZE)); 
		grid = new BoggleTile[SIZE][SIZE];
		score = new JLabel();
		timer = new JLabel(); 
		trie = new Trie(); 
		game = new BoggleGame(); 
		dice = new BoggleDice(); 
		currWord = new ArrayList<String>(); 
		guessedWords = new ArrayList<String>(); 
		counter = 0;
		
	}
	
	public JPanel userGuessed()
	{
		JPanel human = new JPanel(new BorderLayout()); 
		JLabel c = new JLabel("Guessed Words"); 
		c.setFont( new Font("Serif", Font.BOLD, 15));
		words = new JTextArea(game.listToString(guessedWords)); 
		words.setEditable(false); 
		words.setBackground(Color.GRAY);
		human.add(c, BorderLayout.NORTH);
		human.add(words, BorderLayout.CENTER); 
		return human; 
	}
	
	public JPanel computerGuessed()
	{
		JPanel comp = new JPanel(new BorderLayout()); 
		JLabel label = new JLabel("Possible Words"); 
		label.setFont(new Font("Serif", Font.BOLD, 15));
		possWords = new JTextArea("");
		possWords.setEditable(false); 
		possWords.setBackground(Color.GRAY);
		possWords.setForeground(Color.white);
		possWords.setFont(new Font("Serif", Font.BOLD, 15));

		comp.add(label, BorderLayout.NORTH); 
		comp.add(possWords, BorderLayout.CENTER);
		return comp;
	}
	
	//add to the center 
	public JPanel initTiles()
	{
		System.out.println("Init Tiles"); 
		board = new JPanel(new GridLayout(SIZE,SIZE));
		
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid.length; j++)
			{
				grid[i][j] = new BoggleTile(dice.rollDice(dice.getDice()[i]));
				grid[i][j].addMouseListener(this); 
				board.add(grid[i][j]);
			}
		}
		return board; 
	}

	public JPanel inputPanel()
	{
		JPanel n = new JPanel(new GridLayout(3,1)); 
		//add a scoreboard?
		score.setText("Score: " + game.getScore()); 
		timer.setText("Time Remaining: " + gameLength); 
		n.add(foundWord());
		n.add(clearTiles());
		JPanel st = new JPanel(new GridLayout(1, 3)); 
		n.add(score); 
		n.add(timer); 
		n.add(hintButton());
		return n; 
	}

	
	public JButton foundWord()
	{
		JButton word = new JButton("Found Word!");
		word.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String word = String.join("", currWord); //get word
				//check against trie  to see if it is a valid word
				//check with trie AND if adjacent
				String[] currArray = currWord.toArray(new String[currWord.size()]); 
				boolean inDictionary = trie.search(word); 
				boolean isAdjacent = isAdjacent(currArray);
				if(inDictionary && isAdjacent)
				{
					System.out.println("Valid word!"); 
					if(guessedWords.contains(word))
					{
						System.out.println("Already guessed"); 
					}
					else 
					{
						guessedWords.add(word); 
						game.updateScore(word.length()); 
						score.setText("Score: " + game.getScore());
						words.setText(game.listToString(guessedWords));
					}
					
					System.out.println("New Score: " + game.getScore()); 
				}
				else if(!isAdjacent)
				{
					
					System.out.println("Letters must be adjacent to each other"); 
				}
				else if(!inDictionary)
				{
					System.out.println("That is not a valid word"); 
				}
				else 
				{
					System.out.println("Please choose a word");
				}
				
				game.clearTile(grid); 
				repaint();
				currWord = new ArrayList<String>(); //reset arraylist
				counter = 0; //reset counter
			}
		});
		
		return word; 
	}
	
	
	
	public JButton clearTiles()
	{
		JButton clear = new JButton("Clear Tiles"); 
		clear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{	
				game.clearTile(grid); 
				currWord = new ArrayList<String>(); 
				counter = 0;				
				repaint();
			}
		});
		return clear; 
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		clickedTile = (BoggleTile)e.getSource(); 
		clickedTile.setClicked(true); 
		if(e.getClickCount() < 2 )
		{
			currWord.add(counter, clickedTile.getDescription()); 
			counter++;
		}
		repaint(); 
		
	}
	
	private class TimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			count++; 
			timer.setText("Time Remaining: " + (60 - count)); 
			//System.out.println(count + " seconds elapsed"); 
			if(count == 5)
			{
				gameOver(); 
				t.stop();
			}
		}
		
	}
	public boolean isAdjacent(String[] c)
	{
		for(int i = 0; i < c.length - 1; i++)
		{
			if(!game.isAdjacent(c[i], c[i+1], grid))
				return false; 
		}
		return true; 
	}
	
	
	private void gameOver()
	{
		System.out.println("Game Over!"); 
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid.length; j++)
			{
				grid[i][j].removeMouseListener(this); 
			}
		}
		ArrayList<String> n = game.getAllWords(grid); 
		possWords.setText(game.listToString(n));
		
		System.out.println("Your final score is: " + game.getScore() + " Nicely done!"); 
		System.out.println("Your found " + guessedWords.size() + " words in " + count + " seconds!"); 
	}
		
	public JButton hintButton()
	{
		JButton hint = new JButton("Get Hint!"); 
		hint.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{	
				ArrayList<String> n = game.getAllWords(grid); 
				String rand = n.get((int)(Math.random() * n.size()));
				//set text of some random hint textfield as rand
				//score--; 
				//make sure this string has not already been guessed
				//if it has, get another string
				//else; add to guessed letters? 
				System.out.println(rand);
				//repaint
			}
		});
		return hint;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
