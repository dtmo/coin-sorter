package coinsorter.textui;

import static coinsorter.textui.InputSupport.promptForInt;

import coinsorter.CoinSorter;
import coinsorter.validation.MinimumValueConstraintValidator;

public class SetMaximumValueCommand implements Command {
    private final CoinSorter coinSorter;

    public SetMaximumValueCommand(final CoinSorter coinSorter) {
        this.coinSorter = coinSorter;
    }

    @Override
    public void execute() {
        System.out.println("Current maximum coin input is: " + coinSorter.getMaximumValue());

        final int maximumValue = promptForInt("Please enter a new maximum coin input: ",
                new MinimumValueConstraintValidator(coinSorter.getMinimumValue()));

        coinSorter.setMaximumValue(maximumValue);
    }
}
