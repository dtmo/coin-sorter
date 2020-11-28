package coinsorter;

import coinsorter.textui.CoinSorterUi;

/**
 * App provides the main execution entry point.
 */
public class App {
    /**
     * The main application entry point.
     * 
     * @param args An array of arguments provided from the command line.
     * @throws Exception if there is an uncaught exception during the application
     *                   execution.
     */
    public static void main(final String[] args) throws Exception {
        final CoinSorterUi coinSorterUi = new CoinSorterUi();
        coinSorterUi.show();
    }
}
