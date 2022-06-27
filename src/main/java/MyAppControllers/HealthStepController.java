package MyAppControllers;

import HealthTracker.HealthTrackerDataOverview;
import ToDoList.LocalEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import MyAppDatabase.databaseConnection;

public class HealthStepController extends HealthMenuController implements Initializable,HealthTrackerDataOverview {
    @FXML
    private Button HealthStepButton;

    public void initialize(URL url, ResourceBundle rb)
    {
        fetchData();
    }

    @FXML
    ListView<LocalEvent> eventList;
    ObservableList<LocalEvent> list= FXCollections.observableArrayList();


    @FXML
    public void HealthTrackerGoBack() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Menu/HealthDesign.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = (Stage) HealthStepButton.getScene().getWindow();
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e) {
            System.out.println("Can not open BMI Window");
        }


    }

    public void fetchData()  {
        try {
            databaseConnection connection = new databaseConnection();
            Connection con = connection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mansifdb.todolist_info;");
            while (rs.next()) {
                String name = rs.getString("UserName");
                String HealthInfo = rs.getString("HealthInfo");
                String Completed = rs.getString("Completed");


                if(name.equals(getUsername()) && HealthInfo.equals("Yes") &&  Completed.equals("Yes") ) {
                    list.add(new LocalEvent(rs.getString("Date"), rs.getString("Work")));
                    eventList.setItems(list);


                }

            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            e.getCause() ;
        }

    }

}