package dungeon;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

import org.newdawn.slick.Game;

import org.newdawn.slick.GameContainer;

import org.newdawn.slick.Graphics;

import org.newdawn.slick.Input;

import org.newdawn.slick.SlickException;

import org.newdawn.slick.state.BasicGameState;

import org.newdawn.slick.state.StateBasedGame;

import org.newdawn.slick.state.transition.FadeInTransition;

import org.newdawn.slick.state.transition.FadeOutTransition;

public class lose2 extends BasicGameState {

    private StateBasedGame game;
    public Image startimage;
//    public Player player;

    public lose2(int xSize, int ySize) {

    }

    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        startimage = new Image("res/LossScreen.png");

        this.game = game;

// TODO AutoÃ¢â‚¬Âgenerated method stub
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {

        startimage.draw();

// TODO AutoÃ¢â‚¬Âgenerated method stub
        g.setColor(Color.white);

        //g.drawString("You LOSE!", 450, 200);
//        g.drawString("press 1 to try again", 400, 320);
    }

    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {

// TODO AutoÃ¢â‚¬Âgenerated method stub
    }

    public int getID() {

// TODO AutoÃ¢â‚¬Âgenerated method stub
        return 6;

    }

    @Override

    public void keyReleased(int key, char c) {

        switch (key) {

            case Input.KEY_1:

//                DungeonChallenge.player.health = 10000;
//                DungeonChallenge.verticalSpeed = 0;
//                DungeonChallenge.player.speed = .22f;
                Combat.bplayer.health = 100;
                itemwin.isvisible = true;
//                DungeonChallenge.player.x = DungeonChallenge.currentSpawnX;
//                DungeonChallenge.player.y = DungeonChallenge.currentSpawnY;
                Combat.bplayer.x = 95;
                Combat.bplayer.y = 93;
                game.enterState(5, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));

                break;

            case Input.KEY_2:

// TODO: Implement later
                break;

            case Input.KEY_3:

// TODO: Implement later
                break;

            default:

                break;

        }

    }

}
