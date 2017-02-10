package coinpurse;
/**
 * A coin with a monetary value and currency.
 * @author Pawat Nakpiphatkul
 */
public class Coin implements Comparable<Coin>{
    public static final String DEFAULT_CURRENCY = "Baht";
    /** Value of the coin. */
    private final double value;
    /** The currency, of course. */
    private final String currency;
    
    /**
     * A coin with given value using the default currency.
     * @param value is a value of the Coin.
     */
    public Coin( double value ) {
        this.value = value;
        this.currency = DEFAULT_CURRENCY;
    }
    
    /**
     * A coin with given value and currency.
     * @param value is a value of the Coin.
     * @param currency is a currency of the Coin.
     */
    public Coin( double value, String currency ) {
        this.value = value;
        this.currency = currency;
    }

    /**
     * Get the coin value.
     * @return the value of the coin.
     */
    public double getValue() {
        return this.value;
    } 
    
    /**
     * Get the coin currency.
     * @return the currency of the coin.
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
        Coin other = (Coin) obj;
        return this.value==other.value && this.currency.equalsIgnoreCase(other.currency);
    }
    
    /**
     * Compare two coin is less, greater than or equal.
     * @param other is another Coin object.
     * @throws IllegalArgumentException if the objects are null or don't have the same currency.
     * @return positive integer if this object is greater than other, negative if less than, otherwise zero.
     */
    @Override
    public int compareTo(Coin other){
        if(other==null)
            throw new IllegalArgumentException("Cannot compare null object");
        if(!other.currency.equalsIgnoreCase(this.currency))
            throw new IllegalArgumentException("Currency Mismatched");
        return (int)((this.value-other.value)*100);
    }
    /**
     * Get the information of the Coin in String format.
     * @return information of the Coin.
     */
    @Override
    public String toString() {
        String returnValue = ""+value;
        if(returnValue.charAt(returnValue.length()-1)=='0')
            returnValue = returnValue.substring(0,returnValue.length()-2);
        return returnValue+"-"+currency;
    }
}