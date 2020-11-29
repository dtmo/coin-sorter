package coinsorter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

class LooseChangeTest {
        @Test
        void testGetTotalValue() throws Exception {
                final LooseChange looseChange = new LooseChange(Map.of(50, 3, 20, 2, 5, 3));

                assertEquals(205, looseChange.getTotalValue());
        }

        @Test
        void testGetDenominationQuantity() throws Exception {
                final LooseChange looseChange = new LooseChange(Map.of(50, 3, 20, 2, 5, 3));

                assertEquals(3, looseChange.getDenominationQuantity(50));
        }

        @Test
        void testGetDenominationQuantityWithInvalidDenomination() throws Exception {
                final LooseChange looseChange = new LooseChange(Map.of(50, 3, 20, 2, 5, 3));

                assertEquals(0, looseChange.getDenominationQuantity(13));
        }

        @Test
        void testFrom() throws Exception {
                final LooseChange looseChange = LooseChange.from(388, Set.of(200, 100, 50, 20, 10, 5, 2, 1));

                assertEquals(388, looseChange.getTotalValue());
                assertEquals(1, looseChange.getDenominationQuantity(200));
                assertEquals(1, looseChange.getDenominationQuantity(100));
                assertEquals(1, looseChange.getDenominationQuantity(50));
                assertEquals(1, looseChange.getDenominationQuantity(20));
                assertEquals(1, looseChange.getDenominationQuantity(10));
                assertEquals(1, looseChange.getDenominationQuantity(5));
                assertEquals(1, looseChange.getDenominationQuantity(2));
                assertEquals(1, looseChange.getDenominationQuantity(1));
        }
}
