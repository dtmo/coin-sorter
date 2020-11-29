# Coin Sorter

This code serves as a worked example of implementing a text UI based coin sorter application in Java. This readme file aims to describe the design decisions that were used and to explain some of the implementation details.

The details of the original assignment are not reproduced here, but will be described where appropriate in the discussion of the application design.

## Domain Model

The assignment requires the conversion of a total value of money into a collection of coins of various denominations. Where the selected denominations are not able to precisely represent the requested total value, the difference needs to be reported as a remainder.

The domain model required for the assignment is relatively simple and can be provided with just two classes: *LooseChange* and *CoinSorter*.

### LooseChange Class

The LooseChange class represents an amount of loose change. Since the remainder between a requested total value and the value that may be represented by a set of denominations can be handled by simply subtracting the two value, we will not worry about storing the remainder as part of the definition of the loose change.

LooseChange then just needs to represent the denominations used and the quantity of each. This can be implemented as a *Map* keyed with denomination values and the quantity stored against each denomination.

As LooseChange is a value type, we will implement it as being immutable. It's methods will therefore just support querying its state such as getting the total value, or the quentity of a partcular denomination.

As a value type, where it is reasonable that instances may be compared for equality, it also overrides *equals*, *hashCode* and *toString*.

### CoinSorter Class

The CoinSorter class represents the state of the application itself. It stores the application configuration values including the currency, set of denominations, and maximum and minimum allowed total values.

CoinSorter is not a value type and in the assignment is specified as being mutable so that its configuration may be updated. It provides business logic methods to convert values into collections of denominations following various rules.
