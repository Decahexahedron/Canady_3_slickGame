package dungeon;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Enemy {

    private static int numberOfEnemies = 0;
    boolean isvisible = true;
    private int id;
    boolean[][] eblocked = Blocked2.getblocked();
    private Animation skeleton, skup, skdown, skleft, skright, skwait, skdead;
    int SIZE = 16;
    //static int crewsize;
    float Bx;
    float By;
    float projectedright;
    float projectedleft;
    float projecteddown;
    float projectedup;
    boolean canmove;
    boolean cangoright;
    boolean cangoleft;
    boolean cangoup;
    boolean cangodown;
    int health = 100;
    Animation currentanime = new Animation();
    private float fdelta;
    int MapWidth = 50;
    int MapHeight = 100;
    double rightlimit = (MapWidth * SIZE) - (SIZE * 0.75);
    double downlimit = (MapHeight * SIZE) - (SIZE * 0.75);
    private boolean icangoup;
    private boolean icangodown;
    private boolean icangoleft;
    private boolean icangoright;
    private int startX, startY, width = 13, height = 16;
    float hitboxX = this.Bx + 8f;
    float hitboxY = this.By + 8f;
    public Shape rect;

    //System.out.println("Right limit: " + rightlimit);
    public enum Direction {

        UP, DOWN, LEFT, RIGHT, WAIT
    }

    Direction mydirection;

    Enemy(int a, int b) throws SlickException {

        Bx = a;
        By = b;
        hitboxX = this.getskhitboxX();
        hitboxY = this.getskhitboxY();
        rect = new Rectangle(hitboxX, hitboxY, width, height);
        int BHealth;
        boolean isBAlive = true;
        canmove = true;
        currentanime = skwait;
        id = ++numberOfEnemies;
        this.mydirection = Direction.WAIT;
        SpriteSheet skeletonSS = new SpriteSheet("res/zombie.png", 16, 16, 0);

        skup = new Animation();
        skup.setAutoUpdate(true);
        skup.addFrame(skeletonSS.getSprite(5, 1), 330);

        skdown = new Animation();
        skdown.setAutoUpdate(false);
        skdown.addFrame(skeletonSS.getSprite(5, 1), 330);

        skleft = new Animation();
        skleft.setAutoUpdate(false);
        skleft.addFrame(skeletonSS.getSprite(5, 1), 330);
        skleft.addFrame(skeletonSS.getSprite(4, 1), 330);
        skleft.addFrame(skeletonSS.getSprite(3, 1), 330);

        skright = new Animation();
        skright.setAutoUpdate(false);
        skright.addFrame(skeletonSS.getSprite(0, 0), 330);
        skright.addFrame(skeletonSS.getSprite(1, 0), 330);
        skright.addFrame(skeletonSS.getSprite(2, 0), 330);

        skwait = new Animation();
        skwait.setAutoUpdate(true); //turn autoupdate to false so he stops
        skwait.addFrame(skeletonSS.getSprite(5, 1), 733);

        skdead = new Animation();
        skdead.setAutoUpdate(false); //turn autoupdate to false so he stops
        skdead.addFrame(skeletonSS.getSprite(5, 0), 733);

        currentanime = skwait;

    }

    boolean isBlocked2(float xcheck, float ycheck) {

        // System.out.println("The skeleton " + this.getID() + " Checking on the tile at x " + xcheck + " and at y " + ycheck);
        int xBlock = (int) (xcheck / SIZE);

        int yBlock = (int) (ycheck / SIZE);

        if ((xBlock < MapWidth && yBlock < MapHeight) && (xBlock > 0
                && yBlock > 0)) {

            // System.out.println("Am I blocked ? " + blocked.blocked[xBlock][yBlock] );
            return Blocked2.blocked[xBlock][yBlock];

        } else {

            return true;

        }

    }

    private boolean canigoup() {

        fdelta = Combat.bplayer.getpdelta();

        return (!isBlocked2(this.Bx, this.By - fdelta)
                || !isBlocked2(this.Bx + SIZE - 1, this.By - fdelta));

    }

    private boolean canigodown() {

        fdelta = Combat.bplayer.getpdelta();

        return ((!isBlocked2(this.Bx, this.By + SIZE + 8)
                || !isBlocked2(this.Bx + SIZE - 1, this.By + SIZE + fdelta)));

    }

    private boolean canigoright() {

        return (!isBlocked2(this.Bx + SIZE + 6, this.By - 16)
                || !isBlocked2(this.Bx + SIZE + 16, this.By));

        //return true;       
    }

    private boolean canigoleft() {

        fdelta = Combat.bplayer.getpdelta();

        return (!isBlocked2(this.Bx - SIZE / 2, this.By + SIZE / 2)
                || !isBlocked2(this.Bx - SIZE, this.By)
                || !isBlocked2(this.Bx - fdelta, this.By + SIZE - 16));

    }

    void moveup() throws SlickException {

        if (this.canigoup()) {

            fdelta = Combat.bplayer.getpdelta();
            this.currentanime = skup;
//            this.By -= fdelta / 2;
//            this.rect.setLocation(this.Bx, this.By);

        } else {

            this.currentanime = skwait;

        }

    }

    void movedown() throws SlickException {

        if (this.canigodown()) {

            fdelta = Combat.bplayer.getpdelta();
            this.currentanime = skdown;
//            this.By += fdelta / 2;
//            this.rect.setLocation(this.Bx, this.By);
        }
    }

    void moveleft() throws SlickException {

        if (this.canigoleft()) {

            fdelta = Combat.bplayer.getpdelta();
            this.currentanime = skleft;
            this.Bx -= fdelta / 2;
            this.rect.setLocation(this.Bx, this.By);
        }
    }

    void moveright() throws SlickException {

        if (this.canigoright()) {

            fdelta = Combat.bplayer.getpdelta();
            this.currentanime = skright;
            this.Bx += fdelta / 2;
            this.rect.setLocation(this.Bx, this.By);
        }
    }

    void setdirection() {

        if (Combat.bplayer.getplayersY() < this.By) {
            this.mydirection = Direction.UP;
        }

        if ((Combat.bplayer.getplayersY() > this.By)) {
            this.mydirection = Direction.DOWN;
        }

        if ((Combat.bplayer.getplayersX() > this.Bx)) {
            this.mydirection = Direction.RIGHT;
        }

        if ((Combat.bplayer.getplayersX() < this.Bx) && canigoleft()) {
            this.mydirection = Direction.LEFT;
        } else {
            this.mydirection = Direction.DOWN;
        }
    }

    void move() throws SlickException {

        //float fdelta = 18 * 0.1f;
        if (true) {

            if (this.Bx > Combat.bplayer.getplayersX()) {

                this.moveleft();

            } else if (this.Bx < Combat.bplayer.getplayersX()) {

                this.moveright();

            } else {

            }

            if (this.By > Combat.bplayer.getplayersY()) {

//                this.moveup();
            } else if (this.By < Combat.bplayer.getplayersY()) {

//                this.movedown();
            } else {

                int r = (int) (Math.random() * (5 - 1)) + 1;

                //System.out.println("The number is : " + r);
                if (r == 1) {

//                    this.moveup();
                } else if (r == 2) {

//                    this.movedown();
                } else if (r == 3) {

                    this.moveleft();

                } else if (r == 4) {

                    this.moveright();

                }

            }

        } else {
            this.currentanime = skdead;
        }

    }

    public float getskX() {

        return this.Bx;

    }

    public float getskY() {

        return this.By;

    }

    public float getskhitboxX() {

        return this.Bx + 18f;

    }

    public float getskhitboxY() {

        return this.By + 18f;

    }

    public void setskhitboxX(float cBx) {

        this.hitboxX = cBx + 18f;

    }

    public void setskhitboxY(float cBy) {

        this.hitboxY = cBy + 18f;

    }

    public int getID() {

        return id;

    }

    public static int getNumberOfEnemies() {

        return numberOfEnemies;

    }

}
