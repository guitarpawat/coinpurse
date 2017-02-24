package coinpurse;

/**
 * Creates Thai "Baht" Valuable objects.
 * @author Pawat Nakpiphatkul
 */
public class ThaiMoneyFactory extends MoneyFactory {
    
    /** The return object of this class. */
    private Valuable valuable;
    
    /**
     * This constructor does not do nothing.
     */
    ThaiMoneyFactory(){}
    
    /**
     * Creates Thai "Baht" Valuable objects.
     * @param value is a value of the object.
     * @return a Valuable object.
     * @throws IllegalArgumentException if value is invalid.
     */
    @Override
    public Valuable createMoney(double value) {
        int temp = (int)(value*100);
        switch(temp) {
            case 25: case 50: case 100: case 200: case 500: case 1000: case 2000: case 5000: case 10000: case 50000: case 100000:
                newBaht(value);
                break;
            default :
                throw new IllegalArgumentException("Invalid Value");
        }
        return valuable;
    }
    
    /**
     * Creates Coin or BankNote.
     * @param value is the value of Coin or BankNote.
     */
    private void newBaht(double value) {
        if(value < 20) valuable = new Coin(value,"Baht");
        else valuable = new BankNote(value,"Baht");
    }
}
