/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slickexample;

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

    public win(int xSize, int ySize) {

    }

    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
 winImage = new Image("res/Win.png");
        this.game = game;

// TODO AutoÃ¢â‚¬Âgenerated method stub
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {

// TODO AutoÃ¢â‚¬Âgenerated method stub
        g.setColor(Color.white);
       winImage.draw();
        g.drawString("Your score was: " + Unwavering.score, 400, 320);

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

                Player.health = 1000;
                Player.speed = .22f;
                Unwavering.counter = 0;
                Player.x = 96f;
                Player.y = 220f;
                Unwavering.score = 0;
                Unwavering.currentSpawnX = 96;
                Unwavering.currentSpawnY = 220;
                Unwavering.currentStage = "Stage 1";
                
                //item.isvisible = true;
                //item1.isvisible = true;
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
