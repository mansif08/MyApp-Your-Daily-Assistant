package MyAppControllers;

import HealthTracker.HealthLocalEvent;
import HealthTracker.HealthTrackerDataOverview;
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

public class HealthInfoController extends HealthMenuController implements Initializable, HealthTrackerDataOverview {

    public void initialize(URL url, ResourceBundle rb)
    {
        fetchData();
    }


    @FXML
    ListView<HealthLocalEvent> eventList;
    ObservableList<HealthLocalEvent> list= FXCollections.observableArrayList();

    @FXML
    private Button HealthInfoButton;

    @FXML
    public void HealthTrackerGoBack() {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Menu/HealthDesign.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = (Stage) HealthInfoButton.getScene().getWindow();
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

                if(name.equals(getUsername())  ) {
                    list.add(new HealthLocalEvent(rs.getString("Test_Type") ,rs.getDouble("Value"), rs.getString("Date")));
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
