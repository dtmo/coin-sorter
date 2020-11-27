package coinsorter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import org.junit.jupiter.api.Test;

class CoinSorterTest {
    @Test
    void testSetCurrencyWithValidValue() throws Exception {
        final CoinSorter coinSorter = new CoinSorter("GBP", 0, 10000, Set.of(200, 100, 50, 20, 10));

        coinSorter.setCurrency("USD");

        assertEquals("USD", coinSorter.getCurrency());
    }

    @Test
    void testSetCurrencyWithBlankValue() throws Exception {
        final CoinSorter coinSorter = new CoinSorter("GBP", 0, 10000, Set.of(200, 100, 50, 20, 10));

        assertThrows(IllegalArgumentException.class, () -> coinSorter.setCurrency(""));
    }

    @Test
    void testSetCurrencyWithNullValue() throws Exception {
        final CoinSorter coinSorter = new CoinSorter("GBP", 0, 10000, Set.of(200, 100, 50, 20, 10));

        assertThrows(NullPointerException.class, () -> coinSorter.setCurrency(null));
    }

    @Test
    void testSetMinimumValue() throws Exception {
        final CoinSorter coinSorter = new CoinSorter("GBP", 0, 10000, Set.of(200, 100, 50, 20, 10));

        coinSorter.setMinimumValue(10);

        assertEquals(10, coinSorter.getMinimumValue());
    }

    @Test
    void testSetMinimumValueLessThanZero() throws Exception {
        final CoinSorter coinSorter = new CoinSorter("GBP", 0, 10000, Set.of(200, 100, 50, 20, 10));

        assertThrows(IllegalArgumentException.class, () -> coinSorter.setMinimumValue(-10));
    }

    @Test
    void testSetMinimumValueGreaterThanMaxiumumValue() throws Exception {
        final CoinSorter coinSorter = new CoinSorter("GBP", 0, 10000, Set.of(200, 100, 50, 20, 10));

        assertThrows(IllegalArgumentException.class, () -> coinSorter.setMinimumValue(20000));
    }

    @Test
    void setMaximumValue() throws Exception {
        final CoinSorter coinSorter = new CoinSorter("GBP", 0, 10000, Set.of(200, 100, 50, 20, 10));

        coinSorter.setMaximumValue(20000);

        assertEquals(20000, coinSorter.getMaximumValue());
    }

    @Test
    void setMaximumValueLessThanMinimumValue() throws Exception {
        final CoinSorter coinSorter = new CoinSorter("GBP", 5000, 10000, Set.of(200, 100, 50, 20, 10));

        assertThrows(IllegalArgumentException.class, () -> coinSorter.setMaximumValue(250));
    }

    @Test
    void testConvertToDenomination() throws Exception {
        final CoinSorter coinSorter = new CoinSorter("GBP", 0, 10000, Set.of(200, 100, 50, 20, 10));

        final LooseChange result = coinSorter.convertToDenomination(5432, 20);

        assertEquals(5420, result.getValue());
        assertEquals(271, result.getDenominationQuantity(20));
    }

    @Test
    void testConvertToDenominations() throws Exception {
        final CoinSorter coinSorter = new CoinSorter("GBP", 0, 10000, Set.of(200, 100, 50, 20, 10));

        final LooseChange result = coinSorter.convertToDenominations(5432, 20);

        assertEquals(5430, result.getValue());
        assertEquals(27, result.getDenominationQuantity(200));
        assertEquals(0, result.getDenominationQuantity(100));
        assertEquals(0, result.getDenominationQuantity(50));
        assertEquals(0, result.getDenominationQuantity(20));
        assertEquals(3, result.getDenominationQuantity(10));
    }
}
