package coinpurse.strategy;

import coinpurse.Valuable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Greedy money withdraw strategy for purse.
 * This class will sort Valuable from the list.
 * @author Pawat Nakpiphatkul
 */
public class GreedyWithdraw implements WithdrawStrategy {

    /**
     * @see WithdrawStrategy#withdraw(double, java.util.List) 
     */
    @Override
    public List<Valuable> withdraw(double amount, List<Valuable> money) {
        if (amount <= 0) {
            return null;
        }
        Collections.sort(money);
        Collections.reverse(money);
        List<Valuable> returnList = new ArrayList();
        for (Valuable c : money) {
            double value = c.getValue();
            if (amount - value >= 0) {
                amount -= value;
                returnList.add(c);
                if (amount == 0) {
                    break;
                }
            }
        }
        if (amount == 0.0) {
            for (Valuable c : returnList) {
                money.remove(c);
            }
            return returnList;
        }
        return null;
    }
    
}
