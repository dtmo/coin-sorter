package coinsorter.textui;

import java.util.List;

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

        final int maximumValue = console.promptForValidInt("Please enter a new maximum coin input: ",
                List.of(new MinimumValueConstraintValidator(coinSorter.getMinimumValue())));

        coinSorter.setMaximumValue(maximumValue);
    }
}
