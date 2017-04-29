package coinpurse;
 
import coinpurse.gui.PurseBalanceObserver;
import coinpurse.gui.PurseStatusObserver;

/**
 * A main class to create objects and connect objects together.
 * The user interface needs a reference to coin purse.
 * @author Pawat Nakpiphatkul
 */
public class Main {

    private static final int CAPACITY = 10;
    /**
     * Configure and start the application.
     * @param args not used
     */
    public static void main( String[] args ) {
        // 1. create a Purse
        Purse purse = new Purse(CAPACITY);
        purse.addObserver(new PurseBalanceObserver());
        purse.addObserver(new PurseStatusObserver());
        // 2. create a ConsoleDialog with a reference to the Purse object
        Runnable task = new ConsoleDialog(purse,"Thailand");
        // 3. run the ConsoleDialog
        task.run();
    }
}
