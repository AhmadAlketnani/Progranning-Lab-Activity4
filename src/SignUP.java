import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SignUP extends Stage {
    private ArrayList<User> userArrayList ;
    private Label text;
    private TextField id, name, age, phone, email;
    private PasswordField password;

    private Button login, signUP;
    private HBox buttonHBox;
    private VBox controlsVBox;

    private FlowPane pane;
    private Scene scene;

    SignUP(ArrayList<User> userArrayList ) {
        this.userArrayList=userArrayList;
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
        signUP.setOnAction(e->{
            User user = new User(Integer.parseInt(id.getText()),name.getText(),password.getText(),Integer.parseInt(age.getText()),Integer.parseInt(phone.getText()),email.getText());
            userArrayList.add(user);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream("./src/Users.txt");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(userArrayList);
                objectOutputStream.close();



            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println("test "+ex.getMessage());
            }
            this.close();
            new LoginScreen(userArrayList);
        });


        // buttons area
        login = new Button("Login");// button for login
        login.setId("Buttons");
        login.setStyle("-fx-background-color: #EB4B58;");
        login.setOnAction(e->{
            this.close();
            new LoginScreen(userArrayList);
        });

        buttonHBox = new HBox(30, signUP, login);

        // VBox for all controls
        controlsVBox = new VBox(10, text,id, name,password,age, phone, email, buttonHBox);
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
