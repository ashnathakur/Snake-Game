import static java.lang.String.format;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

 class Screen extends JPanel implements ActionListener, KeyListener{
	int WIDTH = 800, HEIGHT = 800;
    public static int time = 50;
    Timer t;
    int x = 400, y = 400;
    boolean right = false, left = false, up = false, down = false;
    public static int score = 0;
    
    LinkedList<Food> foods;
    LinkedList<Body> snake;
    LinkedList<Obstacle> block;

    Body b;
    Food f;
    Obstacle k;
   
    
    final static Font mainFont = new Font("Monospaced", Font.BOLD, 48);
    final static Font smallFont = mainFont.deriveFont(Font.BOLD, 18);
    
    Random rand = new Random();
    int size = 3;

    public Screen() {

        init();
    }


    public void init() {
    	
        block = new LinkedList<Obstacle>();
        foods = new LinkedList<Food>();
        snake = new LinkedList<Body>();
        
        
        level();
        t = new Timer(time, this);
        t.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public void tracking() {
    	if(score<60)
    	{
    		
    		if(x>780)
    	       	x=0;
    	       	else if(x<2)
    	       		x=780;
    	       	if( y>780)
    	           	y=0;
    	       	else if(y<2)
    	       		y=780;
    	       	
    	}
    	else if(score>=60){
        if (x < 0 || x > 800 || y < 0 || y > 800) {
            hit();
        }
    	}
    }

    public void level() {
        
        if(score<20)
    	{JOptionPane.showMessageDialog(null, "You entered the Level 1");
        time=100;}
        else if(score==20)
        {
        	JOptionPane.showMessageDialog(null, "You entered the Level 2");
            
        }
        else if(score==40)
        {
        	JOptionPane.showMessageDialog(null, "You entered the Level 3\n You now entering the MINE LAND");
            
        }
        else if(score==60)
        {
        	JOptionPane.showMessageDialog(null, "You entered the Level 4\n You now entrapped in the box");
            
        }
        else if(score==80)
        {
        	JOptionPane.showMessageDialog(null, "You entered the Level 5\n Just one more level to Win");
            
        }
        else if(score==100)
        {
        	JOptionPane.showMessageDialog(null, "You WON!!!");
        	System.exit(0);
        	
        }
        
    }

    public void hit() {
        t.stop();
        JOptionPane.showInternalMessageDialog(null, "You Lose!");
            System.exit(0);
        
    }

    public void move() {
        if (right) x += 10;
        if (left) x -= 10;
        if (up) y -= 10;
        if (down) y += 10;
    }

    public void snake() {
        if (snake.size() == 0) {
            b = new Body(x, y);
            snake.add(b);
        }
        move();
        b = new Body(x, y);
        snake.add(b);

        if (snake.size() > size) {
            snake.remove(0);
        }
      
        for (int i = 4; i < snake.size(); i++) {
            if (snake.get(i).x == snake.get(0).x && snake.get(i).y == snake.get(0).y) {
                hit();
               
            }
        }
    }

    public void food() {
        if (foods.size() == 0) {
            int ok = 0;
            int rx = 0;
            int ry = 0;
            while (ok != 1) {
                rx = (int) (rand.nextInt(600) + 1);
                ry = (int) (rand.nextInt(600) + 1);

                if ((rx % 10) == 0 && (ry % 10) == 0) {
                    ok = 1;
                } 
            }
            f = new Food(rx, ry);
            foods.add(f);
        }

        if (snake.get(snake.size() - 1).x == foods.get(0).x && snake.get(snake.size() - 1).y == foods.get(0).y) {
            foods.remove();
            size++;
            score += 10;
            if(score==20||score==40||score==60||score==80||score==100)
            	level();
        }
    }

    public void block() {
        if(block.size()==0)
        {
            int ok = 0;
            int rx = 0;
            int ry = 0;
            while (ok != 15) {
                rx = (int) (rand.nextInt(750) + 1);
                ry = (int) (rand.nextInt(750) + 1);

                if ((rx % 10) == 0 && (ry % 10) == 0) {
                    k = new Obstacle(rx, ry);
                    block.add(k);
                    int temp = 10;

                    int r = (int) (rand.nextInt(2) + 1);

                    for (int i = 0; i < 20; i++) {

                        if (r == 1) {
                            k = new Obstacle(rx + temp , ry);
                        } else if (r == 2) {
                            k = new Obstacle(rx , ry + temp);
                        }
                        block.add(k);

                        if ((k.x == foods.get(0).x && k.y == foods.get(0).y) || 
                            (k.x == 400 && k.y == 400) || 
                            ((k.x >= 350 && k.x <= 450) && (k.y >= 350 && k.y <= 450)))  {
                            block.remove(k);
                        }

                        temp += 10;
                    }
                    ok++;
                } 
            }
        }

        for (int i = 0; i < block.size(); i++) {
            if (snake.get(snake.size() - 1).x == block.get(i).x && snake.get(snake.size() - 1).y == block.get(i).y) {
                hit();
               
            }
        }
    }
    
  
    public int getLevel()
    {int level=1;
    if(score>=20&&score<40)
    	level=2;
    if(score>=40&&score<60)
    	level=3;
    if(score>=60&&score<80)
    	level=4;
    if(score>=80&&score<100)
    	level=5;
    
    	return level;
    }

    public void update() {
        if(score<40)
    	{snake();
        food();}
        
        if(score>=40)
        {
        	snake();
            food();	
            block();
        }
        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.BLACK);
        for (int i = 1; i < WIDTH / 10; i++) {
            g.drawLine(i * 10, 0, i * 10, HEIGHT);
        }
        for (int i = 1; i < HEIGHT / 10; i++) {
            g.drawLine(0, i * 10, WIDTH, i * 10);
        }

        for (int i = 0; i < snake.size(); i++) {
            snake.get(i).draw(g);
        }

        for (int i = 0; i < foods.size(); i++) {
            foods.get(i).draw(g);
        }

        for (int i = 0; i < block.size(); i++) {
            block.get(i).draw(g);
        } 
      
        
        Graphics2D g2=(Graphics2D) g;
        int x = 820;
        int y = 400;
        g2.setColor(Color.BLACK);
        g2.setFont(smallFont);
        g2.drawString(format("SCORE      %d", score), x, y + 30);
        g2.drawString(format("LEVEL      %d", getLevel()), x, y + 60);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        tracking();
        repaint(); 
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT && left == false) { 
            up = false;
            down = false;
            right = true;

        }
        if (key == KeyEvent.VK_LEFT && right == false) { 
            up = false;
            down = false;
            left = true;
        }
        if (key == KeyEvent.VK_UP && down == false) {
            left = false;
            right = false;
            up = true;
        }
        if (key == KeyEvent.VK_DOWN && up == false) {
            left = false;
            right = false;
            down = true;
        } 
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

 
 