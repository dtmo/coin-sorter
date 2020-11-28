package coinsorter.textui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import coinsorter.validation.EnumerationConstraintValidator;

/**
 * Menu represents a set of options from which the user may make a selection.
 */
public class Menu {
    private final String headerText;
    private final SortedMap<String, MenuItem> menuItems;

    /**
     * Constructs a new instance of Menu.
     * 
     * @param headerText The text presented before the menu.
     * @param menuItems  The menu items indexed by their identifiers.
     */
    public Menu(final String headerText, final SortedMap<String, MenuItem> menuItems) {
        this.headerText = headerText;
        this.menuItems = menuItems;
    }

    /**
     * Returns the text presented before the menu.
     * 
     * @return The text presented before the menu.
     */
    public String getHeader() {
        return headerText;
    }

    /**
     * Returns the menu items indexed by their identifiers.
     * 
     * @return The menu items indexed by their identifiers.
     */
    public SortedMap<String, MenuItem> getMenuItems() {
        return Collections.unmodifiableSortedMap(menuItems);
    }

    /**
     * Displays the menu and prompts the user to select a menu item. The menu will
     * continue being redisplayed until the user provides a valid menu selection.
     * 
     * @param console The console with which to interact with the user.
     * @return The selected menu item.
     */
    public MenuItem selectMenuItem(final Console console) {
        final int optionsLength = String.valueOf(menuItems.size()).length();
        final String menuBody = menuItems.entrySet().stream()
                .map(entry -> String.format("%" + optionsLength + "s - %s", entry.getKey(), entry.getValue().getText()))
                .collect(Collectors.joining("\n"));

        final String menuSelection = console.promptForValidText(String.format("\n%s\n\n%s", headerText, menuBody),
                Set.of(new EnumerationConstraintValidator<String>(menuItems.keySet()))).trim();
        return menuItems.get(menuSelection);
    }

    /**
     * MenuItem is an abstract item in a {@link Menu}.
     */
    public static abstract class MenuItem {
        private final String text;

        /**
         * Constructs a new instance of MenuItem.
         * 
         * @param text The textual representation of the item in the menu.
         */
        public MenuItem(final String text) {
            this.text = text;
        }

        /**
         * Returns the textual representation of the item in the menu.
         * 
         * @return The textual representation of the item in the menu.
         */
        public String getText() {
            return text;
        }
    }

    /**
     * CommandItem is a menu item that represents a {@link Command}.
     */
    public static class CommandItem extends MenuItem {
        private final Command command;

        /**
         * Constructs a new instance of CommandItem.
         * 
         * @param text    The textual representation of the item in the menu.
         * @param command The command represented by the menu item.
         */
        public CommandItem(final String text, final Command command) {
            super(text);
            this.command = command;
        }

        /**
         * Returns the command represented by the menu item.
         * 
         * @return The command represented by the menu item.
         */
        public Command getCommand() {
            return command;
        }
    }

    /**
     * SubMenuItem represents a sub menu.
     */
    public static class SubMenuItem extends MenuItem {
        private final Menu menu;

        /**
         * Constructs a new instance of SubMenuItem.
         * 
         * @param text The textual representation of the item in the menu.
         * @param menu The menu represented by the menu item.
         */
        public SubMenuItem(final String text, final Menu menu) {
            super(text);
            this.menu = menu;
        }

        /**
         * Returns the menu represented by the menu item.
         * 
         * @return The menu represented by the menu item.
         */
        public Menu getMenu() {
            return menu;
        }
    }

    /**
     * ExitMenuItem represents the item, which should always be at the end of a
     * menu, that will cause the menu to be exited without performing an action.
     */
    public static class ExitMenuItem extends MenuItem {
        /**
         * Constructs a new instance of ExitMenuItem.
         * 
         * @param text The textual representation of the item in the menu.
         */
        public ExitMenuItem(final String text) {
            super(text);
        }
    }

    /**
     * Builder builds instances of {@link Menu}.
     */
    public static class Builder {
        private String headerText;
        private final List<MenuItem> menuItems = new ArrayList<>();
        private String exitText;

        /**
         * Sets the text presented before the menu.
         * 
         * @param headerText The header text to set.
         * @return This builder.
         */
        public Builder withHeaderText(final String headerText) {
            this.headerText = headerText;
            return this;
        }

        /**
         * Adds a command item to the menu.
         * 
         * @param text    The textual with which to represent the item in the menu.
         * @param command The command to represent.
         * @return This builder
         */
        public Builder withCommand(final String text, final Command command) {
            this.menuItems.add(new CommandItem(text, command));
            return this;
        }

        /**
         * Adds a sub menu to the menu.
         * 
         * @param text The textual with which to represent the item in the menu.
         * @param menu The sub menu to add to the menu.
         * @return This builder.
         */
        public Builder withSubMenu(final String text, final Menu menu) {
            this.menuItems.add(new SubMenuItem(text, menu));
            return this;
        }

        /**
         * Sets the text with which to represent the option to exit the menu.
         * 
         * @param text The text with which to represent the option to exit the menu.
         * @return This builder.
         */
        public Builder withExitText(final String text) {
            this.exitText = text;
            return this;
        }

        /**
         * Builds a new instance of {@link Menu}.
         * 
         * @return A new instance of {@link Menu}.
         */
        public Menu build() {
            final SortedMap<String, MenuItem> menuItemMap = new TreeMap<>();
            menuItems.forEach(menuItem -> menuItemMap.put(String.valueOf(menuItemMap.size() + 1), menuItem));
            menuItemMap.put(String.valueOf(menuItemMap.size() + 1), new ExitMenuItem(exitText));
            return new Menu(headerText, menuItemMap);
        }
    }
}
