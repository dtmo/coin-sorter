package coinsorter.textui;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import coinsorter.CoinSorter;
import coinsorter.LooseChange;
import coinsorter.validation.EnumerationConstraintValidator;
import coinsorter.validation.MaximumValueConstraintValidator;
import coinsorter.validation.MinimumValueConstraintValidator;

/**
 * MakeLooseChangeCommand calculates the quantities of the configured
 * denominations to represent a total value, with an optional remainder if the
 * value may not be represented exactly.
 */
public class MakeLooseChangeCommand implements Command {
        private final CoinSorter coinSorter;
        private final Console console;

        /**
         * Constructs a new instance of MakeLooseChangeCommand.
         * 
         * @param coinSorter The coin sorter with which to perform the calculation.
         * @param console    The consoler with which to interact with the user.
         */
        public MakeLooseChangeCommand(final CoinSorter coinSorter, final Console console) {
                this.coinSorter = coinSorter;
                this.console = console;
        }

        @Override
        public void execute() {
                int totalValue = console.promptForValidInt(
                                "Calculate the correct loose change for a given value.\nPlease enter the total value of loose change to create between "
                                                + coinSorter.getMinimumValue() + " and " + coinSorter.getMaximumValue()
                                                + ": ",
                                List.of(new MinimumValueConstraintValidator(coinSorter.getMinimumValue()),
                                                new MaximumValueConstraintValidator(coinSorter.getMaximumValue())));

                int denominationValue = console.promptForValidInt("Denominations in use are: "
                                + coinSorter.getDenominations().stream().sorted(Comparator.reverseOrder())
                                                .map(String::valueOf).collect(Collectors.joining(", "))
                                + "\nPlease enter a denomination value to exclude: ",
                                List.of(new EnumerationConstraintValidator<>(coinSorter.getDenominations())));

                final LooseChange result = coinSorter.multiCoinCalculator(totalValue, denominationValue);
                console.print("The coins exchanged are: ");
                console.print(coinSorter.getDenominations().stream().sorted(Comparator.reverseOrder())
                                .map(denomination -> result.getDenominationQuantity(denomination) + " x " + denomination
                                                + "p")
                                .collect(Collectors.joining(", ")));
                console.print(" with a remainder of ");
                console.print(totalValue - result.getValue());
                console.println("p");
        }
}
