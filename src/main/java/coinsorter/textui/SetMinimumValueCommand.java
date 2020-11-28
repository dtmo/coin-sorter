package coinsorter.textui;

import java.util.List;

import coinsorter.CoinSorter;
import coinsorter.validation.MaximumValueConstraintValidator;
import coinsorter.validation.MinimumValueConstraintValidator;

public class SetMinimumValueCommand implements Command {
    private final CoinSorter coinSorter;
    private final Console console;

    public SetMinimumValueCommand(final CoinSorter coinSorter, final Console console) {
        this.coinSorter = coinSorter;
        this.console = console;
    }

    @Override
    public void execute() {
        System.out.println("Current minimum coin input is: " + coinSorter.getMinimumValue());

        final int minimumValue = console.promptForInt("Please enter a new minimum coin input: ",
                List.of(new MinimumValueConstraintValidator(0),
                        new MaximumValueConstraintValidator(coinSorter.getMaximumValue())));

        coinSorter.setMinimumValue(minimumValue);
    }
}
