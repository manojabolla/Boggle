import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class BoggleGame {
	
	private int score = 0; 
	int[][] grid; 
	private Trie trie; 
	private ArrayList<String> words = new ArrayList<String>(); 
	private char[][] board = new char[4][4];
	private char[] row1 = {'A', 'L', 'O', 'J'}; 
	private char[] row2 = {'V', 'U', 'T', 'S'}; 
	private char[] row3 = {'L', 'B', 'H', 'E'}; 
	private char[] row4 = {'G', 'K', 'R', 'X'};
	private char[][] a = new char[][]{row1, row2, row3, row4};  
	
	public BoggleGame()
	{
		trie = new Trie(); 
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board.length; j++)
			{
				board[i][j] = a[i][j];
			}
		}
		//findAllWords(a);
	}

	public void clearTile(BoggleTile[][] grid)
	{
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid.length; j++)
			{
				grid[i][j].setClicked(false); 
			}
		}
	}
	
	
	//checks if current character is adjacent to the next character		
	//returns true if they are adjacent
	//else return false
	public boolean isAdjacent(String curr, String next, BoggleTile[][] grid)
	{
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid.length; j++)
			{ 
				//do a grid[i][j].isVisited sort of thing
				if(grid[i][j].getDescription().equals(curr) && grid[i][j].isClicked())
				{
					if(j+1 < grid.length && grid[i][j+1].getDescription().equals(next) && grid[i][j+1].isClicked())
					{
						return true;
					}
					else if((j-1 >= 0) && grid[i][j-1].getDescription().equals(next) && grid[i][j-1].isClicked())
					{
						return true; 
					}
					else if(i+1 < grid.length && grid[i+1][j].getDescription().equals(next) && grid[i+1][j].isClicked())
					{
						return true; 
					}
					else if(i-1 >= 0 && grid[i-1][j].getDescription().equals(next) && grid[i-1][j].isClicked())
					{
						return true; 
					}
					else if(i+1 < grid.length && j+1 < grid.length && grid[i+1][j+1].getDescription().equals(next) && grid[i+1][j+1].isClicked())
					{
						return true;
					}
					else if(i-1 >= 0 && j-1 >=0 && grid[i-1][j-1].getDescription().equals(next) && grid[i-1][j-1].isClicked())
					{	
						return true; 
					}
					else if(i-1 >= 0 && j+1 < grid.length && grid[i-1][j+1].getDescription().equals(next) && grid[i-1][j+1].isClicked())
					{
						return true; 
					}
					else if(i+1 < grid.length && j-1 >=0 && grid[i+1][j-1].getDescription().equals(next) && grid[i+1][j-1].isClicked())
					{
						return true;
					}
					
				//	grid[i][j].setClicked(false); 
				}
			}
		}
		return false; 
	}
	
	//find all possible words?
	//for each tile, 
	
	//gets score given the length of the word guessed
	public void updateScore(int length)
	{
		if(length == 3 || length == 4)
		{
			score++; 
		}
		else if(length == 5)
		{
			score = score + 2; 
		}
		else if(length == 6)
		{
			score = score + 3; 
		}
		else if(length == 7)
		{
			score = score + 5; 
		}
		else if(length >= 8)
		{
			score = score + 11; 
		}
	}
	
	public int getScore()
	{
		return score; 
	}
	
	
	public String listToString(ArrayList<String> al)
	{
		StringBuffer text = new StringBuffer(); 
		for(int i = 0; i < al.size(); i++)
		{
			text.append(al.get(i).toString()).append('\n');
		}
		return text.toString(); 
	}
	
	public void findAllWords(String[][] board)
	{
		words.clear();
		boolean[][] visited = new boolean[board.length][board.length]; 
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board.length; j++)
			{
				traversePath(board, visited, "", i, j); 
			}
		}
		System.out.println(words.toString());
	}
	
	public void traversePath(String[][] board, boolean[][] visited, String currWord, int x, int y)
	{
		if(x < 0 || x >= board.length || y < 0 || y >= board.length || visited[x][y])
		{
			return; 
		}
		//add the character at x y to the current word 
		String word = currWord + (board[x][y]).toString();
		//System.out.println(word);
		if(word.length() >= 3 && trie.search(word))// && trie.search(word))
		{
			words.add(word); 
		}
		//copy the visited array
		boolean[][] visit = new boolean[visited.length][visited.length]; 
		for(int i = 0; i < visited.length; i++ )
		{
			for(int j = 0; j < visited.length; j++)
			{
				visit[i][j] = visited[i][j]; 
			}
		}

		visit[x][y] = true;
		traversePath(board, visit, word, x-1, y); 
		traversePath(board, visit, word, x+1, y); 
		traversePath(board, visit, word, x, y-1); 
		traversePath(board, visit, word, x, y+1); 
	}
	
	public ArrayList<String> getAllWords(BoggleTile[][] a)
	{
		
		findAllWords(tileToString(a));
		return words;
	}
	
	public String[][] tileToString(BoggleTile[][] tile)
	{
		String[][] a = new String[tile.length][tile.length];
		
		for(int i = 0; i < tile.length; i++ )
		{
			for(int j = 0; j < tile.length; j++)
			{
				a[i][j] = tile[i][j].getDescription(); 
			}
		}
		
		return a; 
	}
	
	public static void main(String[] args)
	{
		new BoggleGame(); 
	}
	
	
	
}
