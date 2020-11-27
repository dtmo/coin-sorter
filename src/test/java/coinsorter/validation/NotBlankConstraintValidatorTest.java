package coinsorter.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class NotBlankConstraintValidatorTest {
    @Test
    void testIsValidWithValidValue() throws Exception {
        assertTrue(NotBlankConstraintValidator.INSTANCE.isValid("value"));
    }

    @Test
    void testIsValidWithInvalidValue() throws Exception {
        assertFalse(NotBlankConstraintValidator.INSTANCE.isValid(""));
    }

    @Test
    void testIsValidWithNullValue() throws Exception {
        assertTrue(NotBlankConstraintValidator.INSTANCE.isValid(null));
    }
}
