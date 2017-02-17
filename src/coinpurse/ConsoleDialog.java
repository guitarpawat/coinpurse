package coinpurse;
 
import java.util.Scanner;

/** 
 * User Interface for the CoinPurse. 
 * This class provides simple interactive dialog for inserting
 * and removing money to/from the purse, and displaying the
 * balance.
 */
public class ConsoleDialog implements Runnable {
    // default currency for this dialog
    public static final String CURRENCY = "Baht";
    // use a single java.util.Scanner object for reading all input
    private static Scanner console = new Scanner( System.in );
    // contains the reference purse
    private Purse purse;
    
    /** 
     * Initialize a new Purse dialog.
     * @param purse is the Purse to interact with.
     */
    public ConsoleDialog(Purse purse) {
        this.purse = purse;
    }
    
    /** run the user interface */
    @Override
    public void run() {
        String choice = "";
        while( true ) {
            System.out.printf("Purse contains %.2f %s\n", purse.getBalance(), CURRENCY );
            if ( purse.isFull() ) System.out.println("Purse is FULL.");
            // print a list of choices
            System.out.print( 
              "\nPlease enter d (deposit), w (withdraw), ? (inquiry), or q (quit): ");
            choice = console.nextLine().trim().toLowerCase();
 
            if ( choice.equals("d") ) depositDialog();
            else if ( choice.equals("w") ) withdrawDialog();
            else if ( choice.equals("?") ) System.out.println( purse.toString() );
            else if ( choice.equals("q") ) break; // leave the loop
            else System.out.println( "\""+choice+"\" is not a valid choice.");
        }
        // confirm that we are quitting
        System.out.println("Goodbye. The purse still has "+purse.getBalance()+" "+CURRENCY);
    }

    /** Ask the user how many coins to deposit into purse, then deposit them.
     *  Show result of success or failure.
     */
    public void depositDialog() {
        System.out.print("Enter value of coin(s)/note(s) to deposit on one line [eg: 5 5 1]: ");
        String inline = console.nextLine();
        // parse input line into numbers
        Scanner scanline = new Scanner(inline);
        while( scanline.hasNextDouble() ) {
            double value = scanline.nextDouble();
            if(value<20.0){
                Coin coin = new Coin(value);
                System.out.printf("Deposit %s... ", coin.toString() );
                boolean ok = purse.insert(coin);
                System.out.println( (ok? "ok" : "FAILED") );
            }
            else{
                BankNote note = new BankNote(value);
                System.out.printf("Deposit %s... ", note.toString() );
                boolean ok = purse.insert(note);
                System.out.println( (ok? "ok" : "FAILED") );
            }
        }
        if ( scanline.hasNext() )
            System.out.println("Invalid input: "+scanline.next() );
    }
    
    /** Ask how much money (Baht) to withdraw and then do it.
     *  After withdraw, show the values of the coins we withdrew.
     */
    public void withdrawDialog() {
        System.out.print("How much to withdraw? ");
        if ( console.hasNextDouble() ) {
            double amount = console.nextDouble( );
            Valuable [] coins = purse.withdraw(amount);
            if ( coins == null ) 
                System.out.printf("Sorry, couldn't withdraw %g %s\n", amount, CURRENCY);
            else {
                System.out.print("You withdrew:");
                for(int k=0; k<coins.length; k++) {
                	System.out.print(" " + coins[k].toString() );
                }
                System.out.println();
            }
        }
        else System.out.printf("Invalid amount." );
        // discard remainder of the input line so we don't read it again
        console.nextLine();
    }

}
