package coinsorter.textui;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import coinsorter.CoinSorter;
import coinsorter.LooseChange;
import coinsorter.validation.EnumerationConstraintValidator;
import coinsorter.validation.MaximumValueConstraintValidator;
import coinsorter.validation.MinimumValueConstraintValidator;

public class MakeLooseChangeCommand implements Command {
        private final CoinSorter coinSorter;
        private final Console console;

        public MakeLooseChangeCommand(final CoinSorter coinSorter, final Console console) {
                this.coinSorter = coinSorter;
                this.console = console;
        }

        @Override
        public void execute() {
                int totalValue = console.promptForInt(
                                "Calculate the correct loose change for a given value.\nPlease enter the total value of loose change to create between "
                                                + coinSorter.getMinimumValue() + " and " + coinSorter.getMaximumValue()
                                                + ": ",
                                List.of(new MinimumValueConstraintValidator(coinSorter.getMinimumValue()),
                                                new MaximumValueConstraintValidator(coinSorter.getMaximumValue())));

                int denominationValue = console.promptForInt("Denominations in use are: "
                                + coinSorter.getDenominations().stream().sorted(Comparator.reverseOrder())
                                                .map(String::valueOf).collect(Collectors.joining(", "))
                                + "\nPlease enter a denomination value to exclude: ",
                                List.of(new EnumerationConstraintValidator<>(coinSorter.getDenominations())));

                final LooseChange result = coinSorter.convertToDenominations(totalValue, denominationValue);
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
