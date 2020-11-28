package coinsorter.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ConstraintValidatorTest {
    @Test
    void testIsInvalidWhenValid() throws Exception {
        final ConstraintValidator<String> trueConstraintValidator = Mockito.mock(ConstraintValidator.class);
        Mockito.doReturn(true).when(trueConstraintValidator).isValid(Mockito.anyString());

        assertTrue(trueConstraintValidator.isValid("value"));
        assertFalse(trueConstraintValidator.isInvalid("value"));
    }
    
    @Test
    void testIsInvalidWhenInvalid() throws Exception {
        final ConstraintValidator<String> falseConstraintValidator = Mockito.mock(ConstraintValidator.class);
        Mockito.doReturn(false).when(falseConstraintValidator).isValid(Mockito.anyString());

        assertFalse(falseConstraintValidator.isValid("value"));
        assertTrue(falseConstraintValidator.isInvalid("value"));
    }
}
