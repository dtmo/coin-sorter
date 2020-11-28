package coinsorter.textui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import coinsorter.validation.MaximumValueConstraintValidator;
import coinsorter.validation.NotBlankConstraintValidator;

class ConsoleTest {
    @Test
    void testReadLineWithIOException() throws Exception {
        final BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
        final PrintStream printStream = Mockito.mock(PrintStream.class);
        final Console console = new Console(bufferedReader, printStream);

        Mockito.doThrow(IOException.class).when(bufferedReader).readLine();

        assertThrows(IllegalStateException.class, () -> console.readLine());
    }

    @Test
    void testPromptForStringWithValidInput() throws Exception {
        final BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
        final PrintStream printStream = Mockito.mock(PrintStream.class);
        final Console console = new Console(bufferedReader, printStream);

        Mockito.doReturn("input").when(bufferedReader).readLine();

        final String value = console.promptForValidText("prompt", Collections.emptySet());

        Mockito.verify(bufferedReader, Mockito.times(1)).readLine();

        assertEquals("input", value);
    }

    @Test
    void testPromptForStringWithInitialInvalidInput() throws Exception {
        final BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
        final PrintStream printStream = Mockito.mock(PrintStream.class);
        final Console console = new Console(bufferedReader, printStream);

        Mockito.doReturn("").doReturn("valid").when(bufferedReader).readLine();

        final String value = console.promptForValidText("prompt", Set.of(NotBlankConstraintValidator.INSTANCE));

        Mockito.verify(bufferedReader, Mockito.times(2)).readLine();

        assertEquals("valid", value);
    }

    @Test
    void testPromptForIntWithValidInput() throws Exception {
        final BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
        final PrintStream printStream = Mockito.mock(PrintStream.class);
        final Console console = new Console(bufferedReader, printStream);

        Mockito.doReturn("1234").when(bufferedReader).readLine();

        final int value = console.promptForValidInt("prompt", Collections.emptySet());

        Mockito.verify(bufferedReader, Mockito.times(1)).readLine();

        assertEquals(1234, value);
    }

    @Test
    void testPromptForIntWithInitialNonNumericInput() throws Exception {
        final BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
        final PrintStream printStream = Mockito.mock(PrintStream.class);
        final Console console = new Console(bufferedReader, printStream);

        Mockito.doReturn("abcd").doReturn("1234").when(bufferedReader).readLine();

        final int value = console.promptForValidInt("prompt", Set.of());

        Mockito.verify(bufferedReader, Mockito.times(2)).readLine();

        assertEquals(1234, value);
    }

    @Test
    void testPromptForIntWithInitialInvalidInput() throws Exception {
        final BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
        final PrintStream printStream = Mockito.mock(PrintStream.class);
        final Console console = new Console(bufferedReader, printStream);

        Mockito.doReturn("200").doReturn("50").when(bufferedReader).readLine();

        final int value = console.promptForValidInt("prompt", Set.of(new MaximumValueConstraintValidator(100)));

        Mockito.verify(bufferedReader, Mockito.times(2)).readLine();

        assertEquals(50, value);
    }

    @Test
    void testPrintInt() throws Exception {
        final BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
        final PrintStream printStream = Mockito.mock(PrintStream.class);
        final Console console = new Console(bufferedReader, printStream);

        console.print(14);

        Mockito.verify(printStream, Mockito.times(1)).print(Mockito.eq(14));
    }

    @Test
    void testPrintString() throws Exception {
        final BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
        final PrintStream printStream = Mockito.mock(PrintStream.class);
        final Console console = new Console(bufferedReader, printStream);

        console.print("value");

        Mockito.verify(printStream, Mockito.times(1)).print(Mockito.eq("value"));
    }

    @Test
    void testPrintlnInt() throws Exception {
        final BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
        final PrintStream printStream = Mockito.mock(PrintStream.class);
        final Console console = new Console(bufferedReader, printStream);

        console.println(14);

        Mockito.verify(printStream, Mockito.times(1)).println(Mockito.eq(14));
    }

    @Test
    void testPrintlnString() throws Exception {
        final BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
        final PrintStream printStream = Mockito.mock(PrintStream.class);
        final Console console = new Console(bufferedReader, printStream);

        console.println("value");

        Mockito.verify(printStream, Mockito.times(1)).println(Mockito.eq("value"));
    }
}
