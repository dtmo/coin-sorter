package coinsorter.textui;

import static coinsorter.textui.InputSupport.promptForInt;

import coinsorter.CoinSorter;
import coinsorter.LooseChange;
import coinsorter.validation.MaximumValueConstraintValidator;
import coinsorter.validation.MinimumValueConstraintValidator;

public class CalculateDenominationCommand implements Command {
    private final CoinSorter coinSorter;

    public CalculateDenominationCommand(final CoinSorter coinSorter) {
        this.coinSorter = coinSorter;
    }

    @Override
    public void execute() {
        final int totalValue = promptForInt(
                "Calculate the number of coins of a denomination for a total value\nPlease enter a total value of coins to sort between "
                        + coinSorter.getMinimumValue() + " and " + coinSorter.getMaximumValue() + ": ",
                new MinimumValueConstraintValidator(coinSorter.getMinimumValue()),
                new MaximumValueConstraintValidator(coinSorter.getMaximumValue()));

        final int denominationValue = promptForInt("Please enter the denomination into which to calculate: ",
                new MinimumValueConstraintValidator(0));

        final LooseChange result = coinSorter.convertToDenomination(totalValue, denominationValue);

        final int denominationQuantity = result.getDenominationQuantity(denominationValue);
        System.out.println("A total of " + denominationQuantity + " x " + denominationValue
                + "p coins can be exchanged, with a remainder of " + (totalValue - result.getValue()) + "p");
    }
}
