package coinsorter.textui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import coinsorter.validation.EnumerationConstraintValidator;

public class Menu {
    private final String header;
    private final SortedMap<String, MenuItem> menuItems;

    public Menu(final String header, final SortedMap<String, MenuItem> menuItems) {
        this.header = header;
        this.menuItems = menuItems;
    }

    public String getHeader() {
        return header;
    }

    public SortedMap<String, MenuItem> getMenuItems() {
        return Collections.unmodifiableSortedMap(menuItems);
    }

    public MenuItem select(final Console console) {
        MenuItem menuItem;
        do {
            final int optionsLength = String.valueOf(menuItems.size()).length();
            final String menuBody = menuItems.entrySet().stream().map(entry -> String
                    .format("%" + optionsLength + "s - %s", entry.getKey(), entry.getValue().getText()))
                    .collect(Collectors.joining("\n"));

            final String menuSelection = console.promptForString(String.format("\n%s\n\n%s", header, menuBody),
                    Set.of(new EnumerationConstraintValidator<String>(menuItems.keySet()))).trim();
            menuItem = menuItems.get(menuSelection);
        } while (menuItem == null);
        return menuItem;
    }

    public static abstract class MenuItem {
        private final String text;

        public MenuItem(final String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

    public static class CommandItem extends MenuItem {
        private final Command command;

        public CommandItem(final String text, final Command command) {
            super(text);
            this.command = command;
        }

        public Command getCommand() {
            return command;
        }
    }

    public static class SubMenuItem extends MenuItem {
        private final Menu menu;

        public SubMenuItem(final String label, final Menu menu) {
            super(label);
            this.menu = menu;
        }

        public Menu getMenu() {
            return menu;
        }
    }

    public static class ExitMenuItem extends MenuItem {
        public ExitMenuItem(final String text) {
            super(text);
        }
    }

    public static class Builder {
        private String header;
        private final List<MenuItem> menuItems = new ArrayList<>();
        private String exitText;

        public Builder withHeader(final String header) {
            this.header = header;
            return this;
        }

        public Builder withCommand(final String text, final Command command) {
            this.menuItems.add(new CommandItem(text, command));
            return this;
        }

        public Builder withSubMenu(final String text, final Menu menu) {
            this.menuItems.add(new SubMenuItem(text, menu));
            return this;
        }

        public Builder withExitText(final String text) {
            this.exitText = text;
            return this;
        }

        public Menu build() {
            final SortedMap<String, MenuItem> menuItemMap = new TreeMap<>();
            menuItems.forEach(menuItem -> menuItemMap.put(String.valueOf(menuItemMap.size() + 1), menuItem));
            menuItemMap.put(String.valueOf(menuItemMap.size() + 1), new ExitMenuItem(exitText));
            return new Menu(header, menuItemMap);
        }
    }
}
