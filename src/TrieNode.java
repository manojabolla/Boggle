import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class TrieNode {
	private String value = ""; 
	private ArrayList<TrieNode> childs = new ArrayList<TrieNode>(); 
	private char character; 
	private boolean end = false; 
	public TrieNode()
	{
		childs = new ArrayList<TrieNode>();
	}
	public TrieNode(char c)
	{
		this();
		this.character = c;
	}
	public boolean isEnd()
	{
		return end; 
	}
	public void setEnd(boolean f)
	{
		end = f; 
	}
	public ArrayList<TrieNode> getChilds()
	{
		return childs;
	}
	public void setChilds(ArrayList<TrieNode> childs)
	{
		this.childs = childs; 
	}
	
	public void setCharacter(char character)
	{
		this.character = character; 
	}
	
	public char getCharacter()
	{
		return character; 
	}
	
	public TrieNode nextNode(char c){
		if(childs != null)
		{
			for(TrieNode e: childs)
			{
				if(e.getCharacter() == c)
				{
					return e; 
				}
			}
		}
		return null;
	}
	
}
