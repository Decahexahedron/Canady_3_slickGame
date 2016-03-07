package dungeon;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Fire {

    private boolean right = false;
    private boolean left = false;
    public int x, xOrig;
    public int y, yOrig;
    public boolean isvisible = true;
    Image currentImage;
    Shape hitbox;
    Image healthpotion = new Image("res/fire.png");

    Fire(int a, int b) throws SlickException {
        this.xOrig = a;
        this.yOrig = b;
        x = xOrig;
        y = yOrig;
        this.hitbox = new Rectangle(a, b, 11, 13);
        this.currentImage = healthpotion;

    }

    public boolean moveX() {

        if (xOrig == x) {
            return true;
        } else if (x < xOrig + 80 && true) {
            return true;
        } else if (x == xOrig + 80) {
            return false;
        } else if (x > xOrig) {
            return false;
        } else {
            return false;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}
