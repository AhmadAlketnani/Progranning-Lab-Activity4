import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.io.File;
import java.util.Scanner;

public class Events implements EventHandler<ActionEvent> {
    User user;
    String id, password;
    File file;

    Events(String id, String password) {
        this.id = id;
        this.password = password;
    }

    Events(User user) {
        this.user = user;
    }

    @Override
    public void handle(ActionEvent event) {
        if (((Button) event.getSource()).getText().equals("Login")) {
//            try {
//                file = new File(getClass().getResource("User.txt").getFile());
//                Scanner readerFile = new Scanner(file);
//                while (readerFile.hasNextLine()) {
//                    String[] userdata = readerFile.nextLine().split(",");
//                    if (id.equals(userdata[0]) && password.equals(userdata[2])) {
//                        user = new User(userdata[0], userdata[1], userdata[2], userdata[3], userdata[4], userdata[5]);
//                        new Dashboard(user);
//                        readerFile.close();
//                    }
//                }
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
        }
    }
}
