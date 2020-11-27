package coinsorter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;

class LooseChangeTest {
        @Test
        void testGetValue() throws Exception {
                final LooseChange looseChange = new LooseChange.Builder().withDenominations(50, 3)
                                .withDenominations(20, 2).withDenominations(5, 3).build();

                assertEquals(205, looseChange.getValue());
        }

        @Test
        void testGetDenominationQuantity() throws Exception {
                final LooseChange looseChange = new LooseChange.Builder().withDenominations(50, 3)
                                .withDenominations(20, 2).withDenominations(5, 3).build();

                assertEquals(3, looseChange.getDenominationQuantity(50));
        }

        @Test
        void testGetDenominationQuantityWithInvalidDenomination() throws Exception {
                final LooseChange looseChange = new LooseChange.Builder().withDenominations(50, 3)
                                .withDenominations(20, 2).withDenominations(5, 3).build();

                assertEquals(0, looseChange.getDenominationQuantity(13));
        }

        @Test
        void testToDenominationsWithoutRemmainder() throws Exception {
                final LooseChange looseChange = new LooseChange.Builder().withDenominations(1, 543).build();

                assertEquals(new LooseChange.Builder().withDenominations(200, 2).withDenominations(20, 7)
                                .withDenominations(1, 3).build(), looseChange.toDenominations(Set.of(200, 20, 1)));
        }

        @Test
        void testToDenominationsWithRemainder() throws Exception {
                final LooseChange result = new LooseChange.Builder().withDenominations(200, 2).withDenominations(20, 7)
                                .withDenominations(1, 3).build().toDenominations(Set.of(200, 20));

                assertEquals(540, result.getValue());
                assertEquals(2, result.getDenominationQuantity(200));
                assertEquals(7, result.getDenominationQuantity(20));
        }
}
