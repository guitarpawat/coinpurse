package coinpurse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *  A coin purse contains coins.
 *  You can insert coins, withdraw money, check the balance,
 *  and check if the purse is full.
 *  When you withdraw money, the coin purse decides which
 *  coins to remove.
 *  
 *  @author Pawat Nakpiphatkul
 */
public class Purse {
    /** Collection of objects in the purse. */
    List<Coin> coins = new ArrayList<>();
    /** Capacity is maximum number of coins the purse can hold.
     *  Capacity is set when the purse is created and cannot be changed.
     */
    private final int capacity;
    
    /** 
     *  Create a purse with a specified capacity.
     *  @param capacity is maximum number of coins you can put in purse.
     */
    public Purse( int capacity ) {
	this.capacity = capacity;
    }

    /**
     * Count and return the number of coins in the purse.
     * This is the number of coins, not their value.
     * @return the number of coins in the purse
     */
    public int count() {
        return coins.size();
    }
    
    /** 
     *  Get the total value of all items in the purse.
     *  @return the total value of items in the purse.
     */
    public double getBalance() {
        double returnValue = 0.0;
        for(Coin c : coins)
            returnValue += c.getValue();
        return returnValue;
    }

    
    /**
     * Return the capacity of the coin purse.
     * @return the capacity
     */
    //TODO write accessor method for capacity. Use Java naming convention.
    public int getCapacity() { return this.capacity; }
    
    /** 
     *  Test whether the purse is full.
     *  The purse is full if number of items in purse equals
     *  or greater than the purse capacity.
     *  @return true if purse is full.
     */
    public boolean isFull() {
        return count()>=getCapacity();
    }

    /** 
     * Insert a coin into the purse.
     * The coin is only inserted if the purse has space for it
     * and the coin has positive value.  No worthless coins!
     * @param coin is a Coin object to insert into purse
     * @throws IllegalArgumentException if the new coin is not the same currency as coins in purse.
     * @return true if coin inserted, false if can't insert
     */
    public boolean insert( Coin coin ) {
        if(count()!=0){
            if(!coin.getCurrency().equalsIgnoreCase(coins.get(0).getCurrency())){
                throw new IllegalArgumentException("Not the same currency");
            }
        }
        if(isFull()||coin.getValue()<=0)
            return false;
        coins.add(coin);
        return true;
    }
    
    /**  
     *  Withdraw the requested amount of money.
     *  Return an array of Coins withdrawn from purse,
     *  or return null if cannot withdraw the amount requested.
     *  @param amount is the amount to withdraw
     *  @return array of Coin objects for money withdrawn, 
	 *    or null if cannot withdraw requested amount.
     */
    public Coin[] withdraw( double amount ) {
        if(amount<=0)
            return null;
        Collections.sort(coins);
        Collections.reverse(coins);
        List<Coin> returnList = new ArrayList();
        for(Coin c : coins){
            double value = c.getValue();
            if(amount-value>=0){
                amount -= value;
                returnList.add(c);
                if(amount==0)
                    break;
            }
        }
        if(amount==0.0){
            for(Coin c : returnList){
                coins.remove(c);
            }
            Coin[] array = new Coin[returnList.size()];
            return returnList.toArray(array);
	}
        return null;
    }
  
    /** 
     * toString returns a string description of the purse contents.
     * It can return whatever is a useful description.
     */
    public String toString() {
    	return count()+" coin(s) with value "+getBalance();
    }
    
    /**
     * Get the purse currency.
     * @return currency of the first coin in the purse.
     */
    public String getCurrency(){
        if(getCapacity()==0){
            return null;
        }
        return coins.get(0).getCurrency();
    }

}