import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class MainApp extends Application {


    @Override
    public void start(Stage primaryStage) {
        ArrayList<User> userArrayList = new ArrayList<>();
        try {
            FileInputStream inputStream = new FileInputStream("./src/Users.txt");
            ObjectInputStream stream = new ObjectInputStream(inputStream);

            userArrayList = (ArrayList<User>) stream.readObject();


            inputStream.close();
            stream.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        primaryStage = new LoginScreen(userArrayList);
    }

    public static void main(String[] args) {
        launch();
    }

}
