package coinsorter.validation;

public interface ConstraintValidator<T> {
    boolean isValid(T value);

    default boolean isInvalid(final T value) {
        return isValid(value) == false;
    }

    String getConstraintViolationMessage(T value);
}
