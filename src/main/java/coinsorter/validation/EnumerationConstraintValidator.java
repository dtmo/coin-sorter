package coinsorter.validation;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Validates that a value is one of an enumerated set of valid values.
 */
public class EnumerationConstraintValidator<T> implements ConstraintValidator<T> {
    final Collection<T> values;

    /**
     * Constructs a new instance of EnumerationConstraintValidator.
     * 
     * @param values The collection of valid values.
     */
    public EnumerationConstraintValidator(final Collection<T> values) {
        this.values = values;
    }

    @Override
    public String getConstraintViolationMessage(final T value) {
        return values.stream().map(String::valueOf).collect(Collectors.joining(", ", "Value must be one of: ", ""));
    }

    @Override
    public boolean isValid(T value) {
        if (value != null) {
            return values.contains(value);
        } else {
            return true;
        }
    }
}
