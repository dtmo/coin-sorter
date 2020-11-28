package coinsorter.textui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import coinsorter.validation.ConstraintValidator;
import coinsorter.validation.IntegerStringConstraintValidator;
import coinsorter.validation.NotBlankConstraintValidator;

/**
 * Console provides convenient methods with which to interact with the console.
 */
public class Console {
    private BufferedReader bufferedReader;
    private PrintStream printStream;

    /**
     * Returns an instance of Console that writes to {@link System#out} and reads
     * from {@link System#in}.
     * 
     * @return A new instance of Console connected to {@link System#out} and
     *         {@link System#in}.
     */
    public static Console getDefault() {
        return new Console(new BufferedReader(new InputStreamReader(System.in)), System.out);
    }

    /**
     * Constructs a new instance of Console.
     * 
     * @param bufferedReader The {@link BufferedReader} from which to read input.
     * @param printStream    The {@link PrintStream} to which to write output.
     */
    public Console(final BufferedReader bufferedReader, final PrintStream printStream) {
        this.bufferedReader = bufferedReader;
        this.printStream = printStream;
    }

    /**
     * Reads a line of input.
     * 
     * @return The line of input read.
     * @throws IllegalStateException if there is an error while reading.
     */
    public String readLine() {
        try {
            return bufferedReader.readLine();
        } catch (final IOException e) {
            throw new IllegalStateException("Could not read input", e);
        }
    }

    /**
     * Prompts the user to enter a valid textual value, reporting a validation error
     * information and repeating the prompt until the user enters a valid value.
     * 
     * @param prompt               The prompt to display to the user.
     * @param constraintValidators Validation constraints to apply to the value
     *                             entered by the user.
     * @return The valid value entered by the user.
     */
    public String promptForValidText(final String prompt,
            final Collection<ConstraintValidator<String>> constraintValidators) {
        List<String> constraintViolationMessages = Collections.emptyList();
        String value = null;
        do {
            printStream.println(prompt);
            final String input = readLine();

            constraintViolationMessages = constraintValidators.stream()
                    .filter(constraintValidator -> constraintValidator.isInvalid(input))
                    .map(constraintValidator -> constraintValidator.getConstraintViolationMessage(input))
                    .collect(Collectors.toList());
            if (constraintViolationMessages.isEmpty()) {
                value = input;
            } else {
                println("Invalid input: \"" + input + "\". " + String.join(", ", constraintViolationMessages));
            }
        } while (constraintViolationMessages.isEmpty() == false);

        return value;
    }

    /**
     * Prompts the user to enter a valid integer value, reporting a validation error
     * information and repeating the prompt until the user enters a valid value.
     * 
     * @param prompt               The prompt to display to the user.
     * @param constraintValidators Validation constraints to apply to the value
     *                             entered by the user.
     * @return The valid value entered by the user.
     */
    public int promptForValidInt(final String prompt,
            final Collection<ConstraintValidator<Integer>> constraintValidators) {
        List<String> constraintViolationMessages = Collections.emptyList();
        int value = 0;
        do {
            final String integerString = promptForValidText(prompt,
                    List.of(NotBlankConstraintValidator.INSTANCE, IntegerStringConstraintValidator.INSTANCE));
            final int integer = Integer.parseInt(integerString);

            constraintViolationMessages = constraintValidators.stream()
                    .filter(constraintValidator -> constraintValidator.isInvalid(integer))
                    .map(constraintValidator -> constraintValidator.getConstraintViolationMessage(integer))
                    .collect(Collectors.toList());
            if (constraintViolationMessages.isEmpty()) {
                value = integer;
            } else {
                println("Invalid input: \"" + integer + "\". " + String.join(", ", constraintViolationMessages));
            }
        } while (constraintViolationMessages.isEmpty() == false);

        return value;
    }

    /**
     * Prints a integer.
     * 
     * @param value The integer to print.
     */
    public void print(final int value) {
        printStream.print(value);
    }

    /**
     * Prints a string.
     * 
     * @param value The string to print.
     */
    public void print(final String value) {
        printStream.print(value);
    }

    /**
     * Prints an integer followed by a newline.
     * 
     * @param value The integer to print.
     */
    public void println(final int value) {
        printStream.println(value);
    }

    /**
     * Prints a string followed by a newline.
     * 
     * @param value The string to print.
     */
    public void println(final String value) {
        printStream.println(value);
    }
}
