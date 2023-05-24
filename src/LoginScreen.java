import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginScreen extends Stage {

    private Label text;
    private TextField id;
    private PasswordField password;

    private Button login, signUP;

    private HBox buttonHBox;
    private VBox controlsVBox;

    private FlowPane pane;
    private Scene scene;

    LoginScreen(  ) {

        text=new Label("Login");
        text.setId("text");

        // user id input area
        id = new TextField();
        id.setPromptText("Enter your id");
        id.setId("input");

        //user password input area
        password = new PasswordField();
        password.setPromptText("Enter your Password");
        password.setId("input");


        // buttons area
        login = new Button("Login");// button for login
        login.setId("Buttons");
        login.setOnAction(e->{
            if (id.getText().isEmpty() || password.getText().isEmpty()){
                Alert idPasswordAlert = new Alert(Alert.AlertType.INFORMATION);
                idPasswordAlert.setTitle("Alert Message");
                idPasswordAlert.setHeaderText(null);
                idPasswordAlert.setContentText("the id and password are required");
                // Display the alert dialog
                idPasswordAlert.showAndWait();
            }else {

            try {
                String sql = "SELECT * FROM user WHERE id = ? AND password = ?";
                PreparedStatement statement = DataBaseConnection.getConnection().prepareStatement(sql);

                statement.setInt(1, Integer.parseInt(id.getText())); // Replace with the actual user ID you want to retrieve
                statement.setString(2, password.getText()); // Replace with the actual user password you want to retrieve

                ResultSet resultSet = statement.executeQuery();



                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String password = resultSet.getString("password");
                    int age = resultSet.getInt("age");
                    int phone = resultSet.getInt("phone");
                    String email = resultSet.getString("email");
                    User user = new User(id , name, password,age, phone, email);



                    this.close();
                    new Dashboard(user);

                    resultSet.close();
                    statement.close();
                }else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert Message");
                    alert.setHeaderText(null);
                    alert.setContentText("the user data is incorct  or the user doesn't exist");

                    // Display the alert dialog
                    alert.showAndWait();
                }


            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }}
        });

        signUP = new Button("Sign Up");// button for new user sign up
        signUP.setId("Buttons");
        signUP.setStyle("-fx-background-color: #EB4B58;");
        signUP.setOnAction(e->{
            this.close();
            new SignUP();
        });



        buttonHBox = new HBox(30,login,signUP);

        // VBox for all controls
        controlsVBox = new VBox(10,text, id,password,buttonHBox);
        controlsVBox.setAlignment(Pos.CENTER);

        pane= new FlowPane(controlsVBox);
        pane.setId("login");

        scene = new Scene(pane,300,200);
        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());



        this.setTitle("Login Screen");
        this.setScene(scene);
        this.show();
    }


}
