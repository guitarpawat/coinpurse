package coinpurse;

/**
 * A bank note with a monetary value and currency.
 * @author Pawat Nakpiphatkul
 */
public class BankNote implements Valuable{
    public static final String DEFAULT_CURRENCY = "Baht";
    /** Value of the bank note. */
    private final double value;
    /** The currency, of course. */
    private final String currency;
    /** Serial number of the bank note */
    private final long serialNumber;
    
    /**
     * A bank note with given value using the default currency.
     * @param value is a value of the BankNote.
     */
    public BankNote( double value ) {
        this.value = value;
        this.currency = DEFAULT_CURRENCY;
        this.serialNumber = System.nanoTime();
    }
    
    /**
     * A bank note with given value and currency.
     * @param value is a value of the BankNote.
     * @param currency is a currency of the BankNote.
     */
    public BankNote( double value, String currency ) {
        this.value = value;
        this.currency = currency;
        this.serialNumber = System.nanoTime();
    }
    /**
     * Get the bank note value.
     * @return the value of the bank note.
     */
    public double getValue() {
        return this.value;
    } 
    
    /**
     * Get the bank note currency.
     * @return the currency of the bank note.
     */
    public String getCurrency() {
        return this.currency;
    }
    
    /**
     * Check that two objects are same.
     * @param obj is an another object.
     * @return true if they are same, otherwise false.
     */
    public boolean equals(Object obj) {
        if(obj==null)
            return false;
        if(this==obj)
            return true;
        if(this.getClass()!=obj.getClass())
            return false;
        Valuable other = (Valuable) obj;
        return this.value==other.getValue() && this.currency.equalsIgnoreCase(other.getCurrency());
    }
    
    /**
     * Compare two bank note is less, greater than or equal.
     * @param other is another BankNote object.
     * @throws IllegalArgumentException if the objects are null or don't have the same currency.
     * @return positive integer if this object is greater than other, negative if less than, otherwise zero.
     */
    @Override
    public int compareTo(Valuable other){
        if(other==null)
            throw new IllegalArgumentException("Cannot compare null object");
        if(!other.getCurrency().equalsIgnoreCase(this.currency))
            throw new IllegalArgumentException("Currency Mismatched");
        return (int)((this.value-other.getValue())*100);
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
