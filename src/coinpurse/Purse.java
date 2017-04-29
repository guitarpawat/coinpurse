package coinpurse;

import coinpurse.strategy.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * A coin purse contains money. You can insert money, withdraw money, check the
 balance, and check if the purse is full. When you withdraw money, the coin
 purse decides which money to remove.
 *
 * @author Pawat Nakpiphatkul
 */
public class Purse extends Observable{

    /**
     * Collection of objects in the purse.
     */
    List<Valuable> money = new ArrayList<>();
    /**
     * Capacity is maximum number of money the purse can hold. Capacity is set
     * when the purse is created and cannot be changed.
     */
    private final int capacity;
    
    /**
     * Strategy to withdraw Valuable from purse.
     */
    private WithdrawStrategy strategy;

    /**
     * Create a purse with a specified capacity.
     *
     * @param capacity is maximum number of money you can put in purse.
     */
    public Purse(int capacity) {
        this.capacity = capacity;
        strategy = new RecursiveWithdraw();
    }
    
    /**
     * Set the strategy to withdraw from purse.
     * @param strategy is the strategy of withdraw.
     */
    public void setStrategy(WithdrawStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Count and return the number of money in the purse. This is the number of
 money, not their value.
     *
     * @return the number of money in the purse
     */
    public int count() {
        return money.size();
    }

    /**
     * Get the total value of all items in the purse.
     *
     * @return the total value of items in the purse.
     */
    public double getBalance() {
        double returnValue = 0.0;
        for (Valuable c : money) {
            returnValue += c.getValue();
        }
        return returnValue;
    }

    /**
     * Return the capacity of the coin purse.
     *
     * @return the capacity
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * Test whether the purse is full. The purse is full if number of items in
     * purse equals or greater than the purse capacity.
     *
     * @return true if purse is full.
     */
    public boolean isFull() {
        return count() >= getCapacity();
    }

    /**
     * Insert a coin into the purse. The coin is only inserted if the purse has
 space for it and the coin has positive value. No worthless money!
     *
     * @param coin is a Valuable object to insert into purse
     * @throws IllegalArgumentException if the new coin is not the same currency
 as money in purse.
     * @return true if coin inserted, false if can't insert
     */
    public boolean insert(Valuable coin) {
        if (count() != 0) {
            if (!coin.getCurrency().equalsIgnoreCase(money.get(0).getCurrency())) {
                throw new IllegalArgumentException("Not the same currency");
            }
        }
        if (isFull() || coin.getValue() <= 0) {
            return false;
        }
        money.add(coin);
        setChanged();
        notifyObservers();
        return true;
    }

    /**
     * Withdraw the requested amount of money. Return an array of Valuables
     * withdrawn from purse, or return null if cannot withdraw the amount
     * requested.
     *
     * @param amount is the amount to withdraw
     * @return array of Valuable objects for money withdrawn, or null if cannot
     * withdraw requested amount.
     */
    public Valuable[] withdraw(double amount) {
        List<Valuable> returnValue = null;
        if(getBalance() >= amount) {
            returnValue = strategy.withdraw(amount,money.subList(0,money.size()));
        }
        if(returnValue != null){
            for(Valuable v : returnValue) money.remove(v);
            setChanged();
            notifyObservers();
            return returnValue.toArray(new Valuable[0]);
        }
        return null;
    }

    /**
     * toString returns a string description of the purse contents. It can
     * return whatever is a useful description.
     *
     * @return description of Purse
     */
    @Override
    public String toString() {
        return count() + " coin(s) with value " + getBalance();
    }

    /**
     * Get the purse currency.
     *
     * @return currency of the first coin in the purse.
     */
    public String getCurrency() {
        if (money.size() == 0) {
            return null;
        }
        return money.get(0).getCurrency();
    }

}
