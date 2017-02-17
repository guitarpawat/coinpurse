package coinpurse;

/**
 * Interface for classes that are has the value.
 * @author Pawat Nakpiphatkul
 */
public interface Valuable extends Comparable<Valuable> {
    /**
     * Get the monetary value of this object, in its own currency.
     * @return value of this object
     */
    public double getValue();
    /**
     * Get the currency of this object.
     * @return currency of this object
     */
    public String getCurrency();
}
