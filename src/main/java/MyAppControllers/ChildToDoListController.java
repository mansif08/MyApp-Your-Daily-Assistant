package MyAppControllers;
import ToDoList.LocalEvent;
import ToDoList.ToDoListOverview;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import MyAppDatabase.databaseConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ChildToDoListController extends ParentToDoListController implements Initializable {

    public void initialize(URL url, ResourceBundle rb) {
        datePicker.setValue(LocalDate.now());
        fetchData();
    }
    public String res;

    @FXML
    Button addButton;
    @FXML
    Button addHealthButton;
    @FXML
    Button popButton;
    @FXML
    TextField descriptionTextField;
    @FXML
    DatePicker datePicker;


    @FXML
    private Button ChildInstruction;
//    @FXML
  //  private Label instructionLabel;


    @FXML
    private Button ToDoGoBack;

    @FXML
    private Button HealthSuggestion;

    public void fetchData() {
        try {

            databaseConnection connection = new databaseConnection();
            Connection con = connection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mansifdb.todolist_info;");

            while (rs.next()) {
                String name = rs.getString("UserName");
                String Completed = rs.getString("Completed");

                if (name.equals(getChildUsername() ) && Completed.equals("No") ) {
                    list.add(new LocalEvent(rs.getString("Date"), rs.getString("Work")));
                    eventList.setItems(list);

                }

            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }

    }



    @FXML
    public void ToDoGoBack() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ToDoList/ChildToDo.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = (Stage) ToDoGoBack.getScene().getWindow();
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e) {
            System.out.println("Can not open BMI Window");
        }

    }

    @FXML
    public void GoToHealthSuggestion() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ToDoList/ChildHealthSuggestion.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = (Stage) HealthSuggestion.getScene().getWindow();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println("Can not open BMI Window");
        }

    }



    @FXML
    public void popEvent()
    {
        try {
            if (list.size() >= 1) {
                databaseConnection connection = new databaseConnection();
                Connection con = connection.getConnection();
                //System.out.println(username);

                Statement stmt = con.createStatement();

                String insertfields="UPDATE  mansifdb.todolist_info SET Completed='Yes' WHERE UserName='"+getChildUsername()+"' AND Completed='No' LIMIT 1;";

                stmt.executeUpdate(insertfields);
                list.remove(0, 1);
                eventList.setItems(list);

            }

        }
        catch(Exception event)
        {
            event.printStackTrace();
            event.getCause() ;

        }

    }

    @Override
    public void addEventToDatabase() {
        databaseConnection connection = new databaseConnection();
        Connection connectDB = connection.getConnection();

        String Completed = "No";
        String HealthInfo = "No";
        String Work = descriptionTextField.getText();

        LocalDate localDate = datePicker.getValue();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String Date = localDate.format(formatter);


        String insertfields = "INSERT INTO todolist_info(UserName,HealthInfo,Work,Date,Completed) VALUES ('";
        String insertvalues = getChildUsername() + "','" + HealthInfo + "','" + Work + "','" + Date + "','" + Completed + "')";
        String insertToRegister = insertfields + insertvalues;

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void addEventToDatabase(String ItIsHealthEvent) {
        databaseConnection connection = new databaseConnection();
        Connection connectDB = connection.getConnection();

        String Completed = "No";
        String HealthInfo = "Yes";
        String Work = descriptionTextField.getText();

        LocalDate localDate = datePicker.getValue();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String Date = localDate.format(formatter);

        String insertfields = "INSERT INTO todolist_info(UserName,HealthInfo,Work,Date,Completed) VALUES ('";
        String insertvalues = getChildUsername() + "','" + HealthInfo + "','" + Work + "','" + Date + "','" + Completed + "')";
        String insertToRegister = insertfields + insertvalues;

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    /*
    public static void main(String[] args) {
        ChildToDoListController a=new ChildToDoListController();
        a.goToChildInstruction();
    }

     */

}
