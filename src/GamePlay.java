import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics; 
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JOptionPane;
import javax.swing.Timer; 
import javax.swing.JPanel;

public class GamePlay extends JPanel implements KeyListener, ActionListener{
	private static final String Score = null;
	private boolean play = false; 
	private int score = 0; 
	
	private int totalBricks = 24; 
	
	private Timer timer; 
	private int delay = 5; 
	 
	private int playerX= 320; 
	
	private int ballposX = 120; 
	private int ballposY = 350; 
	private int ballXdir = -1; 
	private int ballYdir = -2;
	
	private MapGenerator map; 
	
	public GamePlay(){ 
		map = new MapGenerator(3,8); 
		addKeyListener (this);
		setFocusable(true); 
		setFocusTraversalKeysEnabled (false); 
		timer = new Timer (delay, this ); 
		timer.start(); 
	}
	public void paint (Graphics g){
		//background 
		g.setColor(Color.BLACK); 
		g.fillRect (1,1,792, 692); 
		
		//drawing map 
		map.draw((Graphics2D)g);
		
		// borders
		g.setColor(Color.yellow);
		g.fillRect (0,0,3,692); 
		g.fillRect (0,0,792, 3);
		g.fillRect (791,0,3,692);
		
		//score 
		g.setColor (Color.white); 
		g.setFont (new Font ("Copper Black", Font.BOLD, 25)); 
		g.drawString(""+score,592, 30);
		
		//paddle 
		g.setColor (Color.GREEN); 
		g.fillRect(playerX,600,80,8); 
		
		//ball 
		g.setColor (Color.YELLOW); 
		g.fillOval(ballposX,ballposY, 20, 20);
		g.dispose(); 
		
		if (totalBricks <=0){										//*For some reasons this is not showing up*
			play=false; 
			ballXdir = 0; 
			ballYdir = 0; 
			g.setColor (Color.WHITE);
			g.setFont (new Font ("Copper Black", Font.BOLD, 30)); 
			g.drawString( "You Won!: ",300, 360);
			
			g.setFont (new Font ("Copper Black", Font.BOLD, 20)); 
			g.drawString( "Press Enter to restart ",300, 390);
		}
		
		if (ballposY > 650){										//*For some reasons this is not showing up*
			play=false; 
			ballXdir = 0; 
			ballYdir = 0; 
			g.setColor (Color.WHITE);
			g.setFont (new Font ("Copper Black", Font.BOLD, 30)); 
			g.drawString( "Game Over, Scores: "+ score,300, 360);
			
			g.setFont (new Font ("Copper Black", Font.BOLD, 20)); 
			g.drawString( "Press Enter to Restart ",300, 390);
		}
		g.dispose();
	} 
	
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if (play){
			if (new Rectangle (ballposX, ballposY, 20,20).intersects(new Rectangle(playerX, 600, 80,8))){
				ballYdir = -ballYdir; 
				
			}
			A: for (int i =0 ; i<map.map.length; i++){
				for (int j=0; j<map.map[0].length; j++){
					if (map.map[i][j]>0){
						int brickX = j* map.brickWidth+80; 
						int brickY = i*map.brickHeight+50; 
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight; 
						
						Rectangle rect = new Rectangle (brickX, brickY, brickWidth, brickHeight); 
						Rectangle ballRect = new Rectangle (ballposX, ballposY,20,20); 
						Rectangle brickRect = rect; 
						
						if (ballRect.intersects (brickRect)){
							map.setBrickValue(0, i, j);
							totalBricks--; 
							score +=5; 
							
							if (ballposX+19<=brickRect.x||ballposX+1>=brickRect.x + brickRect.width){
								ballXdir = -ballXdir; 
							}else {
								ballYdir = -ballYdir; 
								}
							break A; 
						}
						
					}
				}
			}
			ballposX += ballXdir; 
			ballposY += ballYdir; 
			//left border
				if (ballposX <0){
					ballXdir = -ballXdir; 
					}
			//top border 
				if (ballposY <0){
					ballYdir = -ballYdir; 
					}
			//right border 
				if (ballposX > 775){
					ballXdir = -ballXdir; 
					}
		}
		repaint(); 
		
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
	}
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT){
			if (playerX>= 720){
				playerX=720; 
			}else{
				moveRight (); 
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT){
			if (playerX<10){
				playerX=10;
			}else {
				moveLeft(); 
			}
		}
		if (e.getKeyCode()==KeyEvent .VK_ENTER){
			if (!play){
				play=true; 
				ballposX = 120; 
				ballposY=350; 
				ballXdir = -1; 
				ballYdir = -2; 
				playerX = 310; 
				score= 0; 
				totalBricks =24; 
				map = new MapGenerator(3,8); 
				
				repaint (); 
			}
		}
		
	}
	public void moveRight(){
		play=true;
		playerX+= 35;
				
	}
	public void moveLeft(){
		play=true;
		playerX-= 35;
				



}
}
