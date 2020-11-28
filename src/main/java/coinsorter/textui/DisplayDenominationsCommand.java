package coinsorter.textui;

import java.util.Comparator;
import java.util.stream.Collectors;

import coinsorter.CoinSorter;

/**
 * DisplayDenominationsCommand displays the denominations configured in an
 * instance of {@link CoinSorter}.
 */
public class DisplayDenominationsCommand implements Command {
    private final CoinSorter coinSorter;
    private final Console console;

    /**
     * Constructs a new instance of DisplayDenominationsCommand.
     * 
     * @param coinSorter The coin sorter from which to get the denominations to
     *                   display.
     * @param console    The console to which to display the denominations.
     */
    public DisplayDenominationsCommand(final CoinSorter coinSorter, final Console console) {
        this.coinSorter = coinSorter;
        this.console = console;
    }

    @Override
    public void execute() {
        console.println("Denominations in use are: " + coinSorter.getDenominations().stream()
                .sorted(Comparator.reverseOrder()).map(String::valueOf).collect(Collectors.joining(", ")));
    }
}
