package coinsorter.textui;

import static coinsorter.textui.InputSupport.promptForString;

import coinsorter.CoinSorter;
import coinsorter.validation.NotBlankConstraintValidator;

public class SetCurrencyCommand implements Command {
    private final CoinSorter coinSorter;

    public SetCurrencyCommand(final CoinSorter coinSorter) {
        this.coinSorter = coinSorter;
    }

    @Override
    public void execute() {
        final String currency = promptForString(
                "Current currency is: " + coinSorter.getCurrency() + "\nPlease enter a new currency: ",
                NotBlankConstraintValidator.INSTANCE);

        coinSorter.setCurrency(currency);
    }
}
