package ru.job4j.tracker;

import java.io.File;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class StartUI {
    private Input input;
    private Tracker tracker;

    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Метод инициализации интерфейса взаимодейтвия с пользователем.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        int key;
        do {
            menu.showMenu();
            key = input.ask("Select : ", menu.actions);
            menu.select(key);
        } while (key != 6);
    }

    public static void main(String[] args) throws Exception {
        File file = new File(args[0]);
        try (Tracker tracker = new Tracker(file)) {
            new StartUI(new ValidateInput(new ConsoleInput()), tracker).init();
        }
    }
}
