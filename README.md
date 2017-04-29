# CoinPurse

CoinPurse simulate monetary objects and Purse by using inheritance and polymorphism.

## Polymorphism

Polymorphism can be used when one task perform in different ways in many class. Sometimes, it like a draft for the classes.

## Inheritance

You can use methods and variables from superclass in subclasses if it is not private and can override if it is not final.

## Abstract class

Abstract classes is like an interface but you can write some codes to use it in subclasses without writing the same code in each class again.

## Observer

Purse class has extends Observable, so you can create any class to receive the event when it's fire. You can create and add the observer to the Main class.

## Strategy

Purse class can set WithdrawStrategy for select how to withdraw the money from purse. There are two WithdrawStrategy class for Purse :

1. `GreedyWithdraw` is the strategy by withdraw money from highest value first. This strategy can be use in most case, but not all.
1. `RecursiveWithdraw` is the strategy that looking all possible way to withdraw money, this may consume much memory and caused stack overflow.

All two strategies are implements WithdrawStrategy, so you can create more strategy to use it in Purse and set the strategy by using `setStrategy()` method in Purse.
