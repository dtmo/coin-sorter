package coinsorter.textui;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;

import coinsorter.CoinSorter;
import coinsorter.textui.Menu.CommandItem;
import coinsorter.textui.Menu.ExitMenuItem;
import coinsorter.textui.Menu.MenuItem;
import coinsorter.textui.Menu.SubMenuItem;

public class CoinSorterUi {
    private final Console console;
    private final Deque<Menu> menuStack = new ArrayDeque<>();

    public CoinSorterUi() {
        final CoinSorter coinSorter = new CoinSorter("GBP", 0, 10000, Set.of(200, 100, 50, 20, 10));
        console = Console.getDefault();

        final Menu menu = new Menu.Builder().withHeader("***Coin Sorter - Main Menu***")
                .withCommand("Coin calculator", new CalculateDenominationCommand(coinSorter, console))
                .withCommand("Multiple coin calculator", new MakeLooseChangeCommand(coinSorter, console))
                .withCommand("Print coin list", new PrintCoinListCommand(coinSorter, console))
                .withSubMenu("Set details", new Menu.Builder().withHeader("***Set Details Sub-Menu***")
                        .withCommand("Set currency", new SetCurrencyCommand(coinSorter, console))
                        .withCommand("Set minimum coin input value", new SetMinimumValueCommand(coinSorter, console))
                        .withCommand("Set maximum coin input value", new SetMaximumValueCommand(coinSorter, console))
                        .withExitText("Return to main menu").build())
                .withCommand("Display program configurations", new DisplayConfigurationCommand(coinSorter, console))
                .withExitText("Quit the program").build();

        menuStack.push(menu);
    }

    public void show() {
        while (menuStack.isEmpty() == false) {
            final Menu currentMenu = menuStack.peek();

            final MenuItem selection = currentMenu.selectMenuItem(console);

            if (selection instanceof SubMenuItem) {
                final SubMenuItem subMenuItem = (SubMenuItem) selection;
                menuStack.push(subMenuItem.getMenu());
            } else if (selection instanceof ExitMenuItem) {
                menuStack.pop();
            } else if (selection instanceof CommandItem) {
                final CommandItem commandItem = (CommandItem) selection;
                commandItem.getCommand().execute();
            }
        }
    }
}
