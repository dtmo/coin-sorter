package coinsorter.textui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import coinsorter.validation.ConstraintValidator;
import coinsorter.validation.IntegerStringConstraintValidator;
import coinsorter.validation.NotBlankConstraintValidator;

public class InputSupport {
    private InputSupport() {
        // Private constructor
    }

    public static String readInput() {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (final IOException e) {
            throw new IllegalStateException("Could not read input", e);
        }
    }

    @SafeVarargs
    public static String promptForString(final String prompt,
            final ConstraintValidator<String>... constraintValidators) {
        List<String> constraintViolationMessages = Collections.emptyList();
        String value = null;
        do {
            System.out.println(prompt);
            final String input = readInput();

            constraintViolationMessages = Arrays.stream(constraintValidators)
                    .filter(constraintValidator -> constraintValidator.isInvalid(input))
                    .map(constraintValidator -> constraintValidator.getConstraintViolationMessage(input))
                    .collect(Collectors.toList());
            if (constraintViolationMessages.isEmpty()) {
                value = input;
            } else {
                System.out
                        .println("Invalid input: \"" + input + "\". " + String.join(", ", constraintViolationMessages));
            }
        } while (constraintViolationMessages.isEmpty() == false);

        return value;
    }

    @SafeVarargs
    public static int promptForInt(final String prompt, final ConstraintValidator<Integer>... constraintValidators) {
        List<String> constraintViolationMessages = Collections.emptyList();
        int value = 0;
        do {
            final String integerString = promptForString(prompt, NotBlankConstraintValidator.INSTANCE,
                    IntegerStringConstraintValidator.INSTANCE);
            final int integer = Integer.parseInt(integerString);

            constraintViolationMessages = Arrays.stream(constraintValidators)
                    .filter(constraintValidator -> constraintValidator.isInvalid(integer))
                    .map(constraintValidator -> constraintValidator.getConstraintViolationMessage(integer))
                    .collect(Collectors.toList());
            if (constraintViolationMessages.isEmpty()) {
                value = integer;
            } else {
                System.out.println(
                        "Invalid input: \"" + integer + "\". " + String.join(", ", constraintViolationMessages));
            }
        } while (constraintViolationMessages.isEmpty() == false);

        return value;
    }
}
