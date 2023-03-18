import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginScreen extends Stage {
    private ArrayList<User> userArrayList ;
    private Label text;
    private TextField id;
    private PasswordField password;

    private Button login, signUP;

    private HBox buttonHBox;
    private VBox controlsVBox;

    private FlowPane pane;
    private Scene scene;

    LoginScreen( ArrayList<User> userArrayList ) {
        this.userArrayList=userArrayList;
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
            for (User user : userArrayList) {
                if ((user.getId() == Integer.parseInt(id.getText())) && password.getText().equals(user.getPassword())){
                   this.close();
                    new Dashboard(user);
                }

            }
        });

        signUP = new Button("Sign Up");// button for new user sign up
        signUP.setId("Buttons");
        signUP.setStyle("-fx-background-color: #EB4B58;");
        signUP.setOnAction(e->{
            this.close();
            new SignUP(userArrayList);
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
