package model;

import com.sun.istack.internal.Nullable;

import javax.swing.*;
import java.util.Random;

import static model.Commons.PARA_LEFT_LIMIT;
import static model.Commons.PARA_RIGHT_LIMIT;

/**
 * plane sprite logic.
 */
public class PlaneModel extends SpriteModel{
    public PlaneModel(double startX, double startY,double speed,int d) {
        super(startX,  startY, speed,d,Commons.PLANE_PATH_IMG);
    }

    /***
     *
     * @return true if the Parachutist is in borders of the game.
     */
    private Boolean pInBorders() {
        return getX() < PARA_RIGHT_LIMIT && getX() > PARA_LEFT_LIMIT;

    }
    public void calculateNextStep(double time) {
        if (getX() < -1*width) {
            setX(Commons.BOARD_WIDTH);
        }else {
            setX(getX()-getSpeed()*time*this.direction);
        }
    }

    /**
     * drop Parachutist from the plane in some probability
     * @param time that used in order to drop in probability , and initialize speed.
     * @return the dropped Parachutist , else null.
     */
    @Nullable
    public ParachutistModel dropParachutist(double time) {
        // dropping
        if (pInBorders()) {
            return new ParachutistModel(getX(), getY()+30, time*6000,1);
        }
        return null;
    }
}
