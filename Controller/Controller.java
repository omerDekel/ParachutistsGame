package Controller;

import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;
import Controller.commands.*;
import com.sun.javaws.exceptions.ExitException;
import Controller.commands.CommandInfo;
import Controller.commands.CommandExecutor;
import Controller.commands.NewGameException;


import static model.Commons.CALCULATIONS_PER_SECOND;
import static model.Commons.INCREASE_WAIT;

/**
 * Controller layer game.
 * Create queue of commands received from view and execute them with the gameLogic .
 */
public class Controller {
	/**
	 *  boolean say if thread of timer  is keep running.
	 */
	private boolean keepRunning;
	/**
	 * map between the name of the command to its executor.
	 */
	private final HashMap<String, CommandExecutor> nameCommandMap;
	/**
	 * queue for commands
	 */
	private final LinkedBlockingQueue<CommandInfo> commandsQueue;
	/**
	 * Timer loop thread, calculating gameLogic every time as demand.
	 */
	private Thread gameTimeThread;

	private double time;

	private final GameLogic gameLogic;

	/**
	 * Constructor .
	 * @param logic .
	 */
	public Controller(GameLogic logic) {
		this.commandsQueue = new LinkedBlockingQueue<>();
		this.gameLogic = logic;
		this.nameCommandMap = new HashMap<>();
		this.keepRunning = false;
		this.nameCommandMap.put("start", new StartGameCommand(this.gameLogic));
		this.nameCommandMap.put("keyPressed", new PushKeyCommand(this.gameLogic));
		this.nameCommandMap.put("keyReleased", new ReleaseKeyCommand(this.gameLogic));
	}



	/**
	 *start controller main game loop, execute commands from queue
	 */
	public void beginControl() {
		CommandInfo curCommand;
		CommandExecutor curCommandExecuter;
		while (true) {
			try {
				//take command from the queue
				curCommand = commandsQueue.take();
			} catch(Exception e) {
				continue;
			}

			try {
				//execute the command
				curCommandExecuter = this.nameCommandMap.get(curCommand.name);
				curCommandExecuter.execute(curCommand.additionalInfo);
			} catch(NewGameException e) {
				System.out.println("Starting new Game!");
				this.startCountTime();
			} catch(ExitException e) {
				break;
			} catch(Exception ignored) {
			}
		}
		//stop time counter thread and stop controller's action .
		this.stopCountTime();
		System.exit(0);
	}

	private void initTimer () {
		this.keepRunning = true;
	}

	private void endTimer(){
		this.keepRunning =false;
	}
	/**
	 * Start count time thread, executing  gameLogic's calculations.
	 */
	private void startCountTime() {
		Runnable timerRunnable = () -> {
			initTimer();
			long millisWaitTime = 1000/CALCULATIONS_PER_SECOND;
			long currT, nextT;
			//previous time
			long prevT = System.nanoTime();
			while (this.keepRunning) {
				try {
					Thread.sleep(millisWaitTime);
				} catch (InterruptedException e) {
				}
				currT = System.nanoTime();
				this.time = (double)(currT - prevT) * 0.000000001;
				prevT = currT;
				nextT = prevT + 1000000000 / CALCULATIONS_PER_SECOND;
				this.gameLogic.updateGame(time);
				// stop the timer game if the player lose
				if(this.gameLogic.playerLose()) {
					this.stopCountTime();
				}
				long timeWait = Math.max(nextT - System.nanoTime(),0);
				timeWait += INCREASE_WAIT;
				millisWaitTime = timeWait / 1000000;
			}
		};
		gameTimeThread = new Thread(timerRunnable);
		gameTimeThread.start();
	}

	/**
	 * Wait the timer thread to stop.
	 */
	private void stopCountTime() {
		endTimer();
		if(this.gameTimeThread != null) {
            try {
                this.gameTimeThread.join();
            } catch (InterruptedException ignored) {
            }
        }
	}
	/**
	 * add command info object to the queue
	 * @param command  to add to the queue
	 */
	public void addCommand(CommandInfo command) {
		try {
			commandsQueue.put(command);
		} catch(InterruptedException exception) {
			exception.printStackTrace();
		}
	}
}