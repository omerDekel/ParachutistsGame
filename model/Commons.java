package model;

import javax.swing.*;
import java.awt.*;

/**
 * common details used in the program.
 */
public interface Commons {

        int BOARD_WIDTH = 1080;
        int BOARD_HEIGHT = 720;
        int SEA_HIGHT = 478;

        int SPEED_BOAT = 120;
        int SPEED_PLANE = 100;

        String BOAT_PATH_IMG = "boat.png";
        String PLANE_PATH_IMG = "plane.png";
        String PARA_PATH_IMG = "parachutist.png";
        String SEA_PATH_IMG = "sea.png";
        String BACKGROUND_PATH_IMG = "background.png";
        //Where allowed to drop parachutists
        int PARA_RIGHT_LIMIT = 920;
        int PARA_LEFT_LIMIT = 0;

        int BOAT_HEIGHT = 153;
        int BOAT_WIDTH = 244;

        int PARA_WIDTH = 77;
        int PARA_HEIGHT = 113;

        int PLANE_WIDTH = 145;
        int PLANE_HEIGHT = 113;

        int BOAT_INIT_X = BOARD_WIDTH/2;
        int BOAT_INIT_Y = BOARD_HEIGHT/2;

        int PLANE_INIT_X = BOARD_WIDTH-200;
        int PLANE_INIT_Y = 45;

        int INCREASE_WAIT = 50000;
        int CALCULATIONS_PER_SECOND = 60;
    }


