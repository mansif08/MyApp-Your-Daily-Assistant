package MyAppControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import MyAppDatabase.databaseConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;


public class ChildHealthSuggestionController extends ParentHealthSuggestionController
{
    public void initialize(URL url, ResourceBundle rb)
    {
        fetchData();
    }
    @FXML
    ListView<String> eventList;
    ObservableList<String> list= FXCollections.observableArrayList();
    String suggest;

    private double bmi=-1;
    private double systolic_pressure=-1;
    private double diastolic_pressure=-1;
    private double pulse=-1;

    @FXML
    private Button HealthSuggestionGoBack;

    @FXML
    public void HealthSuggestionGoBack() {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ToDoList/ChildToDoList.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = (Stage) HealthSuggestionGoBack.getScene().getWindow();
            stage.setScene(new Scene(root1));
            stage.show();
        }

        catch(Exception e)
        {
            System.out.println("Can not open BMI Window");
        }

    }



    public void fetchData()  {
        try {
            databaseConnection connection = new databaseConnection();
            Connection con = connection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mansifdb.health_info;");
            while (rs.next()) {

                String name = rs.getString("UserName");
                String TestType=rs.getString("Test_Type");
                double Value=rs.getDouble("Value");

                if(name.equals(getChildUsername())  ) {
                    /*
                    list.add(new LocalEvent(rs.getString("Date"), rs.getString("Work")));
                    eventList.setItems(list);
                    */
                    if(TestType.equals("BMI"))
                    {
                        bmi=Value;
                    }
                    else if(TestType.equals("Systolic Pressure"))
                    {
                        systolic_pressure=Value;
                    }
                    else if(TestType.equals("Diastolic Pressure"))
                    {
                        diastolic_pressure=Value;
                    }
                    else if(TestType.equals("Pulse"))
                    {
                        pulse=Value;
                    }

                }

            }

            if(bmi!=-1) {
                if (bmi < Underweight_UpperRange) {
                    suggest = "Consult a nutritionist as you have nutrition deficiency and strength building exercise";
                } else if (bmi >= IdealBMI_LowerRange && bmi < IdealBMI_UpperRange) {
                    suggest = "Follow daily diet , do flexibility and strength building exercise";
                } else if (bmi >= Overweight_LowerRange && bmi < Overweight_UpperRange) {
                    suggest = "extend exercise by 30 minutes and do extra endurance exercise";
                } else if (bmi >= ObesityClass1_LowerRange && bmi < ObesityClass1_UpperRange) {
                    suggest = "Consult nutritionist for proper diet and do cardio exercise.";
                } else if (bmi >= ObesityClass2_LowerRange) {
                    suggest = "Consult nutritionist immediately with minimum diet, do cardio and aerobic exercise.";
                }
                list.add(suggest);
                eventList.setItems(list);

            }
            if(diastolic_pressure!=-1) {

                if (diastolic_pressure >=HighPressureStart) {
                    suggest = "Consult a physician immediately for very high blood pressure.";
                }
                else if ( diastolic_pressure>= HigherMidPressureStart ) {
                    suggest = "Consult a physician for elevated blood pressure.";
                } else if (diastolic_pressure >=LowerMidPressureStart ) {
                    suggest= "Take regular checkup for slightly elevated blood pressure";
                } else if( diastolic_pressure >=LowPressureStart ) {
                    suggest = "Carry on basic healthcare to maintain normal blood pressure";
                }
                else{
                    suggest = "Consult a physician for low blood pressure.";
                }

                list.add(suggest);
                eventList.setItems(list);

            }

            if(pulse!=-1) {

                if (pulse < NormalPulse_LowerRange) {
                    suggest = "Consult a cardiologist for low pulse rate.";
                } else if (pulse > NormalPulse_UpperRange) {
                    suggest = "Consult a cardiologist for high pulse rate.";
                } else {
                    suggest = "Take regular health checkups to maintain normal pulse rate.";
                }

                list.add(suggest);
                eventList.setItems(list);


            }


        }
        catch(Exception e)
        {
            e.printStackTrace();
            e.getCause() ;
        }

    }

}
