package model;

/**
 * The Sprite interface.
 */
public interface Sprite {
    /***
     *notify the sprite that time has passed
     * @param time current time in game.
     */
    //     * @param direction 1 if  left;-1 right .
    void calculateNextStep( double time);
}
