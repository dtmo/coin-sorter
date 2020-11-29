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
     * Creates a new instance of LooseChange representing, as closely as possible, a
     * given total value using a specified set of denominations. If the specified
     * denominations cannot represent the total value precisely, then the created
     * LooseChange instance will represent the nearest value that is not greater
     * than the specified total value.
     * 
     * @param value         The target total value to represent with the specified
     *                      denominations.
     * @param denominations The denominations of which to use multiples to represent
     *                      the total value.
     * @return A new LooseChange instance that represents the quantities of
     *         denominations used.
     */
    public static LooseChange from(final int value, final Set<Integer> denominations) {
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

    /**
     * Constructs a new instance of LooseChange.
     * 
     * @param coins A Map of denominations and quantities describing the loose
     *              change.
     */
    public LooseChange(final Map<Integer, Integer> coins) {
        this.coins = coins;
        this.value = coins.entrySet().stream().mapToInt(entry -> entry.getKey() * entry.getValue()).sum();
    }

    /**
     * Returns the total value of the loose change.
     * 
     * @return The total value of the loose change.
     */
    public int getTotalValue() {
        return value;
    }

    /**
     * Returns the quantity of a denomination present in the loose change. If the
     * specified denomination does not exist in the loose change then 0 is returned.
     * 
     * @param denomination The denomination for which to return the quantity.
     * @return The quantity of the denomination present in the loose change.
     */
    public int getDenominationQuantity(final Integer denomination) {
        return coins.getOrDefault(denomination, 0);
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
}