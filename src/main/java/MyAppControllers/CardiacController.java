package MyAppControllers;

import HealthTracker.HealthTrackerCalculationOverview;
import MyAppDatabase.databaseConnection;
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

public class CardiacController extends HealthMenuController implements HealthTrackerCalculationOverview {

    @FXML
    TextField Pulse;

    @FXML
    TextField DiastolicPressure;

    @FXML
    TextField SystolicPressure;


    @FXML
    TextField PulseIndication=new TextField("Default text entered here");


    @FXML
    TextField PressureIndication=new TextField("Default text entered here");

    @FXML
    Button Submit;

    @FXML
    Label validInput=new Label("default");



    private int pulse,diastolicPressure,systolicPressure;

    @FXML
    private  Button CardiacGoBack;

    @FXML
    public void HealthTrackerGoBack() {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Menu/HealthDesign.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            //Stage stage=new Stage();
            Stage stage = (Stage) CardiacGoBack.getScene().getWindow();
            stage.setScene(new Scene(root1));
            stage.show();
        }

        catch(Exception e)
        {
            System.out.println("Can not open BMI Window");
        }

    }



    public void CalculatePulseAndPressure() {

        try {

            validInput.setText("");
            String Ps = Pulse.getText();

            pulse = Integer.parseInt(Ps);

            String Dis = DiastolicPressure.getText();

            diastolicPressure = Integer.parseInt(Dis);

            String Sys = SystolicPressure.getText();

            systolicPressure = Integer.parseInt(Sys);

            if (pulse <= 0 || diastolicPressure <= 0 || systolicPressure <= 0) {
                throw new RuntimeException();
            }

            String PressureI = "";
            String PulseI = "";

            if (pulse < NormalPulse_LowerRange) {
                PulseI = "Low Pulse Rate";
            } else if (pulse > NormalPulse_UpperRange) {
                PulseI = "High Pulse Rate";
            } else {
                PulseI = "Normal Pulse Rate";
            }

            if (diastolicPressure > NormalPressure_UpperRange) {
                PressureI = "High Pressure";
            } else if (diastolicPressure < NormalPressure_LowerRange) {
                PressureI = "Low Pressure";
            } else {
                PressureI = "Normal Pressure";
            }


            PulseIndication.setText("Pulse Indication: " + PulseI);
            PressureIndication.setText("Pressure Indication: " + PressureI);
            AddHealthReportToDatabase();
        }
        catch (Exception exception)
        {
        //    System.out.println(e);
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


        String TestType="Systolic Pressure";

        String insertfields="INSERT INTO health_info(UserName,Test_Type,Value,Date) VALUES ('" ;
        String insertvalues=getUsername() + "','" +TestType+"','" +systolicPressure+"','" +formattedString+ "')";
        String insertToRegister=insertfields+insertvalues;

        String TestType2="Diastolic Pressure";

        String insertfields2="INSERT INTO health_info(UserName,Test_Type,Value,Date) VALUES ('" ;
        String insertvalues2=getUsername() + "','" +TestType2+"','" +diastolicPressure+"','" +formattedString+ "')";
        String insertToRegister2=insertfields2+insertvalues2;

        String TestType3="Pulse";

        String insertfields3="INSERT INTO health_info(UserName,Test_Type,Value,Date) VALUES ('" ;
        String insertvalues3=getUsername() + "','" +TestType3+"','" +pulse+"','" +formattedString+ "')";
        String insertToRegister3=insertfields3+insertvalues3;

        try
        {
            Statement statement= connectDB.createStatement();
            statement.executeUpdate(insertToRegister);
            statement.executeUpdate(insertToRegister2);
            statement.executeUpdate(insertToRegister3);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            e.getCause() ;
        }

    }

}