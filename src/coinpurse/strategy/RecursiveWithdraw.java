package coinpurse.strategy;

import coinpurse.Valuable;
import java.util.ArrayList;
import java.util.List;

/**
 * Recursive money withdraw strategy for purse.
 * @author Pawat Nakpiphatkul
 */
public class RecursiveWithdraw implements WithdrawStrategy {
    
    /**
     * @see WithdrawStrategy#withdraw(double, java.util.List) 
     */
    @Override
    public List<Valuable> withdraw(double amount, List<Valuable> money) {
        List<Valuable> purseList = helper(amount,money.subList(0,money.size()));
        List<Valuable> returnList = new ArrayList<>(money);
        if(purseList != null) {
            for(Valuable v : purseList) returnList.remove(v);
            return returnList;
        }
        return null;
    }
    
    /**
     * Helper method for RecursiveWithdraw.
     * @param amount is amount left to withdraw.
     * @param money is current Valuable in purse.
     * @return Valuable left in purse.
     */
    private List<Valuable> helper(double amount, List<Valuable> money) {
        if(money.size() == 0) return null;
        Valuable current = money.get(0);
        if(amount >= current.getValue()) {
            if(amount - current.getValue() == 0) return new ArrayList<Valuable>(money.subList(1,money.size()));
            List<Valuable> tempList;
            if(( tempList=helper(amount-current.getValue(),money.subList(1,money.size())) ) != null ) {
                return tempList;
            }
        }
        List<Valuable> returnList = helper(amount,money.subList(1,money.size()));
        if(returnList != null) returnList.add(current);
        return returnList;
    }
}
