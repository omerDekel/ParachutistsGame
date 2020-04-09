package model;

import javax.swing.*;
import java.awt.*;

/**
 * Parachutist sprite Model
 */
public class ParachutistModel extends SpriteModel {

    /**
     * constructor.
     * @param startX position of the Parachutist
     * @param startY position of Parachutist.
     * @param mySpeed Parachutist's speed.
     */
    ParachutistModel(double startX, double startY, double mySpeed, int d) {
        super(startX,startY, mySpeed,d,Commons.PARA_PATH_IMG);
    }
    /**
     *
     * @return the image of the sprite as rectangle
     */
    public Rectangle getImageRect() {
        return new Rectangle((int)getX(),(int)getY(),width,height);
    }
    public void calculateNextStep(double time) {
        setY(getY()+getSpeed()*time*direction);
    }
}
