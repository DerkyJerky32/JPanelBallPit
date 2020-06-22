/*
 * NAME: Derrick Demers
 * DATE: 5/31/2020
 * The purpose of this program is to create a JPanel that multiple balls will bounce around in
 */

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class BallPanel extends JPanel //class inherits from JPanel 
{
	private RepaintTimer repaintTimer; //an object used to determine how often the ball moves
	private ExecutorService threadExecutor; //running threads in parallel
	private int panelWidth; //width of panel
	private int panelHeight; //height of panel
	private int ballCounter; //counter for number of balls on screen
	private final int MAX_NUM_OF_BALLS = 20; //maximum number of balls on screen
	private Ball[] ballArray = new Ball[MAX_NUM_OF_BALLS]; //declares the array of Balls
	private Random randomNumber; //initializes random number generator
	
	public BallPanel(int width, int height) //constructor accepting the parameters for the window size
	{
		setPreferredSize(new Dimension(width, height)); //setting size
		panelWidth = (int) getPreferredSize().getWidth(); //getting width
		panelHeight = (int) getPreferredSize().getHeight(); //getting height
		
		repaintTimer = new RepaintTimer(this, 20); //initialize repaint timer and sleeps for 20 milliseconds
		
		threadExecutor = Executors.newCachedThreadPool(); //initializes threadExecutioner
		setBackground(Color.white); //sets the panel background to the color white
		setOpaque(true); //makes background opaque
		addMouseListener(new MouseListener()); //adds a mouse listener
		threadExecutor.execute(repaintTimer); //executes the repaint timer in one thread
		ballCounter = 0; //sets the ball counter to 0
		randomNumber = new Random(); //initializes random number generator
	}
	
	
	public void paintComponent(Graphics g) //paints the ball
	{
		super.paintComponent(g); //calls the superclass method
		Graphics2D g2d = (Graphics2D) g; //declares the graphics object
		
		for (Ball ball:ballArray) //loops through the ball array
		{
			if (ball != null) //if the ball is not null
			{
				g2d.setPaint(ball.getBallColor()); //set paint to ball color
				
				g2d.fill(new Ellipse2D.Double(ball.getXPosition(),
											  ball.getYPosition(),
											  ball.getDiameter(),
											  ball.getDiameter())
						);
				
				g2d.setPaint(Color.BLACK); //for the shadows
				
				double shadowDiameter = (double)ball.getDiameter() * ((double)ball.getYPosition() / (double) panelHeight);
				//measures how far to cast the shadow
				
				g2d.fill(new Ellipse2D.Double(ball.getXPosition(),
												panelHeight - (shadowDiameter / 2),
												shadowDiameter * 1.5, 
												shadowDiameter / 2 
												) //measures the position of the ball and then casts and fills the shadow
						);
			}
		}
	}
	
	public class MouseListener extends MouseAdapter //class for the mouse listener
	{
		public void mousePressed(MouseEvent e) //override method for mousePressed
		{
			if (ballCounter < MAX_NUM_OF_BALLS) //if the maximum number of balls has not been reached
			{
				//adds a new ball to the array
				ballArray[ballCounter] = new Ball((int) getPreferredSize().getWidth(), //sets the width
										 (int) getPreferredSize().getHeight(), //sets the height
										 e.getX(), //gets x position from mouse
										 e.getY(), //gets y position from mouse
										 10); //diameter of 10 pixels
										 
				//setting the ball to a random color
				ballArray[ballCounter].setBallColor(new Color(randomNumber.nextInt(256),
															  randomNumber.nextInt(256),
															  randomNumber.nextInt(256)
															  )
													);
				
				threadExecutor.execute(ballArray[ballCounter]); //execute the ball in its own thread
				
				ballCounter++; //adds one more to the ball counter
			}
		}
	}
}