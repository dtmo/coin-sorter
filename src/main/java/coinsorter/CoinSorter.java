package coinsorter;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class CoinSorter {
    private String currency;

    private int minimumValue;

    private int maximumValue;

    private Set<Integer> denominations;

    public CoinSorter(final String currency, final int minimumValue, final int maximumValue,
            final Set<Integer> denominations) {
        this.currency = currency;
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;
        this.denominations = denominations;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(final String currency) {
        Objects.requireNonNull(currency);
        if (currency.isBlank() == false) {
            this.currency = currency;
        } else {
            throw new IllegalArgumentException("Currency may not be blank");
        }
    }

    public int getMinimumValue() {
        return minimumValue;
    }

    public void setMinimumValue(final int minimumValue) {
        if (minimumValue >= 0 && minimumValue <= maximumValue) {
            this.minimumValue = minimumValue;
        } else {
            throw new IllegalArgumentException(
                    "The minimum value must be between 0 and the current maximum value: " + maximumValue);
        }
    }

    public int getMaximumValue() {
        return maximumValue;
    }

    public void setMaximumValue(final int maximumValue) {
        if (maximumValue >= minimumValue) {
            this.maximumValue = maximumValue;
        } else {
            throw new IllegalArgumentException(
                    "The maximum value must be at least the current minimum value: " + minimumValue);
        }
    }

    public Set<Integer> getDenominations() {
        return Collections.unmodifiableSet(denominations);
    }

    public void validateTotalCoinValue(final int value) {
        if (value < minimumValue || value > maximumValue) {
            throw new IllegalArgumentException("The value to calculate is not within the set boundaries: value=" + value
                    + ", minimum=" + minimumValue + ", maximum=" + maximumValue);
        }
    }

    public LooseChange convertToDenomination(final int value, final int denominationValue)
            throws IllegalArgumentException {
        validateTotalCoinValue(value);

        final LooseChange looseChange = LooseChange.toDenominations(value, Set.of(denominationValue));

        return looseChange;
    }

    public LooseChange convertToDenominations(final int value, final int excludedDenominationValue) {
        validateTotalCoinValue(value);

        final LooseChange looseChange = LooseChange.toDenominations(value, denominations.stream()
                .filter(denomination -> denomination != excludedDenominationValue).collect(Collectors.toSet()));

        return looseChange;
    }
}
