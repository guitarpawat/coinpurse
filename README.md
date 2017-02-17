# Coinpurse

## Description
This CoinPurse has two class of valueable object, `Coin` and `BankNote`. Both are implement Valuable interface and `Valuable extends Comparable<Valuable>`.

## About interface
From the interface `Valuable` has two abstract method, `getValue()` and `getCurrency()`, and extends Comparable<Valuable>.
You can create more type (class) for monetary object just implements Valuable, it will support in all other classes in CoinPurse. This will support Liskov Substitution Principle (LSP) statement and OOP polymorphism.

## Testing
There are `Main` and `PurseTest` (junit) classes for testing.

## Utilities
`CoinUtil` is the example of applying CoinPurse class.
