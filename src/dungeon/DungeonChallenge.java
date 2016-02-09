package dungeon;

import org.newdawn.slick.state.*;
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

public class DungeonChallenge extends BasicGameState {

    public static boolean orbLeft = false;
    public static boolean orbRight = false;
    public Orb orb1;
    public Player player;
    public boolean jumping = false;
    public static float verticalSpeed;
    public boolean ground = false;
    private boolean[][] land;

    public static float currentSpawnX = 96f;
    public static float currentSpawnY = 220f;
    static int score = 0;
    public static String currentStage = "Stage 1";

    public itemwin antidote, endDoor;
    public Lava lava1, lava2, lava3, spikes1, spikes2, lava4, lava5, lava6,
            spikes3, spikes4, spikes5, spikes6, spikes7, spikes8, spikes9, end;
    public Door door1, door2, sdoor1, sdoor2, door3, sdoor3, sdoor4, door4;
    public Ladder ladder1, ladder2, ladder3, ladder4, ladder5, ladder6, ladder7;

    public ArrayList<itemwin> stuffwin = new ArrayList();
    public ArrayList<Lava> lavalist = new ArrayList();
    public ArrayList<Ladder> ladderlist = new ArrayList();
    public ArrayList<Orb> orblist = new ArrayList();

    private boolean[][] hostiles;
    private static TiledMap grassMap;
    private static AppGameContainer app;
    private static Camera camera;
    public static int counter = 0;
    private static final int SIZE = 16;
    private static final int SCREEN_WIDTH = 1000;
    private static final int SCREEN_HEIGHT = 750;

    public DungeonChallenge(int xSize, int ySize) {

    }

