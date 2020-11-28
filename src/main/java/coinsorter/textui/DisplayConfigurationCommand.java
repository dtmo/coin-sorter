package coinsorter.textui;

import coinsorter.CoinSorter;

/**
 * DisplayConfigurationCommand presents to the user the current coin sorter
 * configuration.
 */
public class DisplayConfigurationCommand implements Command {
    private final CoinSorter coinSorter;
    private final Console console;

    /**
     * Constructs a new instance of DisplayConfigurationCommand.
     * 
     * @param coinSorter The coin sorter from which to display the configuration.
     * @param console    The console with which to display the configuration.
     */
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
