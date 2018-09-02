package ru.job4j.servlets;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */
public class ActionDispatch {
    private static ActionDispatch instance = new ActionDispatch();

    private ActionDispatch() { }

    public static ActionDispatch  getInstance() {
        return instance;
    }

    private final ConcurrentHashMap<String, Function<HttpServletRequest, Boolean>> dispatcher = new ConcurrentHashMap<>();
    private final ValidateService validate = ValidateService.getInstance();

    private Function<HttpServletRequest, Boolean> add() {
        return req -> {
            String name = req.getParameter("name");
            String login = req.getParameter("login");
            String email = req.getParameter("email");
            User user = new User(name, email, login);
            return this.validate.add(user);
        };
    }

    private Function<HttpServletRequest, Boolean> delete() {
        return  req -> {
            int id = Integer.valueOf(req.getParameter("id"));
            User user = this.validate.findById(id);
            return this.validate.delete(user);
        };
    }

    private Function<HttpServletRequest, Boolean> update() {
        return req -> {
            int id = Integer.valueOf(req.getParameter("id"));
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String login = req.getParameter("login");
            User user = this.validate.findById(id);
            return this.validate.update(user, name, email, login);
        };
    }

    private void load(String action, Function<HttpServletRequest, Boolean> handler) {
        this.dispatcher.put(action, handler);
    }

    public void init() {
        this.load("add", this.add());
        this.load("delete", this.delete());
        this.load("update", this.update());
    }

    public Boolean execute(HttpServletRequest req) {
        return this.dispatcher.get(req.getParameter("action")).apply(req);
    }
}