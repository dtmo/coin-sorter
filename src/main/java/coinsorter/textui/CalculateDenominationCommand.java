package coinsorter.textui;

import java.util.List;

import coinsorter.CoinSorter;
import coinsorter.LooseChange;
import coinsorter.validation.MaximumValueConstraintValidator;
import coinsorter.validation.MinimumValueConstraintValidator;

/**
 * CalculateDenominationCommand calculates the quantity of a denomination, and a
 * remainder if necessary, to represent a total value.
 */
public class CalculateDenominationCommand implements Command {
        private final CoinSorter coinSorter;
        private final Console console;

        /**
         * Constructs a new instance of CalculateDenominationCommand.
         * 
         * @param coinSorter The coin sorter with which to perform the calculation.
         * @param console    The console with which to interact with the user.
         */
        public CalculateDenominationCommand(final CoinSorter coinSorter, final Console console) {
                this.coinSorter = coinSorter;
                this.console = console;
        }

        @Override
        public void execute() {
                final int totalValue = console.promptForValidInt(
                                "Calculate the number of coins of a denomination for a total value\nPlease enter a total value of coins to sort between "
                                                + coinSorter.getMinimumValue() + " and " + coinSorter.getMaximumValue()
                                                + ": ",
                                List.of(new MinimumValueConstraintValidator(coinSorter.getMinimumValue()),
                                                new MaximumValueConstraintValidator(coinSorter.getMaximumValue())));

                final int denominationValue = console.promptForValidInt(
                                "Please enter the denomination into which to calculate: ",
                                List.of(new MinimumValueConstraintValidator(0)));

                final LooseChange result = coinSorter.convertToDenomination(totalValue, denominationValue);

                final int denominationQuantity = result.getDenominationQuantity(denominationValue);
                console.println("A total of " + denominationQuantity + " x " + denominationValue
                                + "p coins can be exchanged, with a remainder of " + (totalValue - result.getValue())
                                + "p");
        }
}
