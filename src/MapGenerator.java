import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;


public class MapGenerator {
   public int map[][];
   public int brickWidth;
   public int brickHeight;
   public MapGenerator(int rows, int colls){//constructor for generating rows and colls for different number of bricks
	 map=new int[rows][colls];
	 for(int i=0;  i<3; i++){
		 for(int j=0; j<7; j++){
			 map[i][j]=1;
		 }
	 }
	 brickWidth=540/colls;
	 brickHeight=150/rows;
   }
   public void draw(Graphics2D g){
	   for(int i=0;  i<map.length; i++){
			 for(int j=0; j<map[0].length; j++){
				if( map[i][j]>0){
					g.setColor(Color.white);
					g.fillRect(j * brickWidth +80, i* brickHeight +50,brickWidth,brickHeight);
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j * brickWidth +80, i* brickHeight +50,brickWidth,brickHeight);
					
				}
			 }
	   }
   }
   public void setBrickValue(int value, int rows, int colls){
	   map[rows][colls]=value;
   }
}
