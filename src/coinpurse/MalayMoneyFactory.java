package coinpurse;

/**
 * Creates Malaysia "Ringgit" Valuable objects.
 * @author Pawat Nakpiphatkul
 */
public class MalayMoneyFactory extends MoneyFactory {
    /** The return object of this class. */
    private Valuable valuable;
    
    /**
     * This constructor does not do nothing.
     */
    MalayMoneyFactory(){}
    
    /**
     * Creates Malaysia "Ringgit" Valuable objects.
     * @param value is a value of the object.
     * @return a Valuable object.
     * @throws IllegalArgumentException if value is invalid.
     */
    @Override
    public Valuable createMoney(double value) {
        int temp = (int)(value*100);
        switch(temp) {
            case 5: case 10: case 20: case 50: case 100: case 200: case 500: case 1000: case 2000: case 5000: case 10000:
                newRinggit(value);
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
    private void newRinggit(double value) {
        if(value < 1) valuable = new Coin(value,"Ringgit");
        else valuable = new BankNote(value,"Ringgit");
    }
}
