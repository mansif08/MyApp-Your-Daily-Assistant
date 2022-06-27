package MyAppControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import MyAppDatabase.databaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ChildToDoListLoginController  extends LoginController {

    @FXML
    private TextField Name;

    @FXML
    private Button ChildUserGoBack;

    @FXML
    private Button submit;

    @FXML
    private Label childuser;

    @FXML
    void ChildLoginUserGoBack() {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Menu/Gui.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = (Stage) ChildUserGoBack.getScene().getWindow();
            stage.setScene(new Scene(root1));

            stage.show();
        }
        catch(Exception e)
        {
            System.out.println("Can not open BMI Window");
        }

    }

    @FXML
    void VerifyChildLogin() {
        try
        {
            databaseConnection connection = new databaseConnection();
            Connection con = connection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mansifdb.user_account;");
            int c=0;
            setChildUsername(Name.getText());
            while (rs.next()) {

                String name=rs.getString("UserName");

                String parent=rs.getString("parent");

                if (name.equals(getChildUsername()) && parent.equals("No") )
                {
                    c++;
                    break;
                }

            }


            if(c==0)
            {
                childuser.setText("Give proper username!");
            }

            else {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ToDoList/ChildToDoList.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = (Stage) submit.getScene().getWindow();
                stage.setScene(new Scene(root1));
                stage.show();

            }
        }
        catch(Exception e)
        {
            System.out.println("Can not open BMI Window");
        }

    }


}
