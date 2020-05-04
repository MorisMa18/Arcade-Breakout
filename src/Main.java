import javax.swing.JFrame; 
import javax.swing.JOptionPane;

//*IMPORTANT NOTES:*
//The ball sometimes will travel really slow or change its travel speed during the game. I tried to fix it but it didn't work. 
//The user might have to restart the program several times. 
//In addition, if the program doesn't respond in the beginning, please restart the program several times.


public class Main {

	public static void main(String[] args) {
		JFrame obj = new JFrame (); 
		GamePlay gamePlay = new GamePlay(); 
		obj.setBounds (10,10,800,700); 
		obj.setTitle ("Ma_Morris Final Project"); 
		obj.setResizable(false);
		obj.setVisible (true); 
		obj.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE); 
		obj.add(gamePlay);
		JOptionPane.showMessageDialog (null, "Hello, Welcome to the breakout game! "
				+ "The objective of this game is to destroy all the bricks located at the top of the window.\n "
				+ "Use the ARROW KEY to move the paddle!\n"
				+"Press ENTER to restart the game!"); 
			

	}

}
