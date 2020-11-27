package coinsorter.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class MinimumValueConstraintValidatorTest {
    @Test
    void testIsValidWithValidValue() throws Exception {
        final MinimumValueConstraintValidator constraintValidator = new MinimumValueConstraintValidator(10);

        assertTrue(constraintValidator.isValid(15));
    }

    @Test
    void testIsValidWithMinimumValue() throws Exception {
        final MinimumValueConstraintValidator constraintValidator = new MinimumValueConstraintValidator(10);

        assertTrue(constraintValidator.isValid(10));
    }

    @Test
    void testIsValidWithInvalidValue() throws Exception {
        final MinimumValueConstraintValidator constraintValidator = new MinimumValueConstraintValidator(10);

        assertFalse(constraintValidator.isValid(5));
    }

    @Test
    void testIsValidWithNullValue() throws Exception {
        final MinimumValueConstraintValidator constraintValidator = new MinimumValueConstraintValidator(10);

        assertTrue(constraintValidator.isValid(null));
    }
}
