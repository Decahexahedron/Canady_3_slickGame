
package dungeon;

//import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Door {
    public int x;
    public int y;
    public boolean isvisible = true;
//    Image currentImage;
    Shape hitbox;
//    Image healthpotion = new Image("res/health_potion.png");

    Door(int a, int b) throws SlickException {
        this.x = a;
        this.y = b;
        this.hitbox = new Rectangle(a, b, 16,16);
//        this.currentImage = healthpotion;

    }
}
