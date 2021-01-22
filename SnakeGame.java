import javax.swing.JFrame;

public class SnakeGame extends JFrame {

	public static void main(String[] args) {
		JFrame f = new JFrame();
        Screen s = new Screen();
        f.add(s);
        f.setSize(1000,810);
        f.setVisible(true);
        f.setTitle("Snake Game");
        
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
