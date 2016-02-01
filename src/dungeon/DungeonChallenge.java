package dungeon;

import org.newdawn.slick.state.*;

import java.io.IOException;

import java.util.ArrayList;

import java.util.Iterator;

import java.util.logging.Level;

import java.util.logging.Logger;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;

import org.newdawn.slick.AppGameContainer;

import org.newdawn.slick.BasicGame;

import org.newdawn.slick.Font;

import org.newdawn.slick.GameContainer;

import org.newdawn.slick.Graphics;

import org.newdawn.slick.Image;

import org.newdawn.slick.Input;

import org.newdawn.slick.SlickException;

import org.newdawn.slick.SpriteSheet;

import org.newdawn.slick.geom.Rectangle;

import org.newdawn.slick.geom.Shape;

import org.newdawn.slick.state.BasicGameState;

import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import org.newdawn.slick.tiled.TiledMap;

/*
 public Rhys coke;
 coke = new Rhys(100, 500);
 public ArrayList<Rhys> rhys = new ArrayList();
 coke.currentImage.draw(coke.x, coke.y);
 */
public class DungeonChallenge extends BasicGameState {

    public boolean jumping = false;
    public float verticalSpeed;
    public boolean ground = false;
    private boolean[][] land;
//    land  = new boolean[grassMap.getWidth()][grassMap.getHeight()];

    public static float currentSpawnX = 96f;
    public static float currentSpawnY = 220f;
    static int score = 0;
    public static String currentStage = "Stage 1";

    public itemwin antidote, endDoor;
    public Lava lava1, lava2, lava3, spikes1, spikes2, lava4, lava5, lava6,
            spikes3, spikes4, spikes5, spikes6, spikes7, spikes8, spikes9, end;
    public Door door1, door2, sdoor1, sdoor2, door3, sdoor3, sdoor4, door4;
    public Ladder ladder1, ladder2, ladder3, ladder4, ladder5, ladder6, ladder7;

    public ArrayList<Item> stuff = new ArrayList();

    public ArrayList<item1> stuff1 = new ArrayList();

    public ArrayList<itemwin> stuffwin = new ArrayList();

    public ArrayList<Ninja> ninjaz = new ArrayList();

    public ArrayList<Rhys> rhys = new ArrayList();

    public ArrayList<Lava> lavalist = new ArrayList();

    public ArrayList<Ladder> ladderlist = new ArrayList();

//    public ArrayList<Enemy> enemies = new ArrayList();
    private boolean[][] hostiles;

    private static TiledMap grassMap;

    private static AppGameContainer app;

    private static Camera camera;

    public static int counter = 0;

    // Player stuff
    private Animation sprite, up, down, left, right, wait, jump;

    /**
     *
     * The collision map indicating which tiles block movement - generated based
     *
     * on tile properties
     */
    // changed to match size of sprites & map
    private static final int SIZE = 16;

    // screen width and height won't change
    private static final int SCREEN_WIDTH = 1000;

    private static final int SCREEN_HEIGHT = 750;

    public DungeonChallenge(int xSize, int ySize) {

    }

