package MyAppControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import MyAppDatabase.databaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {
    @FXML
    private Button cancelbutton;

    @FXML
    private Label loginmessagelabel;

    @FXML
    private TextField usernametextfield;

    @FXML
    private PasswordField passwordPasswordfield;

    @FXML
    private Button loginbutton;

    private static String username;
    private static String Childusername;

    public void setUsername(String name) {
        username =name;
    }
    public void setChildUsername(String name) {
        Childusername = name;
    }

    public String getUsername()
    {
        return username;
    }

    public String getChildUsername() {
        return Childusername;
    }


        public void cancelButtonOnAction() {
        Stage stage = (Stage) cancelbutton.getScene().getWindow();
        stage.close();

    }

    @FXML
    public void loginActionEvent() {
        if (usernametextfield.getText().isBlank() == false && passwordPasswordfield.getText().isBlank() == false) {
            loginmessagelabel.setText("You Tried to login");
            validateLogin();

        } else {
            loginmessagelabel.setText("Please Enter Username and Password ");
        }
    }

    public void validateLogin() {
        databaseConnection connectNow = new databaseConnection();
        Connection connectDbase = connectNow.getConnection();
        String verifyLogin = "SELECT count(1) FROM user_account WHERE username='" + usernametextfield.getText() + "' AND password='" + passwordPasswordfield.getText() + "'";
        setUsername(usernametextfield.getText());
        Childusername="jim";

        try {
            Statement statement = connectDbase.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while ( queryResult.next() ) {
                if (queryResult.getInt(1) == 1) {
                    loginmessagelabel.setText("Welcome!");
                    EnterMenu();
                } else {
                    loginmessagelabel.setText("Invalid login!Please try again!");
                }
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void EnterMenu()
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Menu/Menu (1).fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage registerStage = (Stage) loginbutton.getScene().getWindow();
            registerStage.setScene(new Scene(root1));
            registerStage.show();

        }
        catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

}