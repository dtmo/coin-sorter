# Coin Sorter

This code serves as a worked example of implementing a text UI based coin sorter application in Java. This readme file aims to describe the design decisions that were used and to explain some of the implementation details.

The details of the original assignment are not reproduced here, but will be described where appropriate in the discussion of the application design.

# Domain Model

The assignment requires the conversion of a total value of money into a collection of coins of various denominations. Where the selected denominations are not able to precisely represent the requested total value, the difference needs to be reported as a remainder.

## LooseChange Class

The *LooseChange* class represents an amount of loose change. Since the remainder between a requested total value and the value that may be represented by a set of denominations can be handled by simply subtracting the two value, we will not worry about storing the remainder as part of the definition of the loose change.

The *LooseChange* class then just needs to represent the denominations used and the quantity of each. This can be implemented as a *Map* keyed with denomination values and the quantity stored against each denomination.

```plantuml
class LooseChange {
    coins: Map<Integer, Integer>
}
```

