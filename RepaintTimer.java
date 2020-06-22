/*
* NAME: Derrick Demers
 * DATE: 5/31/2020
 * The purpose of this program is to create a JPanel that multiple balls will bounce around in
 */

import javax.swing.JPanel;

class RepaintTimer implements Runnable
{
	private JPanel repaintPanel; //the panel to be repainted
	private int sleepTime; //time to wait between repaints
	
	public RepaintTimer(JPanel panel, int time)
	{
		repaintPanel = panel; //sets the panel to be repainted
		sleepTime = time; //sets the sleep time
	}
	
	public void run()
	{
		while(true) //loops until user exits
		{
			try
			{
				Thread.sleep(sleepTime);//sleeps for a while
				repaintPanel.repaint(); //repaints the panel
			}
			catch(InterruptedException exception)
			{
				System.out.println("Interrupted exception on timer");
			}
		}
	}

}
