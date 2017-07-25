import javax.swing.JFrame;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame obj= new JFrame();
		GamePlay gamePlay=new GamePlay();
		obj.add(gamePlay);
		obj.setBounds(10, 10, 700, 600);
		obj.setTitle("Brekout Ball");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}