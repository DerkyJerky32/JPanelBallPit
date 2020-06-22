/*
 * NAME: Derrick Demers
 * DATE: 5/31/2020
 * The purpose of this program is to create a JPanel that multiple balls will bounce around in
 */


import javax.swing.JFrame;

public class BallPanelTest extends JFrame //inherits from the JFrame class
{
	private static final long serialVersionUID = 1L;
	BallPanel ballPanel; //BallPanel object
	
	public BallPanelTest()
	{
		super("DDemers Ball Pit"); //call superclass constructor with window name
		ballPanel = new BallPanel(600, 600); //initializing the window and its height and weight
		add(ballPanel); //add ballPanel to the frame
	}
	
	public static void main(String args[])
	{
		BallPanelTest bpt = new BallPanelTest(); //new BallPaneltest object
		bpt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //sets window to exit on close
		bpt.pack(); //resize window to fit contents
		bpt.setVisible(true); //sets window to be visible
	}
}