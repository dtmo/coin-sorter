package coinsorter.textui;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import coinsorter.CoinSorter;

class PrintCoinListCommandTest {
    @Test
    void testExecute() throws Exception {
        final CoinSorter coinSorter = Mockito.spy(new CoinSorter("GBP", 0, 10000, Set.of(200, 100, 50, 20, 10)));
        final Console console = Mockito.spy(Console.getDefault());

        final DisplayDenominationsCommand command = new DisplayDenominationsCommand(coinSorter, console);

        command.execute();

        Mockito.verify(coinSorter, Mockito.times(1)).getDenominations();
    }
}
