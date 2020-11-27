package coinsorter.textui;

import coinsorter.CoinSorter;

public class DisplayConfigurationCommand implements Command {
    private final CoinSorter coinSorter;
    private final Console console;

    public DisplayConfigurationCommand(final CoinSorter coinSorter, final Console console) {
        this.coinSorter = coinSorter;
        this.console = console;
    }

    @Override
    public void execute() {
        console.println("The current currency is: " + coinSorter.getCurrency());
        console.println("The current minimum input is: " + coinSorter.getMinimumValue());
        console.println("The current maximum input is: " + coinSorter.getMaximumValue());
    }
}
