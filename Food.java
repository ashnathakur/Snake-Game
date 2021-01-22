import java.awt.Color;
import java.awt.Graphics;

class Food {
	int x, y;

    public Food(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void draw(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(x, y, 10, 10);
    }
}

 
