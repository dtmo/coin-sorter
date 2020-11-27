package coinsorter.textui;

import coinsorter.CoinSorter;
import coinsorter.validation.MinimumValueConstraintValidator;

public class SetMaximumValueCommand implements Command {
    private final CoinSorter coinSorter;
    private final Console console;

    public SetMaximumValueCommand(final CoinSorter coinSorter, final Console console) {
        this.coinSorter = coinSorter;
        this.console = console;
    }

    @Override
    public void execute() {
        console.println("Current maximum coin input is: " + coinSorter.getMaximumValue());

        final int maximumValue = console.promptForInt("Please enter a new maximum coin input: ",
                new MinimumValueConstraintValidator(coinSorter.getMinimumValue()));

        coinSorter.setMaximumValue(maximumValue);
    }
}
