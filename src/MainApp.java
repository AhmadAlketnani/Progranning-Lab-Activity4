import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainApp extends Application {


    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException, SQLException {

        primaryStage = new LoginScreen();
    }

    public static void main(String[] args) {
        launch();
    }

}
