package coinsorter.textui;

/**
 * Command represents an executable unit of business logic.
 */
public interface Command {
    /**
     * Performs the actions represented by the command.
     */
    public void execute();
}