    public void init(GameContainer gc, StateBasedGame sbg)
            throws SlickException {

        gc.setTargetFrameRate(60);
        gc.setShowFPS(false);
        grassMap = new TiledMap("res/platform.tmx");
        camera = new Camera(gc, grassMap);
        Blocked.blocked = new boolean[grassMap.getWidth()][grassMap.getHeight()];

        for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {
            for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {
                int tileID = grassMap.getTileId(xAxis, yAxis, 0);
                String value = grassMap.getTileProperty(tileID,
                        "blocked", "false");
                if ("true".equals(value)) {
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

        orb1 = new Orb((int) player.x + 5, (int) player.y);
        orblist.add(orb1);

        player = new Player();
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

//        antidote = new itemwin(3004, 92);
        endDoor = new itemwin(447, 3726);
        stuffwin.add(endDoor);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException {

        camera.centerOn((int) player.x, (int) player.y);
        camera.drawMap();
        camera.translateGraphics();
        player.sprite.draw(player.x, player.y);
        g.drawString("Score: " + score, camera.cameraX + 10, camera.cameraY + 10);
        g.drawString("Current Stage: " + currentStage, camera.cameraX + 10, camera.cameraY + 25);
        if (orb1.isvisible) {
            orb1.orbimage.draw(orb1.getX(), orb1.getY(), 16, 16);
        }

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta)
            throws SlickException {

        if (isBlocked(player.x, player.y + SIZE + delta * 0.1f)) {
            ground = true;
        } else if (isLand(player.x, player.y + SIZE + delta * 0.1f)) {
            ground = true;
        } else {
            ground = false;
        }
        counter += delta;

        float fdelta = delta * player.speed;

        Input input = gc.getInput();

        if (!input.isKeyDown(Input.KEY_SPACE) && !player.rect.intersects(ladder1.hitbox)
                && !player.rect.intersects(ladder2.hitbox) && !player.rect.intersects(ladder3.hitbox)
                && !player.rect.intersects(ladder4.hitbox) && !player.rect.intersects(ladder5.hitbox)
                && !player.rect.intersects(ladder6.hitbox) && !player.rect.intersects(ladder7.hitbox) && !ground) {
            player.speed = 0.22f;
            if (!isBlocked(player.x - 5, player.y + SIZE + 1 + fdelta)
                    && (!isBlocked(player.x + SIZE - 1, player.y + SIZE + fdelta))) {

                player.sprite.update(delta);
                verticalSpeed += .08f / 6 * delta;
                player.y += verticalSpeed;

            }
        } else if (ground) {
            verticalSpeed = 0;
        }

        player.setpdelta(fdelta);

        double rightlimit = (grassMap.getWidth() * SIZE) - (SIZE * 0.75);

        float projectedright = player.x + fdelta + SIZE;

        boolean cangoright = projectedright < rightlimit;

        if (input.isKeyDown(Input.KEY_SPACE)) {

            if (!(isBlocked(player.x, player.y - fdelta) || isBlocked((float) (player.x + SIZE + 1.5), player.y - fdelta))) {

                player.sprite.update(delta);

                if ((input.isKeyDown(Input.KEY_SPACE)) && !jumping && !(verticalSpeed >= 0)) {
                    player.sprite = player.jump;
                    verticalSpeed = -.6f / 2 * delta;//negative value indicates an upward movement 
                    jumping = true;
                }

                if (jumping) {

                    verticalSpeed += .08f / 4 * delta;
                    if (ground) {

                        player.y -= delta * 1f / 2;
                        verticalSpeed = 0;
                        jumping = false;

                    }

                }

                if ((ground) && !jumping) {
                    verticalSpeed = -.06f / 2;
                } else if (!(ground) && !jumping) {
                    verticalSpeed = -.023f / 2 * delta;
                }
                player.y += verticalSpeed;

            }
        }
        if (input.isKeyDown(Input.KEY_UP)) {

            player.sprite = player.up;
            for (Ladder l : ladderlist) {

                if (player.rect.intersects(l.hitbox)
                        && !(isBlocked(player.x, player.y - fdelta) || isBlocked(
                                (float) (player.x + SIZE + 1.5), player.y - fdelta))) {

                    player.sprite.update(delta);
                    player.y -= fdelta;

                }

            }

        } else if (input.isKeyDown(Input.KEY_DOWN)) {

            player.sprite = player.down;

            if (!isBlocked(player.x - 5, player.y + SIZE + 1 + fdelta)
                    && (!isBlocked(player.x + SIZE - 1, player.y + SIZE + fdelta))) {

                player.sprite.update(delta);

                player.y += fdelta;

            }

        }
        if (input.isKeyDown(Input.KEY_LEFT)) {

            player.sprite = player.left;

            if (!(isBlocked(player.x - fdelta, player.y)
                    || isBlocked(player.x - fdelta, player.y + SIZE - 1))) {

                player.sprite.update(delta);

                player.x -= fdelta;

            }

        }
        if (input.isKeyDown(Input.KEY_RIGHT)) {

            player.sprite = player.right;

            if (cangoright
                    && (!(isBlocked(player.x + SIZE + fdelta,
                            player.y) || isBlocked(player.x + SIZE + fdelta, player.y
                            + SIZE - 1)))) {

                player.sprite.update(delta);

                player.x += fdelta;

            }

        }
        if (input.isKeyDown(Input.KEY_W)) {

            player.sprite = player.up;
            for (Ladder l : ladderlist) {

                if (player.rect.intersects(l.hitbox)
                        && !(isBlocked(player.x, player.y - fdelta) || isBlocked(
                                (float) (player.x + SIZE + 1.5), player.y - fdelta))) {

                    player.sprite.update(delta);
                    player.y -= fdelta;

                }

            }

        } else if (input.isKeyDown(Input.KEY_S)) {

            player.sprite = player.down;

            if (!isBlocked(player.x - 5, player.y + SIZE + 1 + fdelta)
                    && (!isBlocked(player.x + SIZE - 1, player.y + SIZE + fdelta))) {

                player.sprite.update(delta);

                player.y += fdelta;

            }

        }
        if (input.isKeyDown(Input.KEY_A)) {

            player.sprite = player.left;

            if (!(isBlocked(player.x - fdelta, player.y)
                    || isBlocked(player.x - fdelta, player.y + SIZE - 1))) {

                player.sprite.update(delta);

                player.x -= fdelta;

            }

        }
        if (input.isKeyDown(Input.KEY_D)) {

            player.sprite = player.right;

            if (cangoright
                    && (!(isBlocked(player.x + SIZE + fdelta,
                            player.y) || isBlocked(player.x + SIZE + fdelta, player.y
                            + SIZE - 1)))) {

                player.sprite.update(delta);

                player.x += fdelta;

            }

        }
        if (input.isKeyPressed(Input.KEY_F)) {
            orb1.setX((int) player.x);
            orb1.setY((int) player.y);
            orb1.hitbox.setX(orb1.getX());
            orb1.hitbox.setY(orb1.getY());
            orb1.setIsvisible(!orb1.isIsvisible());
            if (player.sprite == player.left) {
                orbLeft = true;
            } else if (player.sprite == player.right) {
                orbRight = true;
            }

            if (orbLeft && orbRight) {
                orb1.isvisible = false;
                orbLeft = false;
                orbRight = false;
            }
        }
        if (orb1.isvisible && orbLeft) {
            orb1.x -= 7;
        } else if (orb1.isvisible && orbRight) {
            orb1.x += 7;
        }

        if (input.isKeyPressed(Input.KEY_DELETE)) {
            player.health = 0;
        }

        player.rect.setLocation(player.getplayershitboxX(),
                player.getplayershitboxY());

        if (player.rect.intersects(door1.hitbox)) {
            player.x = 96;
            player.y = 685;
            currentSpawnX = 96f;
            currentSpawnY = 685f;
            score += 1;
            currentStage = "Stage 2";
        }
        if (player.rect.intersects(sdoor1.hitbox)) {
            player.x = 110;
            player.y = 1245;
            currentSpawnX = 110;
            currentSpawnY = 1245;
            score += 2;
            currentStage = "Secret Stage 1";
        }
        if (player.rect.intersects(door2.hitbox)) {
            player.x = 96;
            player.y = 1706;
            currentSpawnX = 96f;
            currentSpawnY = 1706f;
            score += 1;
            currentStage = "Stage 3";
        }
        if (player.rect.intersects(sdoor2.hitbox)) {
            player.x = 96;
            player.y = 1706;
            currentSpawnX = 96f;
            currentSpawnY = 1706f;
            score += 3;
            currentStage = "Stage 3";
        }
        if (player.rect.intersects(door3.hitbox)) {
            player.x = 92;
            player.y = 2168;
            currentSpawnX = 96f;
            currentSpawnY = 2170f;
            score += 1;
            currentStage = "Stage 4";
        }
        if (player.rect.intersects(sdoor3.hitbox)) {
            player.x = 112;
            player.y = 2621;
            currentSpawnX = 112f;
            currentSpawnY = 2621f;
            score += 2;
            currentStage = "Special Stage 2";
        }
        if (player.rect.intersects(sdoor4.hitbox)) {
            player.x = 102;
            player.y = 3309;
            currentSpawnX = 102f;
            currentSpawnY = 3309f;
            score += 3;
            currentStage = "Stage 5";
        }
        if (player.rect.intersects(door4.hitbox)) {
            player.x = 102;
            player.y = 3309;
            currentSpawnX = 102f;
            currentSpawnY = 3309f;
            score += 1;
            currentStage = "Stage 5";
        }

        for (Lava l : lavalist) {

            if (player.rect.intersects(l.hitbox)) {
                player.health = 0;

            }
        }

        for (itemwin w : stuffwin) {

            if (player.rect.intersects(w.hitbox)) {
                if (w.isvisible) {
                    w.isvisible = false;
//                    makevisible();
                    sbg.enterState(3, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));

                }

            }
        }

        if (player.health <= 0) {
//            makevisible();
            sbg.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }

    }

    public int getID() {

        return 1;

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
            player.y -= 32;
            return true;
        }
    }

}
