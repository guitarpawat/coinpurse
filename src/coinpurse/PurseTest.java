package coinpurse;

import coinpurse.strategy.GreedyWithdraw;
import coinpurse.strategy.RecursiveWithdraw;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the Purse. This is a JUnit 4 test. To run these tests, right click on
 * this file (in Navigator pane) and choose Run As -> JUnit test
 *
 * @author Resident Evil
 * @version 2017.02.01
 */
public class PurseTest {

    /**
     * tolerance for comparing two double values
     */
    private static final double TOL = 1.0E-6;

    /**
     * Sets up the test fixture. Called before every test case method.
     */
    @Before
    public void setUp() {
    }

    /**
     * Easy test that the Purse constructor is working.
     */
    @Test
    public void testConstructor() {
        Purse purse = new Purse(3);
        assertEquals(3, purse.getCapacity());
        assertEquals(false, purse.isFull());
        assertEquals(0, purse.count());
    }

    /**
     * Insert some coins. Easy test.
     */
    @Test
    public void testInsert() {
        Purse purse = new Purse(3);
        Coin coin1 = new Coin(5);
        Coin coin2 = new Coin(10);
        Coin coin3 = new Coin(1);
        assertTrue(purse.insert(coin1));
        assertTrue(purse.insert(coin3));
        assertTrue(purse.insert(coin2));
        assertEquals(3, purse.count());
        // purse is full so insert should fail
        assertFalse(purse.insert(new Coin(1)));
    }

    /**
     * Insert should reject coin with no value.
     */
    @Test
    public void testInsertNoValue() {
        Purse purse = new Purse(3);
        Coin fakeCoin = new Coin(0);
        assertFalse(purse.insert(fakeCoin));
    }

    @Test
    public void testIsFull() {   // borderline case (capacity 1)
        Purse purse = new Purse(1);
        assertFalse(purse.isFull());
        purse.insert(new Coin(1));
        assertTrue(purse.isFull());
        // real test
        int capacity = 4;
        purse = new Purse(capacity);
        for (int k = 1; k <= capacity; k++) {
            assertFalse(purse.isFull());
            purse.insert(new Coin(k));
        }
        // full now
        assertTrue(purse.isFull());
        assertFalse(purse.insert(new Coin(5)));
    }

    /**
     * Should be able to insert same coin many times, since spec doesn't say
     * anything about this.
     */
    @Test
    public void testInsertSameCoin() {
        Purse purse = new Purse(5);
        Coin coin = new Coin(10);
        assertTrue(purse.insert(coin));
        assertTrue(purse.insert(coin)); // should be allowed
        assertTrue(purse.insert(coin)); // should be allowed
    }

    @Test
    public void testEasyWithdraw() {
        Purse purse = new Purse(10);
        int[] values = {1, 10, 1000};
        for (int value : values) {
            Coin coin = new Coin(value);
            assertTrue(purse.insert(coin));
            assertEquals(value, purse.getBalance(), TOL);
            Valuable[] result = purse.withdraw(value);
            assertTrue(result != null);
            assertEquals(1, result.length);
            assertSame(coin, result[0]);
            assertEquals(0, purse.getBalance(), TOL);
        }
    }

    @Test
    public void testMultiWithdraw() {
        Purse purse = new Purse(10);
        int value = 1;
        double amount1 = 0;
        double amount2 = 0;
        for (int k = 1; k < 10; k = k + 2) {
            assertTrue(purse.insert(new Coin(value)));
            amount1 += value;
            value = 2 * value;
            assertTrue(purse.insert(new Coin(value)));
            amount2 += value;
            value = 2 * value;
        }
        assertEquals(amount1 + amount2, purse.getBalance(), TOL);
        assertEquals(10, purse.count());
        Valuable[] wd1 = purse.withdraw(amount1);
        assertEquals(amount1, sumValue(wd1), TOL);
        assertEquals(amount2, purse.getBalance(), TOL);
        Valuable[] wd2 = purse.withdraw(amount2);
        assertEquals(0, purse.getBalance(), TOL);
    }

