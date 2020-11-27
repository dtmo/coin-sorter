package coinsorter.textui;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import coinsorter.CoinSorter;

class SetCurrencyCommandTest {
    @Test
    void testExecute() throws Exception {
        final CoinSorter coinSorter = Mockito.spy(new CoinSorter("GBP", 0, 10000, Set.of(200, 100, 50, 20, 10)));
        final Console console = Mockito.spy(Console.getDefault());
        Mockito.doReturn("USD").when(console).promptForString(Mockito.anyString(), ArgumentMatchers.any());

        final SetCurrencyCommand command = new SetCurrencyCommand(coinSorter, console);

        command.execute();

        Mockito.verify(coinSorter, Mockito.times(1)).setCurrency("USD");
    }
}