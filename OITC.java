package OITC;


import java.io.IOException;

import java.util.ArrayList;

import java.util.Iterator;

import java.util.logging.Level;

import java.util.logging.Logger;

import org.lwjgl.system.linux.Time;
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import static org.newdawn.slick.Color.red;

import org.newdawn.slick.Font;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

import org.newdawn.slick.SpriteSheet;

import org.newdawn.slick.TrueTypeFont;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.*;

import org.newdawn.slick.state.BasicGameState;

import org.newdawn.slick.state.StateBasedGame;

import org.newdawn.slick.tiled.TiledMap;

class player {


    public static float x = 164f;

    public static float y = 264f;

    public static int health = 25;

    static float hitboxX = x + 8f;

    static float hitboxY = y + 8f;

    private static int startX, startY, width = 30, height = 50;

    public static Shape rect = new Rectangle(getplayershitboxX(), getplayershitboxY(), width, height);

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

        return x + 18f;

    }


    public static float getplayershitboxY() {

        return y - 18f;

    }


    public static void setplayershitboxX() {

        hitboxX = getplayershitboxX();

    }


    public static void setplayershitboxY() {

        hitboxY = getplayershitboxY();

    }


}
class player2 {


    public static float x = 164f;

    public static float y = 264f;

    public static int health = 25;

    static float hitboxX = x + 8f;

    static float hitboxY = y + 8f;

    private static int startX, startY, width = 30, height = 50;

    public static Shape rect = new Rectangle(getplayershitboxX2(), getplayershitboxY2(), width, height);

    public static float pdelta2;

    public static Animation playeranime2;


    public static void setpdelta2(float somenum) {

        pdelta2 = somenum;

    }


    public static float getpdelta2() {

        return pdelta2;

    }


    public static float getplayersX2() {

        return x;

    }


    public static float getplayersY2() {

        return y;

    }


    public static float getplayershitboxX2() {

        return x + 18f;

    }


    public static float getplayershitboxY2() {

        return y - 18f;

    }


    public static void setplayershitboxX2() {

        hitboxX = getplayershitboxX2();

    }


    public static void setplayershitboxY2() {

        hitboxY = getplayershitboxY2();

    }
}
 

public class OITC extends BasicGame
{
     private TiledMap grassMap;
     private Animation sprite, up, down, left, right, dark,dleft,dright,kill;
     private Image spell,spelld,dead,heart, Title;
     private Music backgroundMusic;
     private float x = 250f, y = 1395f;
     private float defx = x, defy = y;
     private float dx = 1240f, dy = 1325f;
     private float defdx = dx, defdy = dy;
     private float bx = 250f, by = 950f;
     private float bxd = 500f,byd = 950f;
     private float mdy = dy, my = y;
     private float pbx,pbxd;
     private float cx, cy;
     float fade = 1;
     boolean jumping = false;
     boolean jumpind = false;
     boolean ground = false;
     boolean groundd = false;
     boolean bang = false,reload = true;
     boolean bangd = false,reloadd = true;
     boolean playeralive=true,player2alive=true;
     public float timer,timerd;
     boolean neg = false;
     private static AppGameContainer app;
     float verticalSpeed = 0;
     float derticalSpeed = 0;
     private static Camera camera;
     float bulletspeed = 20;
     int direction = 1;
     int pd = direction;
     int directiond = -1;
     int pdd = directiond;
     public Shape bb = new Rectangle(bx, by, 20, 10);
     public Shape bbd = new Rectangle(bxd, byd, 20, 10);
    
    /** The collision map indicating which tiles block movement - generated based on tile properties */
    private boolean[][] blocked;
    private boolean [][] land;
    private boolean [][] wall;
    private static final int SIZE = 16;

 
     public OITC()
     {
         super("One in the Chamber");
     }
 
     public static void main(String [] arguments)
     {
        
         try
         {
             app = new AppGameContainer(new OITC());
             app.setDisplayMode(1200, 800, false);
             app.setTargetFrameRate(60);
             app.start();
         }
         catch (SlickException e)
         {
             e.printStackTrace();
         }
     }
 
