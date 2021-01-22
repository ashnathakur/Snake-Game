import java.awt.Color;
import java.awt.Graphics;

class Obstacle {
	int x, y;

    public Obstacle(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 10, 10);
    }
}