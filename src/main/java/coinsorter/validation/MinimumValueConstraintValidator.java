package coinsorter.validation;

/**
 * 
 * MinimumValueConstraintValidator validates that a value is no less than a
 * specified minimum.
 */
public class MinimumValueConstraintValidator implements ConstraintValidator<Integer> {
    private final int minimum;

    /**
     * Constructs a new instance of MinimumValueConstraintValidator.
     * 
     * @param minimum The minimum valid value.
     */
    public MinimumValueConstraintValidator(final int minimum) {
        this.minimum = minimum;
    }

    @Override
    public String getConstraintViolationMessage(final Integer value) {
        return "Value may not be less than " + minimum + ": " + value;
    }

    @Override
    public boolean isValid(final Integer value) {
        if (value != null) {
            return value >= minimum;
        } else {
            return true;
        }
    }
}
