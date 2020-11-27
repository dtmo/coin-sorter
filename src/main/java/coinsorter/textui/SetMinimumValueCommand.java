package coinsorter.textui;

import static coinsorter.textui.InputSupport.promptForInt;

import coinsorter.CoinSorter;
import coinsorter.validation.MaximumValueConstraintValidator;
import coinsorter.validation.MinimumValueConstraintValidator;

public class SetMinimumValueCommand implements Command {
    private final CoinSorter coinSorter;

    public SetMinimumValueCommand(final CoinSorter coinSorter) {
        this.coinSorter = coinSorter;
    }

    @Override
    public void execute() {
        System.out.println("Current minimum coin input is: " + coinSorter.getMinimumValue());

        final int minimumValue = promptForInt("Please enter a new minimum coin input: ",
                new MinimumValueConstraintValidator(0), new MaximumValueConstraintValidator(coinSorter.getMaximumValue()));

        coinSorter.setMinimumValue(minimumValue);
    }
}
