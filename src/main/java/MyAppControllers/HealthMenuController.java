package MyAppControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HealthMenuController extends MenuController{

    @FXML
    private Button BmiButton;

    @FXML
    private Button CardiacButton;

    @FXML
    private Button ExerButton;

    @FXML
    private Button infoButton;

    @FXML
    private  Button HealthGoBack;

    @FXML
    private  Button HealthSteps;

    @FXML
    void GoBackToMenu(ActionEvent event) {
        try
        {
           // System.out.println(1);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Menu/Menu (1).fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            //Stage stage=new Stage();
            Stage stage = (Stage) HealthGoBack.getScene().getWindow();
            stage.setScene(new Scene(root1));

            stage.show();

        }
        catch(Exception e)
        {
            System.out.println("Can not open BMI Window");
        }

    }


    @FXML
    void GoToBMI() {
        try
        {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/HealthTracker/BMIDesign.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = (Stage) BmiButton.getScene().getWindow();
            stage.setScene(new Scene(root1));
            stage.show();

        }
        catch(Exception e)
        {
            System.out.println("Can not open BMI Window");
        }

    }
    @FXML
    void GoToCardiac() {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/HealthTracker/CardiacDesign.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stages = (Stage) CardiacButton.getScene().getWindow();
            stages.setScene(new Scene(root1));
            stages.show();

        }
        catch(Exception e)
        {
            System.out.println("Can not open Cardiac Window");
        }

    }

    @FXML
    void GoToExercise() {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/HealthTracker/ExerciseDesign.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stages = (Stage) ExerButton.getScene().getWindow();
            stages.setScene(new Scene(root1));
            stages.show();

        }
        catch(Exception e)
        {
            System.out.println("Can not open Cardiac Window");
        }

    }
    @FXML
    void GoToHealthInfo() {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/HealthTracker/myHealthInfo.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = (Stage) infoButton.getScene().getWindow();
            stage.setScene(new Scene(root1));
            stage.show();
        }

        catch(Exception e)
        {
            System.out.println("Can not open Cardiac Window");
        }

    }

    @FXML
    void GoToHealthSteps() {

        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/HealthTracker/HealthSteps.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = (Stage) HealthSteps.getScene().getWindow();
            stage.setScene(new Scene(root1));
            stage.show();
        }

        catch(Exception e)
        {
            System.out.println("Can not open Cardiac Window");
        }

    }

}