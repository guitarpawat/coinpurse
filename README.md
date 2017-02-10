# Coinpurse

This coinpurse has 5 classes

## `Main`
Runs the ConsoleDialog class.

## `ConsoleDialog`
Makes user interact to control the Purse.

### Constructor
* `ConsoleDialog(Purse purse)`

### Methods
* `void run()` is the main interface of the ConsoleDialog.
* `void depositDialog()` shows deposite information to the user.
* `void withdrawDialog()` shows withdraw result to the user.

## `Coin`
This is the Coin object

### Constructors
* `Coin( double value, String currency )`
* `Coin( double value )` this will set current currency to "Baht"

### Methods
* `double getValue()` gets value of the Coin.
* `String getCurrency()` gets currency of the Coin.
* `boolean equals(Object obj)` checks that two Coins are equal.
* `int compareTo(Coin other)` compares two Coins, throws exception if there're not same currency.
* `String toString()` returns description of the Coin.

## `Purse`
Collects the Coins.

### Constructors
* `Purse( int capacity )`

### Methods
* `int count()` shows number of coins in the purse.
* `double getBalance()` gets total balance of all coins in the purse.
* `int getCapacity()` gets limit number of coins in the purse.
* `boolean isFull()` checks that purse are full or not.
* `boolean insert( Coin coin )` inserts new coin to the purse, throws exception if new coin don't have the same currency as first coin in the purse.
* `Coin[] withdraw( double amount )` withdraws coins and shows the coins that witdraw.
* `String toString()` returns the description of the purse.
* `String getCurrency()` returns the purse currency.

## `CoinUtil`
More codes doing with Coin.

### Methods
* `List<Coin> filterByCurrency(final List<Coin> coinlist, String currency)` returns the list of coins with specific currency.
* `void sortByCurrency(List<Coin> coins)` sorts list by currency. (Of course!)
* `void sumByCurrency(List<Coin> coins)` prints balance for each currency in the list.(Now, limited 1024 coins per currency.)

* `void main(String[] args)` for testing CoinUtil.