     @Override
     public void init(GameContainer container) throws SlickException
     {
        container.setShowFPS(false);
        container.setVSync(true);
        backgroundMusic = new Music("res\\background.wav");
       
        Image [] movementRight = {new Image("res\\Player\\Runr1.png"), new Image("res\\Player\\Runr2.png")};
        Image [] movementLeft = {new Image("res\\Player\\Runl1.png"), new Image("res\\Player\\Runl2.png")};
        Image [] movementdRight = {new Image("res\\Dark\\Runrd1.png"), new Image("res\\Dark\\Runrd2.png")};
        Image [] movementdLeft = {new Image("res\\Dark\\Runld1.png"), new Image("res\\Dark\\Runld2.png")};

        int [] duration = {100, 100};
        
        grassMap = new TiledMap("res\\Map\\Dustbowl2.tmx");
        Title = new Image("res\\Title.png");
        spell = new Image("res\\bang.png");
        spelld = new Image("res\\bang.png");
        dead = new Image("res\\dead.png");
        heart = new Image("res\\heart.png");
          
         left = new Animation(movementLeft, duration, false);
         left.addFrame(new Image("res\\Player\\Runl3.png"), 100);
         left.addFrame(new Image("res\\Player\\Runl2.png"), 100);
         left.addFrame(new Image("res\\Player\\Runl1.png"), 100);
         left.addFrame(new Image("res\\Player\\Runl4.png"), 100);
         left.addFrame(new Image("res\\Player\\Runl5.png"), 100);
         left.addFrame(new Image("res\\Player\\Runl4.png"), 100);
         
         right = new Animation(movementRight, duration, false);
         right.addFrame(new Image("res\\Player\\Runr3.png"), 100);
         right.addFrame(new Image("res\\Player\\Runr2.png"), 100);
         right.addFrame(new Image("res\\Player\\Runr1.png"), 100);
         right.addFrame(new Image("res\\Player\\Runr4.png"), 100);
         right.addFrame(new Image("res\\Player\\Runr5.png"), 100);
         right.addFrame(new Image("res\\Player\\Runr4.png"), 100);
         
         dleft = new Animation(movementdLeft, duration, false);
         dleft.addFrame(new Image("res\\Dark\\Runld3.png"), 100);
         dleft.addFrame(new Image("res\\Dark\\Runld2.png"), 100);
         dleft.addFrame(new Image("res\\Dark\\Runld1.png"), 100);
         dleft.addFrame(new Image("res\\Dark\\Runld4.png"), 100);
         dleft.addFrame(new Image("res\\Dark\\Runld5.png"), 100);
         dleft.addFrame(new Image("res\\Dark\\Runld4.png"), 100);
         
         dright = new Animation(movementdRight, duration, false);
         dright.addFrame(new Image("res\\Dark\\Runrd3.png"), 100);
         dright.addFrame(new Image("res\\Dark\\Runrd2.png"), 100);
         dright.addFrame(new Image("res\\Dark\\Runrd1.png"), 100);
         dright.addFrame(new Image("res\\Dark\\Runrd4.png"), 100);
         dright.addFrame(new Image("res\\Dark\\Runrd5.png"), 100);
         dright.addFrame(new Image("res\\Dark\\Runrd4.png"), 100);
         
        camera = new Camera(app, grassMap);
         
         sprite = right;
         dark = dleft;
         
         // build a collision map based on tile properties in the TileD map
         blocked = new boolean[grassMap.getWidth()][grassMap.getHeight()];
 
        for (int xAxis=0;xAxis<grassMap.getWidth(); xAxis++)
        {
             for (int yAxis=0;yAxis<grassMap.getHeight(); yAxis++)
             {
                 int tileID = grassMap.getTileId(xAxis, yAxis, 2);
                 String value = grassMap.getTileProperty(tileID, "blocked", "false");
                 if ("true".equals(value))
                 {
                     blocked[xAxis][yAxis] = true;
                 }
             }
         }
         land = new boolean[grassMap.getWidth()][grassMap.getHeight()];
                 for (int xAxis=0;xAxis<grassMap.getWidth(); xAxis++)
        {
             for (int yAxis=0;yAxis<grassMap.getHeight(); yAxis++)
             {
                 int tileID = grassMap.getTileId(xAxis, yAxis, 2);
                 String value = grassMap.getTileProperty(tileID, "land", "false");
                 if ("true".equals(value))
                 {
                     land[xAxis][yAxis] = true;
                 }
             }
         }
         wall = new boolean[grassMap.getWidth()][grassMap.getHeight()];
                 for (int xAxis=0;xAxis<grassMap.getWidth(); xAxis++)
        {
             for (int yAxis=0;yAxis<grassMap.getHeight(); yAxis++)
             {
                 int tileID = grassMap.getTileId(xAxis, yAxis, 2);
                 String value = grassMap.getTileProperty(tileID, "land", "false");
                 if ("true".equals(value))
                 {
                     wall[xAxis][yAxis] = true;
                 }
             }
         }
     }
 
