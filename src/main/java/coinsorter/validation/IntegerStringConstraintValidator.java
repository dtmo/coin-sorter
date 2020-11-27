package coinsorter.validation;

public class IntegerStringConstraintValidator implements ConstraintValidator<String> {
    public static final IntegerStringConstraintValidator INSTANCE = new IntegerStringConstraintValidator();

    @Override
    public String getConstraintViolationMessage(final String value) {
        return "Value must be an integer number";
    }

    @Override
    public boolean isValid(final String value) {
        if (value != null) {
            try {
                Integer.parseInt(value);
                return true;
            } catch (final NumberFormatException e) {
                return false;
            }
        } else {
            return true;
        }
    }
}
