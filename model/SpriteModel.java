package model;

import javax.swing.*;
import java.awt.*;

/**
 * Abstract class of sprite . Children of this class implement the calculateNextStep method.
 *
 */
public abstract class SpriteModel implements Sprite{
    private double x;
    private double y;
    protected Image image;
    protected int direction;
    protected int width;
    protected int height;
    private double speed;




    /**
     * construtor.
     * @param startX start x position of the sprite.
     * @param startY start x position of the sprite.
     * @param mySpeed speed of sprite.
     */
    public SpriteModel(double startX, double startY,double mySpeed,int direction,String path) {
        this.direction = direction;
        this.speed = mySpeed;
        this.x = startX;
        this.y =startY;
        this.image = new ImageIcon(getClass().getResource(path)).getImage();
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);

    }
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * speed getter .
     * @return speed .
     */
    public double getSpeed() {
        return speed;
    }

    public void setX(double x) {

        this.x = x;
    }

    /**
     * Y setter.
     * @param y cordinate to set.
     */
    public void setY(double y) {

        this.y = y;
    }

    /**
     * y getter .
     * @return y cordinate position of the sprite on board.
     */
    public double getY() {

        return y;
    }

    /**
     * x getter .
     * @return x.
     */
    public double getX() {

        return x;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}
