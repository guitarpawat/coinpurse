package coinpurse;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Some Valuable utility methods for practice using Lists and Comparator.
 *
 * @author Pawat Nakpiphatkul
 */
public class CoinUtil {

    /**
     * Method that examines all the coins in a List and returns only the coins
     * that have a currency that matches the parameter value.
     *
     * @param coinlist is a List of Valuable objects. This list is not modified.
     * @param currency is the currency we want. Must not be null.
     * @throws IllegalArgumentException if currency is null.
     * @return a new List containing only the elements from coinlist that have
     * the requested currency.
     */
    public static List<Valuable> filterByCurrency(final List<Valuable> coinlist, String currency) {
        if (currency == null) throw new IllegalArgumentException("Currency cannot be null");
        Predicate<Valuable> filter = (c) -> (c.getCurrency().equalsIgnoreCase(currency));
        return coinlist.stream().filter(filter).collect(Collectors.toList());
//        List<Valuable> temp = new ArrayList<>();
//        for (Valuable c : coinlist) {
//            if (c.getCurrency().equalsIgnoreCase(currency)) {
//                temp.add(c);
//            }
//        }
//        return Collections.unmodifiableList(temp); // return a list of coin references copied from coinlist
    }
    
    /**
     * Returning the larger of a and b, according to the
     * natural ordering (defined by compartTo).
     * @return the maximum value.
     */
    public static <E extends Comparable<? super E>> E max(E... input) {
        if(input.length <= 0) return null;
        E max = input[0];
        for(E e : input) {
            if(e.compareTo(max) > 0) max = e;
        }
        return max;
    }

    /**
     * Method to sort a list of coins by currency. On return, the list (coins)
     * will be ordered by currency.
     *
     * @param coins is a List of Valuable objects we want to sort.
     */
    public static void sortByCurrency(List<? super Valuable> coins) {
        coins.sort(new CompareByCurrency());
    }

    /**
     * Sum coins by currency and print the sum for each currency. Print one line
     * for the sum of each currency. For example: coins = { Valuable(1,"Baht"),
     * Valuable(20,"Ringgit"), Valuable(10,"Baht"), Valuable(0.5,"Ringgit") } then
     * sumByCurrency(coins) would print:
     *
     * 11.00 Baht 20.50 Ringgit
     *
     * Hint: this is easy if you sort the coins by currency first. :-)
     */
    public static void sumByCurrency(List<Valuable> coins) {
        List<Valuable> copy = new ArrayList<>();
        copy.addAll(coins);
        sortByCurrency(copy);
        List<Purse> temp = new ArrayList<>();
        temp.add(new Purse(Integer.MAX_VALUE));
        int count = 0;
        for (Valuable c : copy) {
            try {
                temp.get(count).insert(c);
            } catch (IllegalArgumentException e) {
                temp.add(new Purse(Integer.MAX_VALUE));
                temp.get(++count).insert(c);
            }
        }
        for (Purse p : temp) {
            System.out.println(String.format("%.2f %s", p.getBalance(), p.getCurrency()));
        }
    }

    /**
     * This method contains some code to test the above methods.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        String currency = "Rupee";
        System.out.println("Filter coins by currency of " + currency);
        List<Valuable> coins = makeInternationalValuables();
        int size = coins.size();
        System.out.print(" INPUT: ");
        printList(coins, " ");
        List<Valuable> rupees = filterByCurrency(coins, currency);
        System.out.print("RESULT: ");
        printList(rupees, " ");
        if (coins.size() != size) {
            System.out.println("Error: you changed the original list.");
        }

        System.out.println("\nSort coins by currency");
        coins = makeInternationalValuables();
        System.out.print(" INPUT: ");
        printList(coins, " ");
        sortByCurrency(coins);
        System.out.print("RESULT: ");
        printList(coins, " ");

        System.out.println("\nSum coins by currency");
        coins = makeInternationalValuables();
        System.out.print("coins= ");
        printList(coins, " ");
        sumByCurrency(coins);

        Valuable result = max(new BankNote(20),new Coin(5),new Coin(10));
        System.out.println(">> "+result);
    }

    /**
     * Make a list of coins containing different currencies.
     */
    public static List<Valuable> makeInternationalValuables() {
        List<Valuable> money = new ArrayList<Valuable>();
        money.addAll(makeValuables("Baht", 0.25, 1.0, 2.0, 5.0, 10.0, 10.0));
        money.addAll(makeValuables("Ringgit", 2.0, 50.0, 1.0, 5.0));
        money.addAll(makeValuables("Rupee", 0.5, 0.5, 10.0, 1.0));
        // randomize the elements
        Collections.shuffle(money);
        return money;
    }

    /**
     * Make a list of coins using given values.
     */
    public static List<BankNote> makeValuables(String currency, double... values) {
        List<BankNote> list = new ArrayList<BankNote>();
        for (double value : values) {
            if(value<20.0) list.add(new BankNote(value, currency));
            else list.add(new BankNote(value, currency));
        }
        return list;
    }

    /**
     * Print the list on the console, on one line.
     */
    public static void printList(List items, String separator) {
        Iterator iter = items.iterator();
        while (iter.hasNext()) {
            System.out.print(iter.next());
            if (iter.hasNext()) {
                System.out.print(separator);
            }

        }
        System.out.println(); // end the line
    }
}

/**
 * Class for comparing two Valuables by currency.
 */
class CompareByCurrency <T extends Valuable> implements Comparator<T> {

    /**
     * Just a constructor
     */
    public CompareByCurrency() {
    }

    /**
     * Compare two coins by currency.
     *
     * @param c1 is the first Valuable.
     * @param c2 is the second Valuable.
     * @return positive integer if c1 is greater than c2, negative if less than,
     * else zero.
     */
    @Override
    public int compare(T c1, T c2) {
        return c1.getCurrency().compareToIgnoreCase(c2.getCurrency());
    }
}
