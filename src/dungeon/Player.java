package dungeon;

import org.newdawn.slick.Animation;
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

    public static Animation playeranime;

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
