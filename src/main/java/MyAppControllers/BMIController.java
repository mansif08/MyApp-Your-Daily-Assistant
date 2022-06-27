package MyAppControllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import MyAppDatabase.databaseConnection;
import HealthTracker.HealthTrackerCalculationOverview;

public class BMIController extends HealthMenuController implements HealthTrackerCalculationOverview {

    @FXML
    TextField Height;

    @FXML
    TextField Weight;

    @FXML
    TextField BMI =new TextField("Default text entered here");

    @FXML
    TextField HealthyWeight=new TextField("Default text entered here");

    @FXML
    TextField Suggestion=new TextField("Default text entered here");

    @FXML
    Label validInput=new Label("Default");


    @FXML
    Button Submit;


    private double kg,inch,meter;
    private double idealkgmin;
    private double idealkgmax;

    private double result;

    @FXML
    private  Button HealthGoBack;

    @FXML
    public void HealthTrackerGoBack() {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Menu/HealthDesign.fxml"));
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

    public void CalculateBMI () {
        try {
            validInput.setText("");
            String Ws = Weight.getText();

            kg = Double.parseDouble(Ws);
            String Hs = Height.getText();

            inch = Double.parseDouble(Hs);

            if (kg <= 0 || inch <= 0) {
                throw new RuntimeException();
            }

            meter = inch / Inch_Per_Meter;

            result = kg / (meter * meter);

            String BMIs = String.valueOf(String.format("%.2f", result));

            BMI.setText("Your BMI: " + BMIs);

            idealkgmin = (IdealBMI_LowerRange * meter * meter);

            idealkgmax = (IdealBMI_UpperRange * meter * meter);

            String IKgMins = String.valueOf(String.format("%.2f", idealkgmin));

            String IKgMaxs = String.valueOf(String.format("%.2f", idealkgmax));

            HealthyWeight.setText("Your Healthy Weight : " + IKgMins + "-" + IKgMaxs);

            String suggest = "";

            if (result < Underweight_UpperRange) {
                suggest = "Need to increase weight";
            } else if (result >= IdealBMI_LowerRange && result < IdealBMI_UpperRange) {
                suggest = "Keep up";
            } else if (result >= Overweight_LowerRange && result < Overweight_UpperRange) {
                suggest = "Balanced diet & exercise";
            } else if (result >= ObesityClass1_LowerRange && result < ObesityClass1_UpperRange) {
                suggest = "Proper diet & moderate exercise";
            } else if (result >= ObesityClass2_LowerRange && result < ObesityClass2_UpperRange) {
                suggest = "Diet & rigorous exercise";
            } else if (result >= ObesityClass3_LowerRange) {
                suggest = "Lose weight urgently.";
            }

            Suggestion.setText("Suggestion: " + suggest);

            AddHealthReportToDatabase();

        } catch (Exception exception) {
            //System.out.println(exception);
            validInput.setText("Enter valid input");

        }
    }

    public void AddHealthReportToDatabase()
    {
        databaseConnection connection=new databaseConnection();
        Connection connectDB= connection.getConnection();

        LocalDate localDate =LocalDate.now() ;//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String formattedString = localDate.format(formatter);

        String TestType="BMI";
        String insertfields="INSERT INTO health_info(UserName,Test_Type,Value,Date) VALUES ('" ;
        String insertvalues=getUsername() + "','" +TestType+"','" +result+"','" +formattedString +"')";
        String insertToRegister=insertfields+insertvalues;

        try
        {
            Statement statement= connectDB.createStatement();
            statement.executeUpdate(insertToRegister);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            e.getCause() ;
        }
    }

}