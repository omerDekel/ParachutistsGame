package View;

import model.SpriteModel;

import java.util.Observer;

/**
 * The GameView.SpriteView interface.
 *
 * @author Omer Dekel
 */
public interface SpriteView extends Observer{

    /**
     * The drawOn method. Draw the sprites to the screen.
     */
    void drawOn();

}