     @Override
     public void update(GameContainer container, int delta) throws SlickException
     {
         player.x = x;
         player.y = y;
         player2.x = dx;
         player2.y = dy;
      bb.setLocation(bx+15,by+15);
      bbd.setLocation(bxd+15,byd+15);
      player.rect.setLocation(player.getplayershitboxX(), player.getplayershitboxY());
      player2.rect.setLocation(player2.getplayershitboxX2(), player2.getplayershitboxY2());
      
      if (timer >= 0){
      timer -= delta;
      }
      if (timerd >= 0){
      timerd -= delta;
      }
      
            if (player.rect.intersects(bbd)&& bangd) {

                player.health -= 5;
                bangd = false;

            }
            if (player2.rect.intersects(bb)&& bang) {
                player2.health -= 5;
                bang = false;
            }
            if (bb.intersects(bbd)){
                bang = false;
                bangd = false;
            }
        
        float fdelta = delta * 0.1f;         
        cx = ((x+dx)/2);
        cy = ((y+dy)/2);
        my = (y - 32);
        mdy = (dy - 32);
        
        Input input = container.getInput();
        // jumping
        
        if (isBlocked(x, y + SIZE + delta * 0.1f)){
             ground = true;
         }
        else if (isLand(x, y + SIZE + delta * 0.1f)){
            ground = true;
        }
        else {
            ground = false;
        }
        
        if (input.isKeyDown(Input.KEY_ESCAPE)){
            System.exit(0);
        }
        
         if ((input.isKeyDown(Input.KEY_W)) && !jumping && !(verticalSpeed > 0)&&playeralive){
             verticalSpeed = -.5f * delta;//negative value indicates an upward movement 
             jumping = true;
         }
        if (jumping) { 
             verticalSpeed += .01f * delta;//change this value to alter gravity strength 
             
        if (ground){
                 // The lower the delta the slowest the sprite will animate.
                 y -= delta * 0.1f;
            verticalSpeed = 0;
            jumping = false;
        }
        
        }
        
        
        if ((ground) && !jumping) {
            
            verticalSpeed = 0;
        }
        
        else if (!(ground) && !jumping) {

            verticalSpeed = 0.3f * delta;
        }
        
        y += verticalSpeed;
        
        //gun stuff
        
        if (!bang){
            bx = x+4;
            by = y-16;
            pbx = x;
            pd = direction;
        }
        
        else if(bang){    
           bx = bx + bulletspeed * pd;
        }
        if (isBlocked(bx + SIZE + delta * 0.1f, by))
        {
            bang = false;
        }
        
                if(playeralive){
        
        
        if (input.isKeyDown(Input.KEY_SPACE)&& !isBlocked(bx + SIZE + delta * 0.1f, y)&& timer < 0){
            
            bang = true;
            timer = 1000;
            bx = x+4;
            by = y-16;
            
        }
        
         if (input.isKeyDown(Input.KEY_A))
         {
                 direction = -1;

             sprite = left;
             if (!isWall(x - delta * 0.1f, y)&&player.x > 150)
             {
                 sprite.update(delta);
                 x -= delta * 0.4f;
             }
             neg = true;
         }
         else if (input.isKeyDown(Input.KEY_D)&&player.x < 1400)
         {
                 direction = 1;
             sprite = right;
             if (!isWall(x + SIZE + delta * 0.1f, y))
             {
                 sprite.update(delta);
                 x += delta * 0.4f;
             }
             neg = true;
         }
         else if (input.isKeyDown(Input.KEY_S))
         {
             if (jumping){
                 y += SIZE * 0.15 + delta * 0.1f;
                 jumping = false;
             }
             if (isLand(x, y + SIZE + delta * 0.1f)){
                 y += SIZE * 0.15 + delta * 0.1f;
                 jumping = false;
             }
             
             
         }
         
        }
         //Player 2 movement
         
         //guns stuff
        if (!bangd){
            bxd = dx+4;
            byd = dy-16;
            pbxd = dx;
            pdd = directiond;
        }
        
        else if(bangd){    
           bxd = bxd + bulletspeed * pdd;
        }
        if (isBlocked(bxd + SIZE + delta * 0.1f, y))
        {
            bangd = false;
        }
         //jumping
        if (isBlocked(dx, dy + SIZE + delta * 0.1f)){
             groundd = true;
         }
        else if (isLand(dx, dy + SIZE + delta * 0.1f)){
             groundd = true;
         }
        else {
            groundd = false;
        }
        
        
         if ((input.isKeyDown(Input.KEY_UP)) && player2alive && !jumpind && !(derticalSpeed > 0)||(input.isKeyDown(Input.KEY_NUMPAD8)) && player2alive && !jumpind && !(derticalSpeed > 0)){
             derticalSpeed = -.5f * delta;//negative value indicates an upward movement 
             jumpind = true;
             
             
         }
        if (jumpind) { 
             derticalSpeed += .01f * delta;//change this value to alter gravity strength 
             
             if (groundd){
        
                dark.update(delta);
                 dy -= delta * 0.1f;
            

            derticalSpeed = 0;
            jumpind = false;
        }
        
        }
        
        
        if ((groundd) && !jumpind) {
            
            derticalSpeed = 0;
        }
        
        else if (!(groundd) && !jumpind) {

            derticalSpeed = 0.3f * delta;
        }
        
        dy += derticalSpeed;
        
        //jumping
         if (player2alive){
         
         if (input.isKeyDown(Input.KEY_LEFT)||input.isKeyDown(Input.KEY_NUMPAD4))
         {
             if(player2.x > 150){
             directiond = -1;
             dark = dleft;
             if (!isWall(dx - delta * 0.1f, y))
             {
                 dark.update(delta);
                 dx -= delta * 0.4f;
             }
             
             neg = true;
         }}
         else if (input.isKeyDown(Input.KEY_RIGHT)||input.isKeyDown(Input.KEY_NUMPAD6))
         {
             if(player2.x < 1400){
             directiond = 1;
             dark = dright;
             if (!isWall(dx + SIZE + delta * 0.1f, y))
             {
                 dark.update(delta);
                 dx += delta * 0.4f;
             }
             
             neg = true;
         }}
         else if (input.isKeyDown(Input.KEY_DOWN)||input.isKeyDown(Input.KEY_NUMPAD5))
         {
                 if (isLand(dx, dy + SIZE + delta * 0.1f)){
                 dy += SIZE * 0.15 + delta * 0.1f;
                 jumpind = false;
             }
                 else{ 
                     jumpind = false;
                             }
             
         }
        if (input.isKeyDown(Input.KEY_RSHIFT)&& !isBlocked(bxd + SIZE + delta * 0.1f, y)&&timerd < 0||input.isKeyDown(Input.KEY_NUMPAD0)&& !isBlocked(bxd + SIZE + delta * 0.1f, y)&&timerd < 0){
             bangd = true;
             timerd = 1000;
             bxd = dx+4;
             byd = dy-16;
         }
         }
         if(player.health <= 0){
             playeralive = false;
     }
         if(player2.health <= 0){
             player2alive = false;
     }
         if(input.isKeyDown(Input.KEY_Y)){
             player.health = 25;
             player2.health = 25;
             playeralive = true;
             player2alive = true;
             x = defx; y = defy;
             dx = defdx; dy = defdy;
             neg = false;
             fade = 1f;
             
         }
         if(neg){
             fade -= delta*.002f;
         }
     }
 
