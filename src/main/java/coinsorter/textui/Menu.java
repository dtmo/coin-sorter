package coinsorter.textui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Menu {
    private final String header;
    private final SortedMap<String, MenuItem> menuItems;
    private final PrintStream printStream = System.out;

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

    public MenuItem select() {
        MenuItem menuItem;
        do {
            printStream.print("\n");
            printStream.print(header);
            printStream.print("\n\n");

            final int optionsLength = String.valueOf(menuItems.size()).length();
            for (final Map.Entry<String, MenuItem> entry : menuItems.entrySet()) {
                printStream.format("%" + optionsLength + "s - %s\n", entry.getKey(), entry.getValue().getText());
            }

            final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                final String input = reader.readLine();
                menuItem = menuItems.get(input.trim());
            } catch (final IOException e) {
                throw new IllegalStateException("Could not read from System.in", e);
            }
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
