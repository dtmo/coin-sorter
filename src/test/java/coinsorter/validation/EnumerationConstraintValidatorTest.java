package coinsorter.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

class EnumerationConstraintValidatorTest {
    @Test
    void testIsValidWithValidValue() throws Exception {
        final EnumerationConstraintValidator<Integer> constraintValidator = new EnumerationConstraintValidator<>(
                Set.of(1, 2, 3, 5, 7, 11));

        assertTrue(constraintValidator.isValid(5));
    }

    @Test
    void testIsValidWithInvalidValue() throws Exception {
        final EnumerationConstraintValidator<Integer> constraintValidator = new EnumerationConstraintValidator<>(
                Set.of(1, 2, 3, 5, 7, 11));

        assertFalse(constraintValidator.isValid(4));
    }

    @Test
    void testIsValidWithNullValue() throws Exception {
        final EnumerationConstraintValidator<Integer> constraintValidator = new EnumerationConstraintValidator<>(
                Set.of(1, 2, 3, 5, 7, 11));

        assertTrue(constraintValidator.isValid(null));
    }
}
