package coinsorter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * LooseChange represents a collection of coins of various denominations.
 */
public class LooseChange {
    /**
     * Calculates the quantities of each denomination to, as closely as possible,
     * represent the given total value. If the specified denominations cannot
     * represent the total value exactly, then they will represent the nearest value
     * that is not greater than the specified total value.
     * 
     * @param value         The target total value to represent with the specified
     *                      denominations.
     * @param denominations The denominations of which to use multiples to represent
     *                      the total value.
     * @return A new LooseChange instance that represents the quantities of
     *         denominations used.
     */
    public static LooseChange toDenominations(final int value, final Set<Integer> denominations) {
        List<Integer> orderedDenominations = new ArrayList<>(denominations);
        Collections.sort(orderedDenominations, Collections.reverseOrder());

        int remainder = value;

        final Map<Integer, Integer> coins = new HashMap<>();

        for (final Integer denomination : orderedDenominations) {
            final int quantity = remainder / denomination;
            if (quantity > 0) {
                remainder = remainder % denomination;
                coins.put(denomination, quantity);
            }
        }

        return new LooseChange(coins);
    }

    private final Map<Integer, Integer> coins;
    private final int value;

    public LooseChange(final Map<Integer, Integer> coins) {
        this.coins = coins;
        this.value = coins.entrySet().stream().mapToInt(entry -> entry.getKey() * entry.getValue()).sum();
    }

    public int getValue() {
        return value;
    }

    public int getDenominationQuantity(final Integer denomination) {
        return coins.getOrDefault(denomination, 0);
    }

    public LooseChange toDenominations(final Set<Integer> denominations) {
        return toDenominations(value, denominations);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((coins == null) ? 0 : coins.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LooseChange other = (LooseChange) obj;
        if (coins == null) {
            if (other.coins != null)
                return false;
        } else if (!coins.equals(other.coins))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "LooseChange [coins=" + coins + "]";
    }

    public static class Builder {
        private Map<Integer, Integer> coins = new HashMap<>();

        public Builder withDenominations(final Integer denomination, final int quantity) {
            coins.merge(denomination, quantity, (oldValue, newValue) -> oldValue += newValue);

            return this;
        }

        public LooseChange build() {
            return new LooseChange(new HashMap<>(coins));
        }
    }
}