import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;

public class BoggleTile extends Component
{
	private String description; 
	private boolean clicked; 
	private boolean visited; 
	
	public BoggleTile(char s)
	{
		description = s + ""; 
		setClicked(false);
	}
    
	public void setClicked(boolean f)
	{
		clicked = f; 
	}
	public boolean isClicked()
	{
		return clicked; 
	}
	public boolean isVisited()
	{
		return visited; 
		
	}
	
	public void setVisited(boolean f)
	{
		visited = f; 
	}
	/*
	 * special method for drawing on the tile
	 * we override the method here to customize the display
	 */
	public void paint (Graphics g)
	{
		if(isClicked())
			paintTile(g, Color.pink); 
		else 
		{	//call utility method to paint background of tile
			//with color and pass along graphics object to paint on
			paintTile(g, Color.WHITE); 
		}
			//call utility method to paint description on tile
			paintDescription(g); 
		
	}
	
	/*
	 * utility method to paint background of tile
	 * with specified color
	 */
	public void paintTile(Graphics g, Color c)
	{
		//set the color of the "brush"
		g.setColor(c); 
		//fil rectangle using x,y,width,height arguments
		g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1,  10, 10);
		//add a rounded rectangular border in black
		g.setColor(Color.BLACK); 
		//arguments are x,y, width, height, arcwidth, archeight
		g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
	}
	/*
	 * utility method to paint description onto card
	 */
	public void paintDescription(Graphics g)
	{
		//set brush color to white 
		g.setColor(Color.BLACK);
		//set font
		g.setFont(new Font ("Palatino", 1, 24));
		//draw text
		g.drawString(description, 10 , getHeight() / 2 + 15); 
	}
	
	public String getDescription()
	{
		return description; 
	}


}