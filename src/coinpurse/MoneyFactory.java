package coinpurse;

/**
 * Other classes create Valuable objects by using this factory class.
 * @author Pawat Nakpiphatkul
 */
public abstract class MoneyFactory {
    
    /** This is a subclass of MoneyFactory. **/
    private static MoneyFactory factory;
    
    
    /** This constructor is protected because it's prevent to create new object of this singleton class outer subclass. **/
    protected MoneyFactory(){}
    
    /**
     * Set the default MoneyFactory subclass.
     * @param def is subclass name of MoneyFactory.
     */
    public static void setMoneyFactory(MoneyFactory def) {
        if(def == null) def = new ThaiMoneyFactory();
        factory = def;
    }
    
    /**
     * Get the shared same instance of MoneyFactory.
     * @return instance of MoneyFactory.
     */
    public static MoneyFactory getInstance(){
        if(factory == null) factory = new ThaiMoneyFactory();
        return factory;
    }
     /**
      * setMoneyFactory for known subclasses of MoneyFactory and getInstance at once.
      * @param country is the country to use the currency
      * @return instance of MoneyFactory.
      */
    public static MoneyFactory initialize(String country) {
        if(country.equalsIgnoreCase("Thailand")) setMoneyFactory(new ThaiMoneyFactory());
        else if(country.equalsIgnoreCase("Malaysia")) setMoneyFactory(new MalayMoneyFactory());
        else throw new IllegalArgumentException("Invalid Country");
        return getInstance();
    }
    
   /**
    * Method for subclasses to create new Valuable objects.
    * @param value is the value of Valuable object.
    * @return Valuable object that created.
    * @throws IllegalArgumentException if value is invalid.
    */
    public abstract Valuable createMoney(double value);
    
    /**
     * Create the Valuable object of input value.
     * @param value is a String of value to create money.
     * @return Valuable object that created.
     * @throws IllegalArgumentException if value is not a number or null.
     */
    public Valuable createMoney(String value) {
        Valuable valuable;
        try{
            valuable = createMoney(Double.parseDouble(value));
        }
        catch (NumberFormatException | NullPointerException e) {
            throw new IllegalArgumentException("This String is not a number");
        }
        return valuable;
    }
}
