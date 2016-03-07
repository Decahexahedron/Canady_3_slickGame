/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon;

import org.newdawn.slick.Color;

import org.newdawn.slick.Game;

import org.newdawn.slick.GameContainer;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import org.newdawn.slick.Input;

import org.newdawn.slick.SlickException;

import org.newdawn.slick.state.BasicGameState;

import org.newdawn.slick.state.StateBasedGame;

import org.newdawn.slick.state.transition.FadeInTransition;

import org.newdawn.slick.state.transition.FadeOutTransition;

public class win extends BasicGameState {

    private StateBasedGame game;
    public Image winImage;
//    public Player player;

    public win(int xSize, int ySize) {

    }

    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        winImage = new Image("res/Win.png");
        this.game = game;
        DungeonChallenge.currentSpawnX = 96f;
        DungeonChallenge.currentSpawnY = 220f;
        DungeonChallenge.currentStage = "Stage 1";
// TODO AutoÃ¢â‚¬Âgenerated method stub
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {

// TODO AutoÃ¢â‚¬Âgenerated method stub
        g.setColor(Color.white);
        winImage.draw();
        g.drawString("Your score was: " + DungeonChallenge.score, 400, 320);

    }

    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {

// TODO AutoÃ¢â‚¬Âgenerated method stub
    }

    public int getID() {

// TODO AutoÃ¢â‚¬Âgenerated method stub
        return 3;

    }

    @Override

    public void keyReleased(int key, char c) {

        switch (key) {

            case Input.KEY_1:

                DungeonChallenge.player.health = 1000;
                DungeonChallenge.player.speed = .22f;
//                DungeonChallenge.counter = 0;
                DungeonChallenge.player.x = DungeonChallenge.currentSpawnX;
                DungeonChallenge.player.y = DungeonChallenge.currentSpawnY;
                DungeonChallenge.score = 0;
                DungeonChallenge.currentStage = "Stage 1";
                itemwin.isvisible = true;
                game.enterState(1, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));

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
