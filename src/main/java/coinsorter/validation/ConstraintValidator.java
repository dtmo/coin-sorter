package coinsorter.validation;

/**
 * ConstraintValidator implements a validation constraint.
 */
public interface ConstraintValidator<T> {
    /**
     * Returns <code>true</code> if a value is valid and <code>false</code> if not.
     * 
     * @param value The value to validate.
     * @return <code>true</code> if a value is valid and <code>false</code> if not.
     */
    boolean isValid(T value);

    /**
     * Returns <code>true</code> if a value is invalid and <code>false</code> if
     * not.
     * 
     * @param value The value to validate.
     * @return <code>true</code> if a value is invalid and <code>false</code> if
     *         not.
     */
    default boolean isInvalid(final T value) {
        return isValid(value) == false;
    }

    /**
     * Returns a message explaining why a valid is invalid.
     * 
     * @param value The invalid value.
     * @return A message explaining why a valid is invalid.
     */
    String getConstraintViolationMessage(T value);
}
