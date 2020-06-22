/*
 * NAME: Derrick Demers
 * DATE: 5/31/2020
 * The purpose of this program is to create a JPanel that multiple balls will bounce around in
 */

import java.util.Random; //calls for a random number generator to be used
import java.awt.Color; //implements various use of color in the program

public class Ball implements Runnable
{
	private int X; //tracks the x position of the ball
	private int Y; //tracks the y position of the ball
	private int xChange; //change in the x position of the ball
	private int yChange; //change in the y position of the ball
	private int Diameter; //the ball's diameter
	private int maxWidth; //width of the area the ball bounces
	private int maxHeight; //height of the area the ball bounces
	private boolean forwards; //is the ball moving one way or the other
	private boolean downwards; //is the ball moving up or down
	private Random numberGenerator; //the number generator for the colors
	private Color ballColor; //color of the balls used
	
	public Ball(int windowWidth, int windowHeight, int StartX, int StartY, int ballDiameter) //constructor for the window and starting ball
	{
		X = StartX; //starting x position
		Y = StartY; //starting y position
		Diameter = ballDiameter; //sets the ball diameter
		forwards = true; //sets ball moving left 
		downwards = true; //sets ball moving down
		setMaxWidth(windowWidth); //sets the width of the area in which the ball will bounce
		setMaxHeight(windowHeight); //sets the height of the area in which the ball will bounce
		numberGenerator = new Random(); //initializes number generator
		xChange = 1 + numberGenerator.nextInt(5); //random rate of x plane change between 1 and 5 pixels
		yChange = 1 + numberGenerator.nextInt(5); //random rate of y plane change between 1 and 5 pixels
		ballColor = Color.blue; //makes the ball blue
	}
	
	public void run() //what is used to run the program
	{
		while(true) //will continue until the user exits program
		{
			try
			{
				Thread.sleep(20); //sleeps for 20 milliseconds
				
				if(forwards) //if the ball is moving forwards
				{
					X += xChange; //moves the ball further forward
					
					if(X >= (maxWidth - Diameter)) //if ball reached the horizontal boundary
					{
						forwards = false; //moves the ball backwards
					}
				}
					else //if the ball is moving backwards
					{
						X -= xChange; //moves the ball forward
						
						if (X <=0 ) //if the ball has reached the other boundary
						{
							forwards = true; //sets the ball moving forward
						}
					}
				if(downwards) //if the ball is moving downwards
					{
						Y += yChange; //brings the ball further down
						
						if(Y >= (maxHeight - Diameter)) //if the ball has reached the bottom
						{
							downwards = false; //ball will start moving up
						}
					}
				else //if the ball is moving up
				{
					Y -= yChange; //brings the ball further up
					
					if (Y <= 0) //if the ball has reached the top
					{
						downwards = true; //the ball moves down again
					}
				}
			}
			catch(InterruptedException exception) //if an error occurs
			{
				System.out.println("Error loading in ball....");
			}
		}
	}
	
	
	public void setMaxWidth(int width) //sets the max width of the ball area 
	{
		maxWidth = width;
	}
	
	public void setMaxHeight(int height) //sets method for ball height
	{
		maxHeight = height;
	}
	
	public void setBallColor(Color color) //sets method for the color of the ball
	{
		ballColor = color;
	}
	
	public int getDiameter() //sets method for getting the diameter
	{
		return Diameter;
	}
	
	public int getXPosition() //gets the x coordinate of the ball
	{
		return X;
	}
	
	public int getYPosition() //gets the y position of the ball
	{
		return Y;
	}
	
	public Color getBallColor() //gets the color of the ball
	{
		return ballColor;
	}
	
}


