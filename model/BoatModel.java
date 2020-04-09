package model;

import javax.swing.*;
import java.awt.*;

/**
 * Boat logic sprite.
 */
public class BoatModel  extends SpriteModel{

    public BoatModel(double startX, double startY,double mySpeed,int d) {
        super(startX,startY,mySpeed,d,Commons.BOAT_PATH_IMG);
    }
    public void calculateNextStep(double time) {
        if (getX() > 0 && getX() < Commons.BOARD_WIDTH -width) {
            setX(getX() + direction * getSpeed()*time);
        }else if(getX() <0 && direction>0) {
            setX(getX() + direction * getSpeed()*time);
        }else if(getX() > Commons.BOARD_WIDTH - width && direction <0) {
            setX( getX() + direction * getSpeed()*time);
        }
    }
    /**
     *
     * @return the image of the sprite as rectangle
     */
    public Rectangle getImageRect() {
        return new Rectangle((int)getX(),(int)getY(),width,height);
    }

}
