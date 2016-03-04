package dungeon;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

class Player {

    public static float x = 96f;
    public static float y = 220f;
    public static int health = 1000;
    public static float speed = .22f;
    static float hitboxX = x;
    static float hitboxY = y;
    private static int startX, startY, width = 16, height = 16;

    public static Shape rect = new Rectangle(getplayershitboxX(),
            getplayershitboxY(), width, height);

    public static float pdelta;
    public Animation sprite, up, down, left, right, wait, jump;
    public static Animation playeranime;

    public Player() throws SlickException {
        SpriteSheet runningSS = new SpriteSheet(
                "res/sprites.png", 16, 16, 0);

        sprite = new Animation();
        jump = new Animation();
        jump.setAutoUpdate(true);
        jump.addFrame(runningSS.getSprite(1, 5), 330);

        up = new Animation();
        up.setAutoUpdate(true);
        up.addFrame(runningSS.getSprite(3, 5), 330);

        down = new Animation();
        down.setAutoUpdate(false);
        down.addFrame(runningSS.getSprite(0, 5), 330);

        left = new Animation();
        left.setAutoUpdate(false);
        left.addFrame(runningSS.getSprite(0, 9), 130);
        left.addFrame(runningSS.getSprite(1, 9), 130);
        left.addFrame(runningSS.getSprite(2, 9), 130);

        right = new Animation();
        right.setAutoUpdate(false);
        right.addFrame(runningSS.getSprite(0, 11), 130);
        right.addFrame(runningSS.getSprite(1, 11), 130);
        right.addFrame(runningSS.getSprite(2, 11), 130);

        wait = new Animation();

        wait.setAutoUpdate(true);
        sprite = right;
    }

    public static void setpdelta(float somenum) {
        pdelta = somenum;
    }

    public static float getpdelta() {
        return pdelta;
    }

    public static float getplayersX() {
        return x;
    }

    public static float getplayersY() {
        return y;
    }

    public static float getplayershitboxX() {
        return x;
    }

    public static float getplayershitboxY() {
        return y;
    }

    public static void setplayershitboxX() {
        hitboxX = getplayershitboxX();
    }

    public static void setplayershitboxY() {
        hitboxY = getplayershitboxY();
    }
}
