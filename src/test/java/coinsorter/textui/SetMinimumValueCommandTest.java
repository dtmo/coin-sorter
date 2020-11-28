package coinsorter.textui;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import coinsorter.CoinSorter;

class SetMinimumValueCommandTest {
    @Test
    void testExecute() throws Exception {
        final CoinSorter coinSorter = Mockito.spy(new CoinSorter("GBP", 0, 10000, Set.of(200, 100, 50, 20, 10)));
        final Console console = Mockito.spy(Console.getDefault());

        Mockito.doReturn(5000).when(console).promptForInt(Mockito.anyString(), Mockito.any());

        final SetMinimumValueCommand command = new SetMinimumValueCommand(coinSorter, console);
        
        command.execute();

        Mockito.verify(coinSorter, Mockito.times(1)).setMinimumValue(Mockito.eq(5000));
    }
}
