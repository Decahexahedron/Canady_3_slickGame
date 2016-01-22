
package slickexample;

//import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Ladder {
    public int x;
    public int y;
    public boolean isvisible = true;
//    Image currentImage;
    Shape hitbox;
//    Image healthpotion = new Image("res/health_potion.png");

    Ladder(int a, int b, int width, int height) throws SlickException {
        this.x = a;
        this.y = b;
        this.hitbox = new Rectangle(a, b, width, height);
//        this.currentImage = healthpotion;

    }
}
