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
import org.newdawn.slick.Music;

public class Menu extends BasicGameState {

    private StateBasedGame game;

    public Image startimage;

    public Menu(int xSize, int ySize) {

    }

    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        
//        Music music1 = new Music("res/music.ogg");
        
//        music1.play();
        
        startimage = new Image("res/Lorelei.png");

        this.game = game;

// TODO AutoÃ¢â‚¬Âgenerated method stub
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {

// TODO AutoÃ¢â‚¬Âgenerated method stub
        g.setColor(Color.white);

        startimage.draw();

    }

    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {

// TODO AutoÃ¢â‚¬Âgenerated method stub
    }

    public int getID() {

// TODO AutoÃ¢â‚¬Âgenerated method stub
        return 0;

    }

    @Override

    public void keyReleased(int key, char c) {

        switch (key) {

            case Input.KEY_1:

                game.enterState(1, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));

                break;

            case Input.KEY_2:

                Combat.bplayer.x = 95;
                Combat.bplayer.y = 93;
                game.enterState(5, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
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
