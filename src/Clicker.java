import java.awt.*;
import java.awt.event.*;

/**
 * Simple auto-clicker.
 *
 * @author Jan Fuhrmann
 */
public class Clicker {
    private static final int CLICK_COUNTER = 100; // number of desired clicks
    private static final int COUNTDOWN_TIMER = 5; // countdown timer in seconds

    public static void main(String[] args) {
        callEvent("ClickBot", 50);
        //callEvent("KeyBot", 20);
    }

    /**
     * This function calls either a mouse click or a key press event
     *
     * @param type     The type of the event which is desired
     * @param duration The duration between two events (50: 19/sek, 25: 38/sek, 15: 63/sek, 0: 89/sek)
     */
    public static void callEvent(String type, int duration) {
        countdown(type);
        for (int i = 0; i < CLICK_COUNTER; i++) {
            try {
                Robot robot = new Robot();
                Thread.sleep(duration); // wait some time after each event
                if (type.equals("ClickBot")) {
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                } else if (type.equals("KeyBot")) {
                    robot.keyPress(KeyEvent.VK_1); // the key id
                    robot.keyPress(KeyEvent.VK_ENTER);
                }
            } catch (InterruptedException | AWTException e) {
                e.printStackTrace();
            }
            // show some output, in total 20 events
            if (i % (CLICK_COUNTER / 20) == 0) {
                System.out.println("Events: " + i + "/" + CLICK_COUNTER + " - time passed: " + (i * duration) / 1000 + "s");
            }
        }
    }

    /**
     * Starts a countdown and outputs it in the console
     *
     * @param name the name of the bot
     */
    public static void countdown(String name) {
        System.out.println(name + " is starting in...");
        // Starting a x second countdown
        for (int j = COUNTDOWN_TIMER; j > 0; j--) {
            try {
                System.out.println(j);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}