import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;
import javax.swing.JPanel;


public class GamePlay extends JPanel implements KeyListener, ActionListener {
	private boolean play=false;
	int score=0;
	
	private int totalBricks=21;
	private Timer timer;
	private int delay=8;
	private int playerX=318;
	private int ballposX=120;
	private int ballposY=350;
	private int balldirX=-1;
	private int balldirY=-2;
	
	MapGenerator map;
	
	public GamePlay(){
		map=new MapGenerator(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer=new Timer(delay,this);
		timer.start();
	}
	public void paint(Graphics g){
		//background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
		//drawing map
		map.draw((Graphics2D)g);
		
		//borders
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
		//scores
		g.setColor(Color.white);
		g.setFont(new Font("serif",Font.BOLD,25));
		g.drawString(""+score,590,30);//the numbers identify the position of the text
		
		//the paddle
		g.setColor(Color.green);
		g.fillRect(playerX,550,100,8);
		
		//the ball
		g.setColor(Color.yellow);
		g.fillOval(ballposX,ballposY,20,20);
		
		if(totalBricks==0){
			play=false;
			g.setColor(Color.yellow);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("You are the man! YOU WON "+score, 190, 300);
		}
		
		if(ballposY>570){
			play=false;
			balldirX=0;
			balldirY=0;
			g.setColor(Color.red);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("Game over sucker, Scores: "+score, 190, 300);
			
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("Press enter to restart ", 230, 350);
		}
		
		g.dispose();
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		timer.start();
		if(play){
			if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8))){
				balldirY= -balldirY;
			}
			A: for(int i=0;  i<map.map.length; i++){//map is the object that we created from the class MapGenerator
				 for(int j=0; j<map.map[0].length; j++){
					 if(map.map[i][j] > 0){
						 int brickX=j*map.brickWidth + 80;
						 int brickY=i*map.brickHeight + 50;
						 int brickWidth=map.brickWidth;
						 int brickHeight=map.brickHeight;
						 
						 Rectangle rec= new Rectangle(brickX,brickY,brickWidth,brickHeight);
						 Rectangle ballRect=new Rectangle(ballposX,ballposY,20,20);
						 Rectangle brickRect=rec;
						 if(ballRect.intersects(brickRect)){
							 map.setBrickValue(0, i, j);
							 totalBricks--;
							 score+=5;
							 if(ballposX + 19 <= brickRect.x || ballposX +1 >= brickRect.x + brickRect.width){
								 balldirX=-balldirX;
								 
							 }
							 else{
								 balldirY=-balldirY;
							 }
							 break A;
						 }
						
						 
					 }
				 }
			 }
		
			ballposX+=balldirX;
			ballposY+=balldirY;
			if(ballposX < 0){
				balldirX= -balldirX; //this is for the left border
			}
			if(ballposY < 0){
				balldirY= -balldirY; //this is for the top border
			}
			if(ballposX > 670){
				balldirX= -balldirX; //this is for the right border
			}
		}
		repaint();//recall the paint method and execute the inside methods again
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT){//detecting the right key
			if(playerX>=600){//check if it goes outside the panel
				playerX=600;//set it inside the borders
			}
			else{
				moveRight();
			}
		}
        if(arg0.getKeyCode() == KeyEvent.VK_LEFT){//detecting the left key
        	if(playerX<10){//check if it goes outside the pannel
				playerX=10;//set it inside the borders
			}
			else{
				moveLeft();
			}
		}
        if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
        	if(!play){
        		play=true;
        		ballposX=120;
        		ballposY=350;
        		balldirX=-1;
        		balldirY=-2;
        		playerX=310;
        		score=0;
        		totalBricks=21;
        		map=new MapGenerator(3, 7);
        		repaint();
        	}
        }
		
	}
	public void moveRight(){
		play=true;
		playerX+=20;
	}
	public void moveLeft(){
		play=true;
		playerX-=20;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
