package coinpurse;
/**
 * A coin with a monetary value and currency.
 * @author Pawat Nakpiphatkul
 */
public class Coin extends AbstractValuable {
    
    /**
     * A coin with given value using the default currency.
     * @param value is a value of the Coin.
     */
    public Coin( double value ) {
        super(value);
    }
    
    /**
     * A coin with given value and currency.
     * @param value is a value of the Coin.
     * @param currency is a currency of the Coin.
     */
    public Coin( double value, String currency ) {
        super(value,currency);
    }
    
        /**
     * Get the information of the Coin in String format.
     * @return information of the Coin.
     */
    @Override
    public String toString() {
        String currency = this.currency;
        double value = this.value;
        if(this.currency.equalsIgnoreCase("Baht")){
            if(value < 1) {
                currency = "Satang";
                value *=100;
            }
        }
        else if(this.currency.equalsIgnoreCase("Ringgit")){
            if(value < 1) {
                currency = "Sen";
                value *=100;
            }
        }
        return String.format("%.2f-%s coin",value,currency);
    }

}