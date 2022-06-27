package MyAppControllers;

//import  DailyCalculator.*;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController extends LoginController {

    @FXML
    private Button menuCalc;
    @FXML
    private Button menuToDo;
    @FXML
    private Button menuHealthTracker;
    @FXML
    private Button menuBudget;
    @FXML
    private Button registerButton;

    @FXML
    void GoToCalculator() {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Menu/Main.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            Stage stage = (Stage) menuCalc.getScene().getWindow();
            stage.setScene(new Scene(root1));

            stage.show();

        }
        catch(Exception e)
        {
            System.out.println("Can not open BMI Window");
        }

    }

    @FXML
    void GoToToDoList() {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ToDoList/ReminderFxml.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            Stage stage = (Stage) menuToDo.getScene().getWindow();
            stage.setScene(new Scene(root1));
            stage.show();

        }
        catch(Exception e)
        {
            System.out.println("Can not open Cardiac Window");
        }

    }

    @FXML
    void GoToHealthTracker() {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Menu/HealthDesign.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = (Stage) menuHealthTracker.getScene().getWindow();
            stage.setScene(new Scene(root1));
            stage.show();

        }
        catch(Exception e)
        {
            System.out.println("Can not open Cardiac Window");
        }

    }

    @FXML
    void GoToBudgetTracker() {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Menu/BudgetDesign.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = (Stage) menuBudget.getScene().getWindow();
            stage.setScene(new Scene(root1));
            stage.show();

        }
        catch(Exception e)
        {
            System.out.println("Can not open Cardiac Window");
        }
    }

    @FXML
    void GoToRegister() {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/login/register.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.setScene(new Scene(root1));
            stage.show();

        }
        catch(Exception e)
        {
            System.out.println("Can not open Cardiac Window");
        }
    }


}
