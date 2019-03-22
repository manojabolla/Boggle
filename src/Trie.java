import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Trie {
private TrieNode root;
    private ArrayList<String> wordlist;
    private String word;
    
    private ArrayList<String> autocomplete;
    //exception, empty string 
	public Trie()
	{
		root = new TrieNode(); 
		 //Initializes the words array list
        wordlist=new ArrayList<String>();
        //String of autocomplete
        autocomplete=new ArrayList<String>();
        //initializes word to an empty string
        word="";
        try {
        	readInput();
        } catch(IOException e)
        {
        	e.printStackTrace(); 
        }
     //  System.out.println( search("lots")); 
	}
	
	public void insert(String word)
	{
		word = word.toLowerCase(); 
		//do not need to add the word again
		if(search(word) == true)
		{
			return; 
		}
		TrieNode curr = root;
		//for each character in the word
		for(char ch: word.toCharArray())
		{	
			TrieNode child = curr.nextNode(ch); 
			if(child != null)
			{
				curr = child; 
			}
			else 
			{
				curr.getChilds().add(new TrieNode(ch)); 
				curr = curr.nextNode(ch); 
			}
			
		}
	
		curr.setEnd(true); //
	}
	
	/*
	 * returns true if word is in trie
	 */
	public boolean search(String word)
	{
		word = word.toLowerCase();
		TrieNode curr = root; 
		
				
		for(char ch: word.toCharArray())
		{
			if(curr.nextNode(ch) == null)
				return false; 
			else 
				curr = curr.nextNode(ch); 
		}
		
		if(curr.isEnd())
			return true;
		
		//return true;
		return false;
	}

    /**
     * Auto compete method for the trie
     */
    public ArrayList<String>  autoComplete(char ch)
    {
        //returns result from depth first search
        return DFS(root.nextNode(ch));
    }

    public ArrayList<String> DFS(TrieNode node)
    {

        //gets the letter at the node that was passed
        word=word+Character.toString(node.getCharacter());
        //checks whether the node is an end
        if(node.isEnd())
        {
            //adds the word to the auto complete array list
            autocomplete.add(word);
        }
        //loops through the children of the node
        for (TrieNode tn: node.getChilds())
        {
            //calls Depth first search on the children
            DFS(tn);
            //shortens the length of the words to allow for the recursion to back track
            word = word.substring( 0, word.length()-1);
        }
        //returns the auto completed array
        return autocomplete;

    }
    
    /**
     * Prints the trie by doing a DFS search on the Tree
     */
    public  void printTree()
    {
        //calls DFS on the root
        DFS(root);
        //Prints the autocomplete
        for(int i=0;i<autocomplete.size();i++)
        {
            //prints all the values in the array
            System.out.println(autocomplete.get(i));
        }

    }
    /**
	 * Read input from the dictionary and insert each definition into the hash table
	 * @throws IOException
	 */
	public void readInput() throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader("bogwords.txt"));
			
		String line = reader.readLine();
		
		while(line != null){
			//parse line
			//skip the comma and the space
			insert(line);
			line = reader.readLine();
		}
	}
	


	public static void main(String[] args)
	{
		
			new Trie();
		
		
	}
}
