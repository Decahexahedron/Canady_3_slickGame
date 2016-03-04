package dungeon;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Orb {

    public int x;
    public int y;
    private int damage;
    public boolean isvisible = true;
    public int orbTime;
    Image currentImage;
    Shape hitbox;
    Image orbimage = new Image("res/Ninja_0.png");

    Orb(int a, int b) throws SlickException {
        this.isvisible = false;
        this.x = a;
        this.y = b;
        this.hitbox = new Rectangle(a, b, 16, 16);
        this.currentImage = orbimage;
        this.orbTime = 50;
    }

    public void setOrbTime(int orbTime) {
        this.orbTime = orbTime;
    }

    public int getOrbTime() {
        return orbTime;
    }

    public void setLocation(int a, int b) {

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isIsvisible() {
        return isvisible;
    }

    public Image getCurrentImage() {
        return currentImage;
    }

    public Shape getHitbox() {
        return hitbox;
    }

    public Image getHealthpotion() {
        return orbimage;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setIsvisible(boolean isvisible) {
        this.isvisible = isvisible;
    }

    public void setCurrentImage(Image currentImage) {
        this.currentImage = currentImage;
    }

    public void setHitbox(Shape hitbox) {
        this.hitbox = hitbox;
    }

    public void setHealthpotion(Image orbimage) {
        this.orbimage = orbimage;
    }

}
