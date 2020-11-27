package coinsorter.textui;

import static coinsorter.textui.InputSupport.promptForInt;

import java.util.Comparator;
import java.util.stream.Collectors;

import coinsorter.CoinSorter;
import coinsorter.LooseChange;
import coinsorter.validation.EnumerationConstraintValidator;
import coinsorter.validation.MaximumValueConstraintValidator;
import coinsorter.validation.MinimumValueConstraintValidator;

public class MakeLooseChangeCommand implements Command {
    private final CoinSorter coinSorter;

    public MakeLooseChangeCommand(final CoinSorter coinSorter) {
        this.coinSorter = coinSorter;
    }

    @Override
    public void execute() {
        int totalValue = promptForInt(
                "Calculate the correct loose change for a given value.\nPlease enter the total value of loose change to create between "
                        + coinSorter.getMinimumValue() + " and " + coinSorter.getMaximumValue() + ": ",
                new MinimumValueConstraintValidator(coinSorter.getMinimumValue()),
                new MaximumValueConstraintValidator(coinSorter.getMaximumValue()));

        int denominationValue = promptForInt(
                "Denominations in use are: " + coinSorter.getDenominations().stream().sorted(Comparator.reverseOrder())
                        .map(String::valueOf).collect(Collectors.joining(", "))
                        + "\nPlease enter a denomination value to exclude: ",
                new EnumerationConstraintValidator<>(coinSorter.getDenominations()));

        final LooseChange result = coinSorter.convertToDenominations(totalValue, denominationValue);
        System.out.print("The coins exchanged are: ");
        System.out.print(coinSorter.getDenominations().stream().sorted(Comparator.reverseOrder())
                .map(denomination -> result.getDenominationQuantity(denomination) + " x " + denomination + "p")
                .collect(Collectors.joining(", ")));
        System.out.print(" with a remainder of ");
        System.out.print(totalValue - result.getValue());
        System.out.println("p");
    }
}
