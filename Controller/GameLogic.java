package Controller;


import model.*;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Random;

/**
 * game logic ,moving sprites of the game. Notify its observers (View) for its changes.
 */
public class GameLogic extends Observable {

    private ArrayList<SpriteModel> spriteCollection = new ArrayList<>();
    private final Random randomGenerator = new Random();
    private int scores = 0;
    private int lives = 3;
    private final static double dropProbability = 0.5;
    //boolean map to check which key was typed. true for pushed key.
    private boolean typedKeyMap[];
    private BoatModel boat;
    private PlaneModel plane;
    private ArrayList<ParachutistModel> parachutists = new ArrayList<>();
    private boolean playerLose;
    public GameLogic() {
        this.playerLose = false;
        this.boat = new BoatModel(Commons.BOAT_INIT_X,Commons.BOAT_INIT_Y ,Commons.SPEED_BOAT,0);
        this.plane = new PlaneModel(Commons.PLANE_INIT_X, Commons.PLANE_INIT_Y,Commons.SPEED_PLANE,1);

        spriteCollection.add(boat);
        spriteCollection.add(plane);
        this.typedKeyMap = new boolean[256];
    }

    /***
     * initial new game.
     */
    public void initNewGame() {
        Arrays.fill(this.typedKeyMap,false);
        this.setChanged();
        //Notify the observers of the logic about its change.
        this.notifyObservers(this);

    }


    /***
     * update the state of the key in typed key map.
     * @param pushedKey code of the key to set .
     * @param isPressed true if pressed, false if released.
     */
    public void setKeyState(int pushedKey, boolean isPressed){
        if(pushedKey <=255){
            this.typedKeyMap[pushedKey] = isPressed;
        }
    }

    /**
     *
     * @param key to check if was pressed.
     * @return true if key was pressed.
     */
    public boolean IsKeyPressesd(int key) {
        return key <= 255 && this.typedKeyMap[key];
    }
    /**
     * updating current game logic.
     * @param currTime frame time, in seconds
     */
    public void updateGame(double currTime){
        setBoatDirection();
        for (SpriteModel spriteModel: spriteCollection){
            spriteModel.calculateNextStep(currTime);
        }
        updatePlane(currTime);
        if(parachutists.size()>0) {
            updateParachutists(currTime);
        }

        this.setChanged();
        this.notifyObservers(this);
    }

    /***
     * updating current Parachutists logic.
     * @param t current time
     */
    private void updateParachutists(double t){
        for (int i  = 0;i<parachutists.size();i++) {
            parachutists.get(i).calculateNextStep( t);
            parachutistLanding(parachutists.get(i));
        }
    }
    private void setBoatDirection() {
        boolean leftKeyDown = this.IsKeyPressesd(KeyEvent.VK_LEFT);
        boolean rightKeyDown = this.IsKeyPressesd(KeyEvent.VK_RIGHT);
        if (leftKeyDown && !rightKeyDown) {
            boat.setDirection(-1);
        } else if (!leftKeyDown && rightKeyDown) {
            this.boat.setDirection(1);
        }else{
            this.boat.setDirection(0);
        }
    }


    /***
     * updating plane logic.
     * @param t time.
     */
   private void updatePlane(double t){
        ParachutistModel currentParachutist;
        if (this.parachutists.size()<=3&& this.randomGenerator.nextDouble() <= (dropProbability*t )) {
            currentParachutist = this.plane.dropParachutist(t);
            if (currentParachutist != null) {
                this.parachutists.add(currentParachutist);
            }

        }
    }

    /***
     *checks whether the parachutist landed to boat or fell to the sea.
     */
    private void parachutistLanding(ParachutistModel parachutist) {
        //the parachutist lands to the boat
        if (parachutist.getImageRect().intersects(boat.getImageRect())) {
            this.scores +=10;
            if(!parachutists.isEmpty()){
                parachutists.remove(parachutist);
            }
            // fell to the sea
        } else if (parachutist.getY() > Commons.SEA_HIGHT-100){
            this.lives--;
            if(!parachutists.isEmpty()){
                parachutists.remove(parachutist);
            }
        }  if (lives ==0) {
            this.playerLose = true;
        }
    }
    public void closeGame(){
    }
    //Getters
    public int getScores() {
        return scores;
    }

    public int getLives() {
        return lives;
    }

    public boolean playerLose() {
        return playerLose;
    }
    public ArrayList<SpriteModel> getSpriteCollection() {
        return spriteCollection;
    }

    public ArrayList<ParachutistModel> getParachutists() {
        return parachutists;
    }
}

