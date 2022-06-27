package MyAppControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import MyAppDatabase.databaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class RegisterController extends LoginController
{
    @FXML
    private Button closeButton ;
    @FXML
    private Label registrationConfLabel ;
    @FXML
    private PasswordField setPasswordField;
    @FXML
    private PasswordField Confirmpasswordfield ;
    @FXML
    private Label PasswordmatchLabel;

    @FXML
    private TextField firstnameTextfield;

    @FXML
    private TextField lastnameTextfield;
    @FXML
    private TextField usernameTextfield;

    @FXML
    private  Button RegisterGoBack;

    @FXML
    void HandleRegisterGoBack(ActionEvent event) {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Menu/Menu (1).fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = (Stage) RegisterGoBack.getScene().getWindow();
            stage.setScene(new Scene(root1));
            stage.show();
        }

        catch(Exception e)
        {
            System.out.println("Can not open BMI Window");
        }

    }

    public void closeButtonOnAction(ActionEvent  e) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void registrationButtonAction(ActionEvent ex)
    {
        if(!firstnameTextfield.getText().isEmpty() && !lastnameTextfield.getText().isEmpty() && !usernameTextfield.getText().isEmpty()&& !setPasswordField.getText().isEmpty() &&   setPasswordField.getText().equals(Confirmpasswordfield.getText()))
        {
            registerUser();
            PasswordmatchLabel.setText("");
        }
        else if(firstnameTextfield.getText().isEmpty() || lastnameTextfield.getText().isEmpty() || usernameTextfield.getText().isEmpty()|| setPasswordField.getText().isEmpty())
        {
            PasswordmatchLabel.setText("Essential field left empty!");
        }

        else if(!setPasswordField.getText().equals(Confirmpasswordfield.getText()))
        {

            PasswordmatchLabel.setText("Password doesn't match!");
        }

    }
    @FXML
    public void registrationParentButtonAction(ActionEvent e)
    {
        try {
            databaseConnection connection = new databaseConnection();
            Connection con = connection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mansifdb.user_account;");
            int c=0;

            while (rs.next()) {

                String name = rs.getString("username");

                String parent=rs.getString("parent");

                if (name.equals(getUsername()) && parent.equals("Yes"))
                {
                    c++;
                    break;
                }

            }
            if(c==0)
            {
                PasswordmatchLabel.setText("Not authenticated to register as parent!");
            }

            else if(!firstnameTextfield.getText().isEmpty() && !lastnameTextfield.getText().isEmpty() && !usernameTextfield.getText().isEmpty()&& !setPasswordField.getText().isEmpty() &&   setPasswordField.getText().equals(Confirmpasswordfield.getText()))
            {
                registerParentUser();
                PasswordmatchLabel.setText("");
            }
            else if(firstnameTextfield.getText().isEmpty() || lastnameTextfield.getText().isEmpty() || usernameTextfield.getText().isEmpty()|| setPasswordField.getText().isEmpty())
            {
                PasswordmatchLabel.setText("Essential field left empty!");
            }

            else if(!setPasswordField.getText().equals(Confirmpasswordfield.getText()))
            {

                PasswordmatchLabel.setText("Password doesn't match!");
            }
        }
        catch(Exception event2)
        {
            event2.printStackTrace();
            event2.getCause() ;
        }
    }

    public void registerUser()
    {
        databaseConnection connection=new databaseConnection();
        Connection connectDB= connection.getConnection();
        String firstname=firstnameTextfield.getText();
        String lastname=lastnameTextfield.getText();
        String username=usernameTextfield.getText() ;
        String password=setPasswordField.getText();
        String parent="No";

        String insertfields="INSERT INTO user_account(FirstName,LastName,UserName,password,parent) VALUES ('" ;
        String insertvalues=firstname + "','" +lastname+"','"+username+"','"+password+"','"+parent+"')";
        String insertToRegister=insertfields+insertvalues;

        try
        {
            Statement statement= connectDB.createStatement();
            statement.executeUpdate(insertToRegister);
            registrationConfLabel.setText("User registered successfully!!");

        }
        catch(Exception e)
        {
            registrationConfLabel.setText("Enter valid input!!");
        }

    }

    @FXML
    public void registerParentUser()
    {
        databaseConnection connection=new databaseConnection();
        Connection connectDB= connection.getConnection();

        String firstname=firstnameTextfield.getText();
        String lastname=lastnameTextfield.getText();
        String username=usernameTextfield.getText() ;
        String password=setPasswordField.getText();

        String parent="Yes";
        String insertfields="INSERT INTO user_account(FirstName,LastName,UserName,password,parent) VALUES ('" ;
        String insertvalues=firstname + "','" +lastname+"','"+username+"','"+password+"','"+parent+"')";
        String insertToRegister=insertfields+insertvalues;

        try
        {
            Statement statement= connectDB.createStatement();
            statement.executeUpdate(insertToRegister);
            registrationConfLabel.setText("User registered successfully!!");

        }
        catch(Exception e)
        {
            registrationConfLabel.setText("Enter valid input!!");
        }

    }


}
