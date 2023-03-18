import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Dashboard extends Stage {
    private User user;
    private Label id, idValue , name ,nameValue,password , passwordValue ,age , ageValue,phone, phoneValue ,email, emailValue;
    private VBox controls, controlsValue , all;
    private HBox box;

    private Button close;
    private FlowPane pane;
    private Scene scene;


    Dashboard(User user ){
        this.user = user;

        id= new Label("ID: ");
        id.setId("labels");

        name= new Label("Name: ");
        name.setId("labels");

        password= new Label("Password: ");
        password.setId("labels");

        age= new Label("Age: ");
        age.setId("labels");

        phone= new Label("Phone: ");
        phone.setId("labels");

        email= new Label("Email: ");
        email.setId("labels");

        controls=new VBox(5, id, name,password,age, phone,email);

        idValue= new Label(String.valueOf(this.user.getId()));
        idValue.setId("labelsValue");

        nameValue= new Label(this.user.getName());
        nameValue.setId("labelsValue");

        passwordValue= new Label(this.user.getPassword());
        passwordValue.setId("labelsValue");

        ageValue= new Label(String.valueOf(this.user.getAge()));
        ageValue.setId("labelsValue");

        phoneValue= new Label(String.valueOf(this.user.getPhone()));
        phoneValue.setId("labelsValue");

        emailValue= new Label(this.user.getEmail());
        emailValue.setId("labelsValue");

        controlsValue=new VBox(5, idValue, nameValue,passwordValue,ageValue, phoneValue,emailValue);


        box=new HBox(1,controls,controlsValue);

        close=new Button("Close");
        close.setId("Buttons");
        close.setOnAction(e->this.close());

        all =new VBox(5, box, close);
        all.setAlignment(Pos.CENTER);

        pane=new FlowPane(all);
        pane.setId("login");

        scene=new Scene(pane,600,300);
        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());

        this.setTitle("Dashboard Screen");
        this.setScene(scene);
        this.show();



    }
}
