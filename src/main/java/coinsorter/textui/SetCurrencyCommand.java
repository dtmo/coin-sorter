package coinsorter.textui;

import java.util.List;

import coinsorter.CoinSorter;
import coinsorter.validation.NotBlankConstraintValidator;

public class SetCurrencyCommand implements Command {
    private final CoinSorter coinSorter;
    private final Console console;

    public SetCurrencyCommand(final CoinSorter coinSorter, final Console console) {
        this.coinSorter = coinSorter;
        this.console = console;
    }

    @Override
    public void execute() {
        final String currency = console.promptForString(
                "Current currency is: " + coinSorter.getCurrency() + "\nPlease enter a new currency: ",
                List.of(NotBlankConstraintValidator.INSTANCE));

        coinSorter.setCurrency(currency);
    }
}
