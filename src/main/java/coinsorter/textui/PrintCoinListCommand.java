package coinsorter.textui;

import java.util.Comparator;
import java.util.stream.Collectors;

import coinsorter.CoinSorter;

public class PrintCoinListCommand implements Command {
    private final CoinSorter coinSorter;
    private final Console console;

    public PrintCoinListCommand(final CoinSorter coinSorter, final Console console) {
        this.coinSorter = coinSorter;
        this.console = console;
    }

    @Override
    public void execute() {
        console.println("Denominations in use are: " + coinSorter.getDenominations().stream()
                .sorted(Comparator.reverseOrder()).map(String::valueOf).collect(Collectors.joining(", ")));
    }
}
