import java.util.Arrays;


public class BoggleDice {
	//Taken from the actual board game. http://en.wikipedia.org/wiki/Talk%3ABoggle
		//Each die represents a tile on the Boggle Grid 
		//Makes it easier/more fun to find words
		private char[] die1 = new char[]{'A','A','E','E','G','N'};
		private char[] die2 = new char[]{'E','L','R','T','T','Y'};
		private char[] die3 = new char[]{'A','O','O','T','T','W'};
		private char[] die4 = new char[]{'A','B','B','J','O','O'};
		private char[] die5 = new char[]{'E','H','R','T','V','W'};
		private char[] die6 = new char[]{'C','I','M','O','T','U'};
		private char[] die7 = new char[]{'D','I','S','T','T','Y'};
		private char[] die8 = new char[]{'E','I','O','S','S','T'};
		private char[] die9 = new char[]{'D','E','L','R','V','Y'};
		private char[] die10 = new char[]{'A','C','H','O','P','S'};
		private char[] die11 = new char[]{'H','I','M','N','Q','U'};
		private char[] die12 = new char[]{'E','E','I','N','S','U'};
		private char[] die13 = new char[]{'E','E','G','H','N','W'};
		private char[] die14 = new char[]{'A','F','F','K','P','S'};
		private char[] die15 = new char[]{'H','L','N','N','R','Z'};
		private char[] die16 = new char[]{'D','E','I','L','R','X'};
		private char[][] dice = new char[][]{die1, die2, die3, die4, die5, die6, die7, die8, die9, die10, die11,die12, die13, die14, die15, die16}; 

	public BoggleDice()
	{
		
	}
	
	public char[][] getDice()
	{
		return dice; 
	}
	public char rollDice(char[] die)
	{
		int index = (int) Math.floor(Math.random()*die.length); 
		//System.out.println("rolled dice: " + die[index]); 
		
		return die[index];
	}
}
