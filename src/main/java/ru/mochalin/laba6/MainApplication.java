package ru.mochalin.laba6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.mochalin.laba6.utils.DBHelper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static javafx.application.Application.launch;

public class MainApplication extends Application {
    public static Stage getMainStage() {
        return mainStage;
    }

    private static Stage mainStage;

    public static Connection getConnection() {
        return connection;
    }

    private static Connection connection;

    @Override
    public void stop() throws Exception {
        if(connection != null){
            connection.close();
        }
        super.stop();
    }

    @Override
    public void start(Stage stage) throws IOException {
        try {
            connection = DBHelper.getConnection();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            //TODO: Вывести Alert
        }
        this.mainStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/ru/mochalin/laba6/fxmls/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
