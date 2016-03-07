package dungeon;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Trap {

    public int x;
    public int y;
    private int damage;
//    public boolean isvisible = true;
//    public int orbTime;
    Image currentImage;
    Shape hitbox;
    Image spikeimage = new Image("res/spikes.png");

    Trap(int a, int b) throws SlickException {
//        this.isvisible = false;
        this.x = a;
        this.y = b;
        this.hitbox = new Rectangle(a, b, 16, 12);
        this.currentImage = spikeimage;
//        this.orbTime = 50;
    }
}