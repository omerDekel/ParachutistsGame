import Controller.Controller;
import View.GameView;
import Controller.GameLogic;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
	    GameLogic logic = new GameLogic();
        Controller controller = new Controller(logic);
        GameView view = new GameView(controller);
        logic.addObserver(view);
        SwingUtilities.invokeLater(view::setupView);
        controller.beginControl();
    }
}
