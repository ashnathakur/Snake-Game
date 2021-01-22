import java.awt.Color;
import java.awt.Graphics;
class Body {
	int x, y;

    public Body(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect(x, y, 10, 10);
        g.setColor(Color.black);
        g.drawRect(x, y, 10, 10);
    }
}
