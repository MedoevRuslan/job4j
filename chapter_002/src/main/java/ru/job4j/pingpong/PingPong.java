package ru.job4j.pingpong;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class PingPong extends Application {
    private static final String JOB4J = "Пинг-понг www.job4j.ru";

    @Override
    public void start(Stage stage) {
        int limitX = 300;
        int limitY = 300;
        Group group = new Group();
        Rectangle rect = new Rectangle(50, 100, 10, 10);
        group.getChildren().add(rect);
        Thread thread = new Thread(new RectangleMove(rect));
        thread.start();
        stage.setScene(new Scene(group, limitX, limitY));
        stage.setOnCloseRequest(
                event -> {
                    thread.interrupt();
                }
);
        stage.setTitle(JOB4J);
        stage.setResizable(false);
        stage.show();
    }
}
