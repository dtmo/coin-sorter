package coinsorter.validation;

public class MaximumValueConstraintValidator implements ConstraintValidator<Integer> {
    private final int maximum;

    public MaximumValueConstraintValidator(final int maximum) {
        this.maximum = maximum;
    }

    @Override
    public String getConstraintViolationMessage(final Integer value) {
        return "Value may not be more than " + maximum + ": " + value;
    }

    @Override
    public boolean isValid(final Integer value) {
        if (value != null) {
            return value <= maximum;
        } else {
            return true;
        }
    }
}
