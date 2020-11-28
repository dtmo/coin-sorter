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

public class Console {
    private BufferedReader bufferedReader;
    private PrintStream printStream;

    public static Console getDefault() {
        return new Console(new BufferedReader(new InputStreamReader(System.in)), System.out);
    }

    public Console(final BufferedReader bufferedReader, final PrintStream printStream) {
        this.bufferedReader = bufferedReader;
        this.printStream = printStream;
    }

    public String readLine() {
        try {
            return bufferedReader.readLine();
        } catch (final IOException e) {
            throw new IllegalStateException("Could not read input", e);
        }
    }

    public String promptForString(final String prompt,
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

    public int promptForInt(final String prompt, final Collection<ConstraintValidator<Integer>> constraintValidators) {
        List<String> constraintViolationMessages = Collections.emptyList();
        int value = 0;
        do {
            final String integerString = promptForString(prompt,
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

    public void print(int i) {
        printStream.print(i);
    }

    public void print(String s) {
        printStream.print(s);
    }

    public void println(int i) {
        printStream.println(i);
    }

    public void println(String x) {
        printStream.println(x);
    }

    public PrintStream format(String format, Object... args) {
        return printStream.format(format, args);
    }
}
