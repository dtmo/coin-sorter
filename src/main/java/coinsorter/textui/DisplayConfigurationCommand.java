package coinsorter.textui;

import coinsorter.CoinSorter;

public class DisplayConfigurationCommand implements Command {
    private final CoinSorter coinSorter;

    public DisplayConfigurationCommand(final CoinSorter coinSorter) {
        this.coinSorter = coinSorter;
    }

    @Override
    public void execute() {
        System.out.println("The current currency is: " + coinSorter.getCurrency());
        System.out.println("The current minimum input is: " + coinSorter.getMinimumValue());
        System.out.println("The current maximum input is: " + coinSorter.getMaximumValue());
    }
}
