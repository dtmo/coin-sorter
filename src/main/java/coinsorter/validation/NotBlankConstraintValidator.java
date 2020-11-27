package coinsorter.validation;

public class NotBlankConstraintValidator implements ConstraintValidator<String> {
    public static final NotBlankConstraintValidator INSTANCE = new NotBlankConstraintValidator();

    @Override
    public String getConstraintViolationMessage(final String value) {
        return "Value may not be blank";
    }

    @Override
    public boolean isValid(final String value) {
        if (value != null) {
            return value.isBlank() == false;
        } else {
            return true;
        }
    }
}
