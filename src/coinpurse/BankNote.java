package coinpurse;

/**
 * A bank note with a monetary value and currency.
 * @author Pawat Nakpiphatkul
 */
public class BankNote extends AbstractValuable {
    /** Serial number of the bank note. */
    private final long serialNumber;
    
    /**
     * A bank note with given value using the default currency.
     * @param value is a value of the BankNote.
     */
    public BankNote( double value ) {
        super(value);
        this.serialNumber = System.nanoTime();
    }
    
    /**
     * A bank note with given value and currency.
     * @param value is a value of the BankNote.
     * @param currency is a currency of the BankNote.
     */
    public BankNote( double value, String currency ) {
        super(value,currency);
        this.serialNumber = System.nanoTime();
    }

    /**
     * Get the information of the BankNote in String format.
     * @return information of the BankNote.
     */
    @Override
    public String toString() {
        String returnValue = ""+value;
        if(returnValue.charAt(returnValue.length()-1)=='0')
            returnValue = returnValue.substring(0,returnValue.length()-2);
        return returnValue+"-"+currency+" note ["+serialNumber+']';
    }
    
    /**
     * Get the serial number of the bank note.
     * @return serial number of this bank note
     */
    public long getSerial(){
        return this.serialNumber;
    }
}