    public void init(GameContainer gc, StateBasedGame sbg)
            throws SlickException {

        gc.setTargetFrameRate(60);

        gc.setShowFPS(false);

        // *******************
        // Scenerey Stuff
        // ****************
        grassMap = new TiledMap("res/platform.tmx");

        // Ongoing checks are useful
        System.out.println("Tile map is this wide: " + grassMap.getWidth());

        camera = new Camera(gc, grassMap);

        // *********************************************************************************
        // Player stuff --- these things should probably be chunked into methods
        // and classes
        // *********************************************************************************
        SpriteSheet runningSS = new SpriteSheet(
                "res/sprites.png", 16, 16, 0);

        // System.out.println("Horizontal count: "
        // +runningSS.getHorizontalCount());
        // System.out.println("Vertical count: " +runningSS.getVerticalCount());
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

//        wait.addFrame(runningSS.getSprite(0, 14), 733);
        // wait.addFrame(runningSS.getSprite(2, 14), 733);
        // wait.addFrame(runningSS.getSprite(5, 14), 333);
        sprite = down;

        // *****************************************************************
        // Obstacles etc.
        // build a collision map based on tile properties in the TileD map
        Blocked.blocked = new boolean[grassMap.getWidth()][grassMap.getHeight()];
//        Deadly.deadly = new boolean[grassMap.getWidth()][grassMap.getHeight()];

        // System.out.println("Map height:" + grassMap.getHeight());750
        // System.out.println("Map width:" + grassMap.getWidth());
        // There can be more than 1 layer. You'll check whatever layer has the
        // obstacles.
        // You could also use this for planning traps, etc.
        // System.out.println("Number of tile layers: "
        // +grassMap.getLayerCount());
        System.out.println("The grassmap is " + grassMap.getWidth() + "by "
                + grassMap.getHeight());

        for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {

            for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {

                // int tileID = grassMap.getTileId(xAxis, yAxis, 0);
                // Why was this changed?
                // It's a Different Layer.
                // You should read the TMX file. It's xml, i.e.,human-readable
                // for a reason
                int tileID = grassMap.getTileId(xAxis, yAxis, 0);

                String value = grassMap.getTileProperty(tileID,
                        "blocked", "false");

                if ("true".equals(value)) {

                    System.out.println("The tile at x " + xAxis + " andy axis "
                            + yAxis + " is blocked.");

                    Blocked.blocked[xAxis][yAxis] = true;

                }

            }

        }
        for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {
            for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {
                int tileID = grassMap.getTileId(xAxis, yAxis, 0);
                String value = grassMap.getTileProperty(tileID, "blocked", "false");
                if ("true".equals(value)) {
                    Blocked.blocked[xAxis][yAxis] = true;
                }
            }
        }
        land = new boolean[grassMap.getWidth()][grassMap.getHeight()];
        for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {
            for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {
                int tileID = grassMap.getTileId(xAxis, yAxis, 0);
                String value = grassMap.getTileProperty(tileID, "blocked", "false");
                if ("true".equals(value)) {
                    land[xAxis][yAxis] = true;
                }
            }
        }
        System.out.println("Array length" + Blocked.blocked[0].length);

        // A remarkably similar process for finding hostiles
        hostiles = new boolean[grassMap.getWidth()][grassMap.getHeight()];

        for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {
            for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {
                int xBlock = (int) xAxis;
                int yBlock = (int) yAxis;
                if (!Blocked.blocked[xBlock][yBlock]) {
                    if (yBlock % 7 == 0 && xBlock % 15 == 0) {
                        Item i = new Item(xAxis * SIZE, yAxis * SIZE);
                        stuff.add(i);
                        //stuff1.add(h);
                        hostiles[xAxis][yAxis] = true;
                    }
                }
            }
        }

        for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {
            for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {
                int xBlock = (int) xAxis;
                int yBlock = (int) yAxis;
                if (!Blocked.blocked[xBlock][yBlock]) {
                    if (xBlock % 9 == 0 && yBlock % 25 == 0) {
                        item1 h = new item1(xAxis * SIZE, yAxis * SIZE);
                        //	stuff.add(i);
                        stuff1.add(h);
                        hostiles[xAxis][yAxis] = true;
                    }
                }
            }
        }

//        healthpotion = new Item(100, 100);
//        healthpotion1 = new Item(450, 400);
//        stuff.add(healthpotion);
//        stuff.add(healthpotion1);
        door1 = new Door(690, 174);
        door2 = new Door(607, 684);
        sdoor1 = new Door(340, 671);
        sdoor2 = new Door(720, 1245);
        door3 = new Door(670, 1711);
        sdoor3 = new Door(308, 2144);
        sdoor4 = new Door(111, 2894);
        door4 = new Door(416, 2159);

        lava1 = new Lava(481, 240, 80, 15);
        lava2 = new Lava(256, 705, 288, 15);
        lava3 = new Lava(81, 1280, 672, 15);
        spikes1 = new Lava(160, 692, 32, 11);
        spikes2 = new Lava(162, 1716, 142, 11);
        lava4 = new Lava(352, 1728, 96, 15);
        lava5 = new Lava(496, 1728, 144, 15);
        lava6 = new Lava(128, 2192, 304, 15);
        spikes3 = new Lava(294, 2129, 11, 14);
        spikes4 = new Lava(81, 2788, 64, 11);
        spikes5 = new Lava(129, 2672, 11, 16);
        spikes6 = new Lava(148, 2639, 11, 63);
        spikes7 = new Lava(225, 2788, 16, 11);
        spikes8 = new Lava(176, 2799, 11, 80);
        spikes9 = new Lava(213, 2800, 11, 96);
        end = new Lava(80, 3966, 616, 16);

        ladder1 = new Ladder(609, 176, 15, 64);
        ladder2 = new Ladder(705, 1249, 15, 16);
        ladder3 = new Ladder(161, 1664, 144, 16);
        ladder4 = new Ladder(433, 1712, 15, 16);
        ladder5 = new Ladder(545, 1665, 15, 16);
        ladder6 = new Ladder(577, 1680, 15, 16);
        ladder7 = new Ladder(609, 1696, 15, 16);

        lavalist.add(lava1);
        lavalist.add(lava2);
        lavalist.add(lava3);
        lavalist.add(spikes1);
        lavalist.add(spikes2);
        lavalist.add(lava4);
        lavalist.add(lava5);
        lavalist.add(lava6);
        lavalist.add(spikes3);
        lavalist.add(spikes4);
        lavalist.add(spikes5);
        lavalist.add(spikes6);
        lavalist.add(spikes7);
        lavalist.add(spikes8);
        lavalist.add(spikes9);
        lavalist.add(end);

        ladderlist.add(ladder1);
        ladderlist.add(ladder2);
        ladderlist.add(ladder3);
        ladderlist.add(ladder4);
        ladderlist.add(ladder5);
        ladderlist.add(ladder6);
        ladderlist.add(ladder7);

        antidote = new itemwin(3004, 92);
        endDoor = new itemwin(447, 3726);
        stuffwin.add(endDoor);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException {

        camera.centerOn((int) Player.x, (int) Player.y);

        camera.drawMap();

        camera.translateGraphics();

//        g.draw(sdoor1.hitbox);
        sprite.draw((int) Player.x, (int) Player.y);
//        g.drawString("x: " + (int) Player.x + " y: " + (int) Player.y, Player.x, Player.y - 10);
        g.drawString("Score: " + score, camera.cameraX + 10, camera.cameraY + 10);
        g.drawString("Current Stage: " + currentStage, camera.cameraX + 10, camera.cameraY + 25);
        //g.draw(player.rect);
//        g.drawString("time passed: " + counter / 1000, camera.cameraX + 600, camera.cameraY);
        // moveenemies();

//        for (Lava l : lavalist) {
//            g.draw(l.hitbox);
//        }
//
//        for (Ladder l : ladderlist) {
//            g.draw(l.hitbox);
//        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta)
            throws SlickException {

//        while (gravity) {
//            Player.y -= 1f;
//        }
        if (isBlocked(Player.x, Player.y + SIZE + delta * 0.1f)) {
            ground = true;
        } else if (isLand(Player.x, Player.y + SIZE + delta * 0.1f)) {
            ground = true;
        } else {
            ground = false;
        }
        counter += delta;

        float fdelta = delta * Player.speed;

        Input input = gc.getInput();

        if (!input.isKeyDown(Input.KEY_SPACE) && !Player.rect.intersects(ladder1.hitbox)
                && !Player.rect.intersects(ladder2.hitbox) && !Player.rect.intersects(ladder3.hitbox)
                && !Player.rect.intersects(ladder4.hitbox) && !Player.rect.intersects(ladder5.hitbox)
                && !Player.rect.intersects(ladder6.hitbox) && !Player.rect.intersects(ladder7.hitbox)) {
            Player.speed = 0.22f;
//            verticalSpeed = 2;
//            Player.y += verticalSpeed;
//            sprite.update(delta);
            if (!isBlocked(Player.x - 5, Player.y + SIZE + 1 + fdelta)
                    && (!isBlocked(Player.x + SIZE - 1, Player.y + SIZE + fdelta))) {

                sprite.update(delta);
                verticalSpeed += .08f / 4 * delta;
                Player.y += verticalSpeed;
//                Player.y += fdelta;

            }
        }

        Player.setpdelta(fdelta);

        double rightlimit = (grassMap.getWidth() * SIZE) - (SIZE * 0.75);

        // System.out.println("Right limit: " + rightlimit);
        float projectedright = Player.x + fdelta + SIZE;

        boolean cangoright = projectedright < rightlimit;

        if (input.isKeyDown(Input.KEY_SPACE)) {
//            Player.speed = 0.35f;
//            float fdsc = (float) (fdelta - (SIZE * 1));
            if (!(isBlocked(Player.x, Player.y - fdelta) || isBlocked((float) (Player.x + SIZE + 1.5), Player.y - fdelta))) {

                sprite.update(delta);

                // The lower the delta the slower the sprite will animate.
//                Player.y -= fdelta;
                if ((input.isKeyDown(Input.KEY_SPACE)) && !jumping && !(verticalSpeed >= 0)) {
                    sprite = jump;
//                    System.out.println("jumped??");
                    verticalSpeed = -.6f / 2 * delta;//negative value indicates an upward movement 
                    jumping = true;
                }

                if (jumping) {
//                    delta++;
//                    System.out.println("changing vspeed??");
                    verticalSpeed += .08f / 4 * delta;//change this value to alter gravity strength 

                    if (ground) {
                        // The lower the delta the slowest the sprite will animate.
//                        System.out.println("idk");
                        Player.y -= delta * 1f / 2;
                        verticalSpeed = 0;
                        jumping = false;

                    }

                }

                if ((ground) && !jumping) {
//                    System.out.println("i am midair");
                    verticalSpeed = -.06f / 2;
                } else if (!(ground) && !jumping) {
//                    System.out.println("im starting a jump");
                    verticalSpeed = -.023f / 2 * delta;
                }
//                if(!isBlocked(Player.x - 5, Player.y + SIZE + 1 + fdelta)
//                    && (!isBlocked(Player.x + SIZE - 1, Player.y + SIZE + fdelta))){
                Player.y += verticalSpeed;

            }
        }
        if (input.isKeyDown(Input.KEY_UP)) {

            sprite = up;
            for (Ladder l : ladderlist) {

                if (Player.rect.intersects(l.hitbox)
                        && !(isBlocked(Player.x, Player.y - fdelta) || isBlocked(
                                (float) (Player.x + SIZE + 1.5), Player.y - fdelta))) {

                    sprite.update(delta);

                    // The lower the delta the slower the sprite will animate.
                    Player.y -= fdelta;

                }

            }

        } else if (input.isKeyDown(Input.KEY_DOWN)) {

            sprite = down;

            if (!isBlocked(Player.x - 5, Player.y + SIZE + 1 + fdelta)
                    && (!isBlocked(Player.x + SIZE - 1, Player.y + SIZE + fdelta))) {

                sprite.update(delta);

                Player.y += fdelta;

            }

        }
        if (input.isKeyDown(Input.KEY_LEFT)) {

            sprite = left;

            if (!(isBlocked(Player.x - fdelta, Player.y)
                    || isBlocked(Player.x - fdelta, Player.y + SIZE - 1))) {

                sprite.update(delta);

                Player.x -= fdelta;

            }

        }
        if (input.isKeyDown(Input.KEY_RIGHT)) {

            sprite = right;

            // the boolean-kludge-implementation
            if (cangoright
                    && (!(isBlocked(Player.x + SIZE + fdelta,
                            Player.y) || isBlocked(Player.x + SIZE + fdelta, Player.y
                            + SIZE - 1)))) {

                sprite.update(delta);

                Player.x += fdelta;

            } // else { System.out.println("Right limit reached: " +
            // rightlimit);}

        }
        if (input.isKeyPressed(Input.KEY_DELETE)) {
            Player.health = 0;
        }

        Player.rect.setLocation(Player.getplayershitboxX(),
                Player.getplayershitboxY());

        if (Player.rect.intersects(door1.hitbox)) {
            Player.x = 96;
            Player.y = 685;
            currentSpawnX = 96f;
            currentSpawnY = 685f;
            score += 1;
            currentStage = "Stage 2";
        }
        if (Player.rect.intersects(sdoor1.hitbox)) {
            Player.x = 110;
            Player.y = 1245;
            currentSpawnX = 110;
            currentSpawnY = 1245;
            score += 2;
            currentStage = "Secret Stage 1";
        }
        if (Player.rect.intersects(door2.hitbox)) {
            Player.x = 96;
            Player.y = 1706;
            currentSpawnX = 96f;
            currentSpawnY = 1706f;
            score += 1;
            currentStage = "Stage 3";
        }
        if (Player.rect.intersects(sdoor2.hitbox)) {
            Player.x = 96;
            Player.y = 1706;
            currentSpawnX = 96f;
            currentSpawnY = 1706f;
            score += 3;
            currentStage = "Stage 3";
        }
        if (Player.rect.intersects(door3.hitbox)) {
            Player.x = 92;
            Player.y = 2168;
            currentSpawnX = 96f;
            currentSpawnY = 2170f;
            score += 1;
            currentStage = "Stage 4";
        }
        if (Player.rect.intersects(sdoor3.hitbox)) {
            Player.x = 112;
            Player.y = 2621;
            currentSpawnX = 112f;
            currentSpawnY = 2621f;
            score += 2;
            currentStage = "Special Stage 2";
        }
        if (Player.rect.intersects(sdoor4.hitbox)) {
            Player.x = 102;
            Player.y = 3309;
            currentSpawnX = 102f;
            currentSpawnY = 3309f;
            score += 3;
            currentStage = "Stage 5";
        }
        if (Player.rect.intersects(door4.hitbox)) {
            Player.x = 102;
            Player.y = 3309;
            currentSpawnX = 102f;
            currentSpawnY = 3309f;
            score += 1;
            currentStage = "Stage 5";
        }

//        if(Player.rect.intersects(lava1.hitbox)){
//            Player.health =0;
//        }
        for (Lava l : lavalist) {

            if (Player.rect.intersects(l.hitbox)) {
                //System.out.println("yay");

                Player.health = 0;
//                    l.isvisible = false;

            }
        }

        for (itemwin w : stuffwin) {

            if (Player.rect.intersects(w.hitbox)) {
                //System.out.println("yay");
                if (w.isvisible) {
                    w.isvisible = false;
                    makevisible();
                    sbg.enterState(3, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));

                }

            }
        }

//        Player.health -= counter / 1000;
        if (Player.health <= 0) {
            makevisible();
            sbg.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }

    }

    public int getID() {

        return 1;

    }

    public void makevisible() {
        for (item1 h : stuff1) {

            h.isvisible = true;
        }

        for (Item i : stuff) {

            i.isvisible = true;
        }
    }

    private boolean isLand(float x, float y) {
        int xBlock = (int) x / SIZE;
        int yBlock = (int) y / SIZE;
        return land[xBlock][yBlock];
    }

    private boolean isBlocked(float tx, float ty) {

        int xBlock = (int) tx / SIZE;

        int yBlock = (int) ty / SIZE;
        try {
            return Blocked.blocked[xBlock][yBlock];
        } catch (IndexOutOfBoundsException e) {
            // this could make a better kludge
            System.out.println("whoops");
            Player.y -= 32;
            return true;
        }
    }
}
