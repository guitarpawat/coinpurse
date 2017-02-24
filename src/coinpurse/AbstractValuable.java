package coinpurse;

/**
 *
 * @author Pawat Nakpiphatkul
 */
public abstract class AbstractValuable implements Valuable {
    
    public static final String DEFAULT_CURRENCY = "Baht";
    /** Value of the valuable. */
    protected final double value;
    /** The currency, of course. */
    protected final String currency;
    
    /**
     * Constructor to initialize value and currency.
     * @param value is the value of valuable object.
     * @param currency is the currency of valuable object.
     */
    public AbstractValuable(double value,String currency) {
        this.value = value;
        this.currency = currency;
    }
    
    /**
     *  Constructor to initialize value and currency to DEFAULT_CURRENCY.
     * @param value is the value of valuable object..
     */
    public AbstractValuable(double value) {
        this (value,DEFAULT_CURRENCY);
    }

    /**
     * Get the valuable value.
     * @return the value of the valuable.
     */
    public double getValue() {
        return this.value;
    }

    /**
     * Get the valuable currency.
     * @return the currency of the valuable.
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
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Valuable other = (Valuable) obj;
        return this.value == other.getValue() && this.currency.equalsIgnoreCase(other.getCurrency());
    }

    /**
     * Compare two valuable is less, greater than or equal.
     * @param other is another Valuable object.
     * @throws IllegalArgumentException if the objects are null or don't have the same currency.
     * @return positive integer if this object is greater than other, negative if less than, otherwise zero.
     */
    @Override
    public int compareTo(Valuable other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot compare null object");
        }
        if (!other.getCurrency().equalsIgnoreCase(this.currency)) {
            throw new IllegalArgumentException("Currency Mismatched");
        }
        return (int) ((this.value - other.getValue()) * 100);
    }
    
}