     public void render(GameContainer container, Graphics g) throws SlickException
     {
        camera.centerOn((int) cx, (int) cy);
        camera.drawMap();
        camera.translateGraphics();
        
         if (timer >= 0){
             if (bang){
                spell.draw((int)bx, (int)by);
             }
         g.drawString("Reloading", x - 10, y - 50);
         }
         if (timerd >= 0){
             if (bangd){
                spelld.draw((int)bxd, (int)byd);
             }
         g.drawString("Reloading", dx - 10, dy - 50);
         }
        if (playeralive){
         sprite.draw((int)x, (int)my);
        }
        if(player2alive){
         dark.draw((int)dx, (int)mdy);
        }
        
        if(!backgroundMusic.playing()){
            backgroundMusic.loop();
        }
        if (!playeralive){
         g.drawString("  Player 2 Wins!\npress Y to restart \n press Esc to quit", camera.cameraX+500, camera.cameraY+670);
         dead.draw(x, y-32);
         g.drawString("          is Kill", camera.cameraX + 50, camera.cameraY + 50);
        }
        if (!player2alive){
         g.drawString("  Player 1 Wins!\npress Y to restart \n press Esc to quit", camera.cameraX+500, camera.cameraY+670);
         dead.draw(dx, dy-32);
         g.drawString("is Kill", camera.cameraX + 1000, camera.cameraY + 50);
         
        }
        
        
        if (player.health >= 25){
           heart.draw(camera.cameraX + 250, camera.cameraY + 42,40,40);
        }
        if (player.health >= 20){
           heart.draw(camera.cameraX + 200, camera.cameraY + 42,40,40);
        }
        if (player.health >= 15){
           heart.draw(camera.cameraX + 150, camera.cameraY + 42,40,40);
        }
        if (player.health >= 10){
           heart.draw(camera.cameraX + 100, camera.cameraY + 42,40,40);
        }
        if (player.health >= 5){
           heart.draw(camera.cameraX + 50, camera.cameraY + 42,40,40);
        }
        
        if (player2.health >= 25){
           heart.draw(camera.cameraX + 910, camera.cameraY + 42,40,40);
        }
        if (player2.health >= 20){
           heart.draw(camera.cameraX + 960, camera.cameraY + 42,40,40);
        }
        if (player2.health >= 15){
           heart.draw(camera.cameraX + 1010, camera.cameraY + 42,40,40);
        }
        if (player2.health >= 10){
           heart.draw(camera.cameraX + 1060, camera.cameraY + 42,40,40);
        }
        if (player2.health >= 5){
           heart.draw(camera.cameraX + 1110, camera.cameraY + 42,40,40);
        }
              Color  myFilter = new Color(1f, 1f, 1f, 0.5f);   //50%
    Color myFilter2 = new Color(1f, 1f, 1f, 0.25f); //25%
           myFilter = new Color(1f, 1f, 1f, fade);
        Title.draw(camera.cameraX+200,camera.cameraY+60,myFilter);
     }
     private boolean isBlocked(float x, float y)
     {
         int xBlock = (int)x / SIZE;
         int yBlock = (int)y / SIZE;
         return blocked[xBlock][yBlock];
     }
     private boolean isLand(float x, float y)
     {
         int xBlock = (int)x / SIZE;
         int yBlock = (int)y / SIZE;
         return land[xBlock][yBlock];
     }
     private boolean isWall(float x, float y)
     {
         int xBlock = (int)x / SIZE;
         int yBlock = (int)y / SIZE;
         return land[xBlock][yBlock];
     }
 }