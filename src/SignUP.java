import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SignUP extends Stage {

    private Label text;
    private TextField id, name, age, phone, email;
    private PasswordField password;

    private Button login, signUP;
    private HBox buttonHBox;
    private VBox controlsVBox;

    private FlowPane pane;
    private Scene scene;

    SignUP() {

        text = new Label("Login");
        text.setId("text");

        // user id input area
        id = new TextField();
        id.setPromptText("Enter your id");
        id.setId("input");

        // user Name input area
        name = new TextField();
        name.setPromptText("Enter your Name");
        name.setId("input");

        //user password input area
        password = new PasswordField();
        password.setPromptText("Enter your Password");
        password.setId("input");

        // user Age input area
        age = new TextField();
        age.setPromptText("Enter your Age");
        age.setId("input");

        // user Phone input area
        phone = new TextField();
        phone.setPromptText("Enter your Phone");
        phone.setId("input");

        // user Email input area
        email = new TextField();
        email.setPromptText("Enter your Email");
        email.setId("input");


        signUP = new Button("Sign Up");// button for new user sign up
        signUP.setId("Buttons");
        signUP.setOnAction(e -> {

            if (id.getText().isEmpty() || password.getText().isEmpty()
                    || name.getText().isEmpty() || age.getText().isEmpty() ||
                    phone.getText().isEmpty() || email.getText().isEmpty()) {
                Alert dataAlert = new Alert(Alert.AlertType.INFORMATION);
                dataAlert.setTitle("Alert Message");
                dataAlert.setHeaderText(null);
                dataAlert.setContentText("you must to input all data ");
                // Display the alert dialog
                dataAlert.showAndWait();
            } else {


                try {
                    String sql = "SELECT * FROM user WHERE id = ? ";
                    PreparedStatement statement = DataBaseConnection.getConnection().prepareStatement(sql);

                    statement.setInt(1, Integer.parseInt(id.getText())); // Replace with the actual user ID you want to retrieve

                    ResultSet resultSet = statement.executeQuery();



                    if (resultSet.next()) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Alert Message");
                        alert.setHeaderText(null);
                        alert.setContentText("the user is exist");
                        // Display the alert dialog
                        alert.showAndWait();
                    }else {

                        String sql1 = "INSERT INTO user  VALUES (?,?,?,?,?,?)";
                        PreparedStatement statement1 = DataBaseConnection.getConnection().prepareStatement(sql1);


                        statement1.setInt(1, Integer.parseInt(id.getText()));
                        statement1.setString(2, name.getText());
                        statement1.setString(3, password.getText());
                        statement1.setInt(4, Integer.parseInt(age.getText()));
                        statement1.setInt(5, Integer.parseInt(phone.getText()));
                        statement1.setString(6, email.getText());

                        int rowsInserted = statement1.executeUpdate();

                        if (rowsInserted > 0) {
                            this.close();
                            new LoginScreen();

                        }


                        statement.close();

                    }







                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });


        // buttons area
        login = new Button("Login");// button for login
        login.setId("Buttons");
        login.setStyle("-fx-background-color: #EB4B58;");
        login.setOnAction(e -> {
            this.close();
            new LoginScreen();
        });

        buttonHBox = new HBox(30, signUP, login);

        // VBox for all controls
        controlsVBox = new VBox(10, text, id, name, password, age, phone, email, buttonHBox);
        controlsVBox.setAlignment(Pos.CENTER);

        pane = new FlowPane(controlsVBox);
        pane.setId("login");

        scene = new Scene(pane, 300, 350);
        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());


        this.setTitle("Sign Up Screen");
        this.setScene(scene);
        this.show();
    }
}
