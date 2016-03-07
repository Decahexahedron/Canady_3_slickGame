package dungeon;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;

public class Combat extends BasicGameState {

    private static TiledMap battleMap;
    private static AppGameContainer app;
    private static Camera camera;
    public static int counter = 0;
    private static final int SIZE = 16;
    private static final int SCREEN_WIDTH = 1000;
    private static final int SCREEN_HEIGHT = 750;
    public boolean jumping = false;
    public static float verticalSpeed;
    public boolean ground = false;
    private boolean[][] land;
    public static Player bplayer;
    public Orb orb2;
    private static boolean orbLeft = false;
    private static boolean orbRight = false;
    public Fire fire1;
    public Trap[][] traps;
    public Enemy enemy1, enemy2, enemy3;
    public ArrayList<Enemy> enemylist = new ArrayList();
    public Door door;

    public Combat(int xSize, int ySize) {
    }

    public void init(GameContainer gc, StateBasedGame sbg)
            throws SlickException {
        DungeonChallenge.currentSpawnX = 95;
        DungeonChallenge.currentSpawnY = 95;
        gc.setTargetFrameRate(60);
        gc.setShowFPS(false);
        battleMap = new TiledMap("res/untitled.tmx");
        camera = new Camera(gc, battleMap);
        Blocked2.blocked = new boolean[battleMap.getWidth()][battleMap.getHeight()];

        for (int xAxis = 0; xAxis < battleMap.getWidth(); xAxis++) {
            for (int yAxis = 0; yAxis < battleMap.getHeight(); yAxis++) {
                int tileID = battleMap.getTileId(xAxis, yAxis, 0);
                String value = battleMap.getTileProperty(tileID, "blocked", "false");
                if ("true".equals(value)) {
                    Blocked2.blocked[xAxis][yAxis] = true;
                }
            }
        }
        land = new boolean[battleMap.getWidth()][battleMap.getHeight()];
        for (int xAxis = 0; xAxis < battleMap.getWidth(); xAxis++) {
            for (int yAxis = 0; yAxis < battleMap.getHeight(); yAxis++) {
                int tileID = battleMap.getTileId(xAxis, yAxis, 0);
                String value = battleMap.getTileProperty(tileID, "blocked", "false");
                if ("true".equals(value)) {
                    land[xAxis][yAxis] = true;
                }
            }
        }

        orb2 = new Orb((int) bplayer.x + 5, (int) bplayer.y);
        bplayer = new Player();
        fire1 = new Fire(120, 94);
//        traps[0][0] = new Trap(0,0);
        door = new Door(80, 254);
        enemy1 = new Enemy(135, 254);
        enemy2 = new Enemy(185, 254);
        enemy3 = new Enemy(215, 254);
        enemylist.add(enemy1);
        enemylist.add(enemy2);
        enemylist.add(enemy3);

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException {
        for (Enemy e : enemylist) {
            if (e.isvisible) {
                e.currentanime.draw(e.Bx, e.By);
//                g.draw(e.rect);
            }
        }
        camera.centerOn((int) bplayer.x, (int) bplayer.y);
        camera.drawMap();
        camera.translateGraphics();
        g.drawString("Health: " + bplayer.health, camera.cameraX + 10, camera.cameraY + 30);
        bplayer.sprite.draw(bplayer.x, bplayer.y);

        if (orb2.isvisible) {
            orb2.orbimage.draw(orb2.getX(), orb2.getY(), 16, 16);
        }
//        g.draw(door.hitbox);
//        g.draw(orb2.hitbox);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta)
            throws SlickException {

        if (bplayer.rect.intersects(door.hitbox)) {
            sbg.enterState(3, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }

        for (Enemy e : enemylist) {
            if (bplayer.rect.intersects(e.rect) && e.isvisible) {
                bplayer.health -= 10;
            }
        }
        if (orb2.hitbox.intersects(enemy1.rect)) {
            enemy1.isvisible = false;
            enemy1.health = 0;
            orb2.isvisible = false;
        }
        if (orb2.hitbox.intersects(enemy2.rect)) {
            enemy2.isvisible = false;
            enemy2.health = 0;
            orb2.isvisible = false;
        }
        if (orb2.hitbox.intersects(enemy3.rect)) {
            enemy3.isvisible = false;
            enemy3.health = 0;
            orb2.isvisible = false;
        }
        for (Enemy e : enemylist) {
            e.move();
        }
        if (fire1.moveX()) {
            fire1.setX(fire1.getX() + 1);
        }
        if (!fire1.moveX()) {
            fire1.setX(fire1.getX() - 1);
        }
        if (isBlocked2(bplayer.x, bplayer.y + SIZE + delta * 0.1f)) {
            ground = true;
        } else if (isLand(bplayer.x, bplayer.y + SIZE + delta * 0.1f)) {
            ground = true;
        } else {
            ground = false;
        }
        counter += delta;
        float fdelta = delta * bplayer.speed;
        Input input = gc.getInput();

        if (!input.isKeyDown(Input.KEY_SPACE) && !ground) {
            bplayer.speed = 0.22f;
            if (!isBlocked2(bplayer.x - 5, bplayer.y + SIZE + 1 + fdelta)
                    && (!isBlocked2(bplayer.x + SIZE - 1, bplayer.y + SIZE + fdelta))) {

                bplayer.sprite.update(delta);
                verticalSpeed += .08f / 6 * delta;
                bplayer.y += verticalSpeed;

            }
        } else if (ground) {
            verticalSpeed = 0;
        }

        bplayer.setpdelta(fdelta);
        double rightlimit = (battleMap.getWidth() * SIZE) - (SIZE * 0.75);
        float projectedright = bplayer.x + fdelta + SIZE;
        boolean cangoright = projectedright < rightlimit;

        if (input.isKeyDown(Input.KEY_SPACE)) {

            if (!(isBlocked2(bplayer.x, bplayer.y - fdelta) || isBlocked2((float) (bplayer.x + SIZE + 1.5), bplayer.y - fdelta))) {

                bplayer.sprite.update(delta);

                if ((input.isKeyDown(Input.KEY_SPACE)) && !jumping && !(verticalSpeed >= 0)) {
                    bplayer.sprite = bplayer.jump;
                    verticalSpeed = -.6f / 2 * delta;//negative value indicates an upward movement 
                    jumping = true;
                }

                if (jumping) {

                    verticalSpeed += .08f / 4 * delta;
                    if (ground) {

                        bplayer.y -= delta * 1f / 2;
                        verticalSpeed = 0;
                        jumping = false;
                    }
                }

                if ((ground) && !jumping) {
                    verticalSpeed = -.06f / 2;
                } else if (!(ground) && !jumping) {
                    verticalSpeed = -.023f / 2 * delta;
                }
                bplayer.y += verticalSpeed;
            }
        }
        if (input.isKeyDown(Input.KEY_UP)) {

            bplayer.sprite = bplayer.up;

        } else if (input.isKeyDown(Input.KEY_DOWN)) {

            bplayer.sprite = bplayer.down;

            if (!isBlocked2(bplayer.x - 5, bplayer.y + SIZE + 1 + fdelta)
                    && (!isBlocked2(bplayer.x + SIZE - 1, bplayer.y + SIZE + fdelta))) {
                bplayer.sprite.update(delta);
                bplayer.y += fdelta;
            }
        }
        if (input.isKeyDown(Input.KEY_LEFT)) {

            bplayer.sprite = bplayer.left;

            if (!(isBlocked2(bplayer.x - fdelta, bplayer.y)
                    || isBlocked2(bplayer.x - fdelta, bplayer.y + SIZE - 1))) {
                bplayer.sprite.update(delta);
                bplayer.x -= fdelta;
            }
        }
        if (input.isKeyDown(Input.KEY_RIGHT)) {

            bplayer.sprite = bplayer.right;

            if (cangoright && (!(isBlocked2(bplayer.x + SIZE + fdelta, bplayer.y)
                    || isBlocked2(bplayer.x + SIZE + fdelta, bplayer.y + SIZE - 1)))) {
                bplayer.sprite.update(delta);
                bplayer.x += fdelta;
            }

        }
        if (input.isKeyDown(Input.KEY_W)) {

            bplayer.sprite = bplayer.up;

        } else if (input.isKeyDown(Input.KEY_S)) {

            bplayer.sprite = bplayer.down;

            if (!isBlocked2(bplayer.x - 5, bplayer.y + SIZE + 1 + fdelta)
                    && (!isBlocked2(bplayer.x + SIZE - 1, bplayer.y + SIZE + fdelta))) {
                bplayer.sprite.update(delta);
                bplayer.y += fdelta;
            }
        }
        if (input.isKeyDown(Input.KEY_A)) {

            bplayer.sprite = bplayer.left;

            if (!(isBlocked2(bplayer.x - fdelta, bplayer.y)
                    || isBlocked2(bplayer.x - fdelta, bplayer.y + SIZE - 1))) {

                bplayer.sprite.update(delta);
                bplayer.x -= fdelta;
            }
        }
        if (input.isKeyDown(Input.KEY_D)) {

            bplayer.sprite = bplayer.right;

            if (cangoright
                    && (!(isBlocked2(bplayer.x + SIZE + fdelta,
                            bplayer.y) || isBlocked2(bplayer.x + SIZE + fdelta, bplayer.y
                            + SIZE - 1)))) {

                bplayer.sprite.update(delta);
                bplayer.x += fdelta;
            }
        }
        if (input.isKeyPressed(Input.KEY_F)) {
            orb2.setX((int) bplayer.x);
            orb2.setY((int) bplayer.y);
            orb2.hitbox.setX(orb2.getX());
            orb2.hitbox.setY(orb2.getY());
            orb2.setIsvisible(!orb2.isIsvisible());
            if (bplayer.sprite == bplayer.left) {
                orbLeft = true;
                orb2.setOrbTime(50);
            } else if (bplayer.sprite == bplayer.right) {
                orbRight = true;
                orb2.setOrbTime(50);
            }

            if (orbLeft && orbRight) {
                orb2.isvisible = false;
                orbLeft = false;
                orbRight = false;
            }
        }
        if (orb2.isvisible) {
            orb2.hitbox.setX(orb2.getX() + 5);
            orb2.hitbox.setY(orb2.getY() + 5);
        }
        if (orb2.isvisible && orbLeft) {
            if (!(isBlocked2(orb2.getX() - fdelta, bplayer.y)
                    || isBlocked2(orb2.getX() - fdelta, orb2.getY() + SIZE - 1))) {
                orb2.x -= 7;
            }
            orb2.setOrbTime(orb2.orbTime - 1);
        } else if (orb2.isvisible && orbRight) {
            if (!(isBlocked2(orb2.getX() + SIZE + fdelta,
                    orb2.getY()) || isBlocked2(orb2.getX() + SIZE + fdelta, orb2.getY() + SIZE - 1))) {
                orb2.x += 7;
                orb2.setOrbTime(orb2.orbTime - 1);
            }
        }
        if (orb2.orbTime <= 0) {
            orb2.setIsvisible(false);
        }

        if (input.isKeyDown(Input.KEY_DELETE)) {
            bplayer.health = 0;
        }

        bplayer.rect.setLocation(bplayer.getplayershitboxX(),
                bplayer.getplayershitboxY());

        if (bplayer.health <= 0) {
//            makevisible();
            sbg.enterState(6, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }
    }

    public int getID() {
        return 5;
    }

    private boolean isLand(float x, float y) {
        int xBlock = (int) x / SIZE;
        int yBlock = (int) y / SIZE;
        return land[xBlock][yBlock];
    }

    private boolean isBlocked2(float tx, float ty) {

        int xBlock = (int) tx / SIZE;
        int yBlock = (int) ty / SIZE;
        try {
            return Blocked2.blocked[xBlock][yBlock];
        } catch (IndexOutOfBoundsException e) {
            bplayer.y -= 32;
            return true;
        }
    }
}
