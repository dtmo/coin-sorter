package coinsorter.validation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IntegerStringConstraintValidatorTest {
    @Test
    void testIsValid() throws Exception {
        assertTrue(IntegerStringConstraintValidator.INSTANCE.isValid("123"));
    }

    @Test
    void testIsValidInvalidValue() throws Exception {
        assertFalse(IntegerStringConstraintValidator.INSTANCE.isValid("abc"));
    }

    @Test
    void testIsValidNullValue() throws Exception {
        assertTrue(IntegerStringConstraintValidator.INSTANCE.isValid(null));
    }
}