    @Test
    public void testImpossibleWithdraw() {
        Purse purse = new Purse(10);
        assertNull(purse.withdraw(1));
        purse.insert(new Coin(20));
        assertNull(purse.withdraw(1));
        assertNull(purse.withdraw(19));
        assertNull(purse.withdraw(21));
        purse.insert(new Coin(20));
        assertNull(purse.withdraw(30));
    }

    @Test
    public void testBankNoteDeposit() {
        Purse purse = new Purse(4);
        purse.insert(new Coin(5,"dollar"));
        purse.insert(new BankNote(100,"dollar"));
        purse.insert(new Coin(1,"dollar"));
        purse.insert(new BankNote(20,"dollar"));
        assertEquals(126, purse.getBalance(), TOL);
        assertTrue(purse.getCurrency().equalsIgnoreCase("dollar"));
    }
    
    @Test
    public void testSingletonInstance() {
        MoneyFactory factory = MoneyFactory.initialize("Malaysia");
        MoneyFactory f1 = factory.getInstance();
        MoneyFactory f2 = factory.getInstance();
        assertTrue(f1 == f2);
        factory = MoneyFactory.initialize("Thailand");
        MoneyFactory f3 = factory.getInstance();
        MoneyFactory f4 = factory.getInstance();
        assertTrue(f3 == f4);
    }
    
    @Test
    public void testGreedyWithdraw() {
        Purse purse = new Purse(15);
        purse.setStrategy(new GreedyWithdraw());
        for(int i=0 ; i<5 ; i++) purse.insert(new Coin(1));
        purse.insert(new BankNote(1000));
        purse.insert(new Coin(5));
        purse.insert(new BankNote(100));
        purse.insert(new Coin(10));
        purse.insert(new Coin(1));
        purse.insert(new Coin(2));
        purse.insert(new BankNote(500));
        purse.insert(new Coin(5));
        purse.insert(new BankNote(50));
        purse.insert(new BankNote(20));
        Valuable[] withdraw = purse.withdraw(85);
        assertEquals(85,sumValue(withdraw),TOL);
        Valuable[] check = {new BankNote(50),new BankNote(20),new Coin(10),new Coin(5)};
        Assert.assertArrayEquals(check,withdraw);
    }

        @Test
    public void testRecursiveWithdraw() {
        Purse purse = new Purse(5);
        purse.setStrategy(new RecursiveWithdraw());
        for(int i=0 ; i<3 ; i++) purse.insert(new Coin(2));
        purse.insert(new Coin(5));
        purse.insert(new Coin(10));
        Valuable[] withdraw = purse.withdraw(16);
        assertEquals(16,sumValue(withdraw),TOL);
    }
    
    @Test
    public void testSetStrategy() {
        Purse purse = new Purse(5);
        for(int i=0 ; i<3 ; i++) purse.insert(new Coin(2));
        purse.insert(new Coin(5));
        purse.insert(new Coin(10));
        Valuable[] withdraw = purse.withdraw(16);
        assertEquals(16,sumValue(withdraw),TOL);
        purse = new Purse(5);
        for(int i=0 ; i<3 ; i++) purse.insert(new Coin(2));
        purse.insert(new Coin(5));
        purse.insert(new Coin(10));
        purse.setStrategy(new GreedyWithdraw());
        withdraw = purse.withdraw(16);
        assertNotEquals(16,sumValue(withdraw),TOL);
        purse = new Purse(5);
        for(int i=0 ; i<3 ; i++) purse.insert(new Coin(2));
        purse.insert(new Coin(5));
        purse.insert(new Coin(10));
        purse.setStrategy(new RecursiveWithdraw());
        withdraw = purse.withdraw(16);
        assertEquals(16,sumValue(withdraw),TOL);
        }
    
    /**
     * Sum the value of some coins.
     *
     * @param coins array of coins
     * @return sum of values of the coins
     */
    private double sumValue(Valuable[] coins) {
        if (coins == null) {
            return 0;
        }
        double sum = 0;
        for (Valuable c : coins) {
            if (c != null) {
                sum += c.getValue();
            }
        }
        return sum;
    }
}
