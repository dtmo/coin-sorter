package coinsorter.textui;

import java.util.Comparator;
import java.util.stream.Collectors;

import coinsorter.CoinSorter;

public class PrintCoinListCommand implements Command {
    private final CoinSorter coinSorter;

    public PrintCoinListCommand(final CoinSorter coinSorter) {
        this.coinSorter = coinSorter;
    }

    @Override
    public void execute() {
        System.out.println("Denominations in use are: " + coinSorter.getDenominations().stream()
                .sorted(Comparator.reverseOrder()).map(String::valueOf).collect(Collectors.joining(", ")));
    }
}
