package View;

import Controller.Controller;
import Controller.GameLogic;
import Controller.commands.CommandInfo;
import model.Commons;
import model.ParachutistModel;
import model.SpriteModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Observable;

import static model.Commons.BOARD_HEIGHT;
import static model.Commons.BOARD_WIDTH;

/**
 * View layer of the game .
 * Responsible for drawing the sprite and send commands to te controller by user's actions with gui.
 */
public class GameView implements SpriteView {
    //members
    private final Controller controller;
    private JFrame frame;
    private Image seaImg;
    private Image background;
    private GameLogic logic;
    private JPanel panel;

    /***
     * constructor.
     * @param controller of the game.
     */
    public GameView(Controller controller) {
        this.controller = controller;
        this.frame = new JFrame("Save The Parachutists!");
        this.frame.setSize(BOARD_WIDTH, BOARD_HEIGHT);

        this.background = new ImageIcon(getClass().getResource(Commons.BACKGROUND_PATH_IMG)).getImage();
        this.seaImg = new ImageIcon(getClass().getResource(Commons.SEA_PATH_IMG)).getImage();
        panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawPanel( g);
            }

        };
        frame.add(panel);
    }
    /***
     *set frame display , start the game and adding Keylisteners to the game.
     */
    public void setupView() {
            frame.setResizable(false);
            frame.setVisible(true);
            frame.addKeyListener(new KeyListener() {
                public void keyReleased(KeyEvent event) {
                    controller.addCommand(new CommandInfo("keyReleased") {{
                        additionalInfo.put("keyNumber", Integer.toString(event.getKeyCode()));
                    }});
                }
                public void keyTyped(KeyEvent event) {}
                public void keyPressed(KeyEvent event) {
                    controller.addCommand(new CommandInfo("keyPressed") {{
                        additionalInfo.put("keyNumber", Integer.toString(event.getKeyCode()));
                    }});
                }
            });
        controller.addCommand(new CommandInfo("start"));
    }
    /***
     * draw on screen scores and lives of player.
     * @param g Graphics to draw on.
     */
    private void drawPlayerInfo(Graphics g){
        g.drawString("Scores: "+ logic.getScores(),10,10);
        g.drawString("Lives: "+ logic.getLives(), BOARD_WIDTH -80,10);
    }

    /***
     * Draw the images on the panel of game
     * @param g Graphics to draw on.
     */
    private void drawPanel(Graphics g) {
        g.drawImage(this.background,0,0,null);
        g.drawImage(this.seaImg,0,Commons.SEA_HIGHT,null);
        ArrayList<SpriteModel> spriteModels = logic.getSpriteCollection();
        drawPlayerInfo(g);
        for (SpriteModel spriteModel: spriteModels){
            drawImageModel(g,spriteModel);
        }

        ArrayList<ParachutistModel>parachutists = logic.getParachutists();
        if(parachutists!= null){
            drawPara(g,parachutists);
        }
        //in case the game was over
        if(logic.playerLose()){
            g.setFont(new Font(Font.SERIF,Font.BOLD|Font.ITALIC,100));
            g.drawString("Game Over :( ", BOARD_WIDTH/2 -300,BOARD_HEIGHT/2-100);
        }
    }

    /***
     * draw the parachutists of the game on screen.
     * @param g graphics to draw on.
     * @param parachutists to draw.
     */
    private void drawPara(Graphics g, ArrayList<ParachutistModel> parachutists) {
        for (ParachutistModel p: parachutists) {
            drawImageModel(g,p);
        }
    }

    private void drawImageModel(Graphics g, SpriteModel model){
        g.drawImage(model.getImage(),(int)model.getX(),(int)model.getY(),null);

    }
    @Override
    public void drawOn() {
        this.panel.repaint();
    }

    @Override
    public void update(Observable o, Object arg) {
        this.logic = (GameLogic)(arg);
        SwingUtilities.invokeLater(this::drawOn);

    }
}

