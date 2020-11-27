package coinsorter.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class MaximumValueConstraintValidatorTest {
    @Test
    void testIsValidWithValidValue() throws Exception {
        final MaximumValueConstraintValidator constraintValidator = new MaximumValueConstraintValidator(10);

        assertTrue(constraintValidator.isValid(5));
    }

    @Test
    void testIsValidWithMaximumValue() throws Exception {
        final MaximumValueConstraintValidator constraintValidator = new MaximumValueConstraintValidator(10);

        assertTrue(constraintValidator.isValid(10));
    }

    @Test
    void testIsValidWithInvalidValue() throws Exception {
        final MaximumValueConstraintValidator constraintValidator = new MaximumValueConstraintValidator(10);

        assertFalse(constraintValidator.isValid(20));
    }

    @Test
    void testIsValidWithNullValue() throws Exception {
        final MaximumValueConstraintValidator constraintValidator = new MaximumValueConstraintValidator(10);

        assertTrue(constraintValidator.isValid(null));
    }
}
