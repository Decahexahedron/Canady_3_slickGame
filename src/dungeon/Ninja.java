package dungeon;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

class Ninja {

    public int x;
    public int y;
    public boolean isvisible = true;
    Image currentImage;
    Shape hitbox;
    Image ninja = new Image(
            "res/d22_ninja.png");

    Ninja(int a, int b) throws SlickException {
        this.x = a;
        this.y = b;

        this.hitbox = new Rectangle(a, b, 32, 32);// 64 is the width of the item
        this.currentImage = ninja;

    }

}
