package coinsorter;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * CoinSorter provides methods to sort coins, between a minimum and maximum
 * value, into quantities of configured denominations.
 */
public class CoinSorter {
    private String currency;

    private int minimumValue;

    private int maximumValue;

    private Set<Integer> denominations;

    /**
     * Constucts a new instance of CoinSorter.
     * 
     * @param currency      The currency.
     * @param minimumValue  The minimum allowed value of coins.
     * @param maximumValue  The maximum allowed value of coins.
     * @param denominations The set of denominations to use.
     */
    public CoinSorter(final String currency, final int minimumValue, final int maximumValue,
            final Set<Integer> denominations) {
        this.currency = currency;
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;
        this.denominations = denominations;
    }

    /**
     * Returns the currency.
     * 
     * @return The currency.
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the currency. Allowable values must be non-null and non-blank.
     * 
     * @param currency The currency to set.
     */
    public void setCurrency(final String currency) {
        Objects.requireNonNull(currency);
        if (currency.isBlank() == false) {
            this.currency = currency;
        } else {
            throw new IllegalArgumentException("Currency may not be blank");
        }
    }

    /**
     * Returns the minimum allowed value of coins.
     * 
     * @return The minimum allowed value of coins.
     */
    public int getMinimumValue() {
        return minimumValue;
    }

    /**
     * Sets the minimum allowed value of coins. The value must be no greater than
     * the current maximum.
     * 
     * @param minimumValue The minimum allowed value of coins to set.
     */
    public void setMinimumValue(final int minimumValue) {
        if (minimumValue >= 0 && minimumValue <= maximumValue) {
            this.minimumValue = minimumValue;
        } else {
            throw new IllegalArgumentException(
                    "The minimum value must be between 0 and the current maximum value: " + maximumValue);
        }
    }

    /**
     * Returns the maximum allowed value of coins.
     * 
     * @return The maximum allowed value of coins.
     */
    public int getMaximumValue() {
        return maximumValue;
    }

    /**
     * Sets the maximum allowed value of coins. The value must be no less than the
     * current minimum.
     * 
     * @param maximumValue The maximum allowed value of coins to set.
     */
    public void setMaximumValue(final int maximumValue) {
        if (maximumValue >= minimumValue) {
            this.maximumValue = maximumValue;
        } else {
            throw new IllegalArgumentException(
                    "The maximum value must be at least the current minimum value: " + minimumValue);
        }
    }

    /**
     * Returns the current set of denominations.
     * 
     * @return The current set of denominations.
     */
    public Set<Integer> getDenominations() {
        return Collections.unmodifiableSet(denominations);
    }

    /**
     * Validates that a total coin valid is within the minimum and maximum allowed.
     * 
     * @param value The value to validate.
     * @throws IllegalArgumentException if the value is not within the configured
     *                                  minimum and maximum.
     */
    public void validateTotalCoinValue(final int value) throws IllegalArgumentException {
        if (value < minimumValue || value > maximumValue) {
            throw new IllegalArgumentException("The value to calculate is not within the set boundaries: value=" + value
                    + ", minimum=" + minimumValue + ", maximum=" + maximumValue);
        }
    }

    /**
     * Converts a total value of coins into the closest loose change no greater than
     * the specified value that may be created with a given denomination.
     * 
     * @param value             The total value of coins to convert.
     * @param denominationValue The denomination of coins to use.
     * @return An instance of {@link LooseChange} representing the closest value no
     *         greater than the specified total value created with a given
     *         denomination.
     * @throws IllegalArgumentException if the total value is not within the
     *                                  configured minimum and maximum.
     */
    public LooseChange coinCalculator(final int value, final int denominationValue) throws IllegalArgumentException {
        validateTotalCoinValue(value);

        final LooseChange looseChange = LooseChange.from(value, Set.of(denominationValue));

        return looseChange;
    }

    /**
     * Converts a total value into the closest loose change no greater than the
     * specified value, using the configured denominations excluding a specified
     * denomination.
     * 
     * @param value                The total value of coins to convert.
     * @param excludedDenomination The denomination to exclude.
     * @return An instance of {@link LooseChange} representing the closest value no
     *         greater than the specified total value created with the configured
     *         denominations excluding a specified denomination.
     * @throws IllegalArgumentException if the total value is not within the
     *                                  configured minimum and maximum.
     */
    public LooseChange multiCoinCalculator(final int value, final int excludedDenomination)
            throws IllegalArgumentException {
        validateTotalCoinValue(value);

        final LooseChange looseChange = LooseChange.from(value, denominations.stream()
                .filter(denomination -> denomination != excludedDenomination).collect(Collectors.toSet()));

        return looseChange;
    }
}
