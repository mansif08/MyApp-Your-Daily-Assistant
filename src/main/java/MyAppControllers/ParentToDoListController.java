package MyAppControllers;
import ToDoList.LocalEvent;
import ToDoList.ToDoListOverview;

import javafx.scene.control.*;
import MyAppDatabase.databaseConnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ParentToDoListController extends ToDoListOverview implements Initializable{
  int count=0;

  public void initialize(URL url,ResourceBundle rb)
  {
    datePicker.setValue(LocalDate.now());
    fetchData();
  }
  public void fetchData()  {
    try {
      databaseConnection connection = new databaseConnection();
      Connection con = connection.getConnection();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM mansifdb.todolist_info;");
      while (rs.next()) {
        String name = rs.getString("UserName");
        String Completed=rs.getString("Completed");;

        if(name.equals(getUsername()) && Completed.equals("No") ) {
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
  ListView<LocalEvent>eventList;

  @FXML
  private Label instructionLabel;


  ObservableList<LocalEvent>list= FXCollections.observableArrayList();

  @FXML
  private Button ToDoGoBack;

  @FXML
  private Button HealthSuggestion;

  @FXML
  private Button ChildInstruction;


  @FXML
  public void ToDoGoBack() {
    try
    {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Menu/Menu (1).fxml"));
      Parent root1 = (Parent) fxmlLoader.load();
      Stage stage = (Stage) ToDoGoBack.getScene().getWindow();
      stage.setScene(new Scene(root1));
      stage.show();
    }

    catch(Exception e)
    {
      System.out.println("Can not open BMI Window");
    }

  }

  @FXML
  public void GoToHealthSuggestion() {
    try
    {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ToDoList/HealthSuggestion.fxml"));
      Parent root1 = (Parent) fxmlLoader.load();
      Stage stage = (Stage) HealthSuggestion.getScene().getWindow();
      stage.setScene(new Scene(root1));
      stage.show();
    }
    catch(Exception e)
    {
      System.out.println("Can not open BMI Window");
    }

  }


  @FXML
  public void addEvent()
  {
    String s=descriptionTextField.getText();
    if(s.length()>=1) {
      LocalDate localDate = datePicker.getValue();//For reference
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
      String formattedString = localDate.format(formatter);
      list.add(new LocalEvent(formattedString, descriptionTextField.getText()));
      eventList.setItems(list);
      addEventToDatabase();
      refresh();
    }
    else
    {
      instructionLabel.setText("No Tasks Added");
    }
  }

  @FXML
  public void addHealthEvent()
  {
    String s=descriptionTextField.getText();
    if(s.length()>=1) {
      LocalDate localDate = datePicker.getValue();//For reference
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
      String formattedString = localDate.format(formatter);
      list.add(new LocalEvent(formattedString, descriptionTextField.getText()));
      eventList.setItems(list);
      addEventToDatabase("Health");
      refresh();
    }
    else
    {
      instructionLabel.setText("No Tasks Added");
    }
  }

  public void refresh()
  {
    datePicker.setValue(LocalDate.now());
    descriptionTextField.setText("");
    instructionLabel.setText("");
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
        String insertfields="UPDATE  mansifdb.todolist_info SET Completed='Yes' WHERE UserName='"+getUsername()+"' AND Completed='No' LIMIT 1;";
        stmt.executeUpdate(insertfields);
        list.remove(0, 1);
        eventList.setItems(list);
      }
      else
      {
        instructionLabel.setText("No events to be popped");
      }
    }
    catch(Exception event)
    {
      event.printStackTrace();
      event.getCause() ;

    }

  }

  public void addEventToDatabase()
  {
    databaseConnection connection=new databaseConnection();
    Connection connectDB= connection.getConnection();
    //System.out.println(username);

    String Completed="No";
    String HealthInfo="No";
    String Work=descriptionTextField.getText();

    LocalDate localDate = datePicker.getValue();//For reference
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
    String  Date= localDate.format(formatter);

    String insertfields="INSERT INTO todolist_info(UserName,HealthInfo,Work,Date,Completed) VALUES ('" ;
    String insertvalues=getUsername() + "','" +HealthInfo+"','"+Work+"','"+Date+"','"+Completed+"')";
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

  public void addEventToDatabase(String ItIsHealthEvent) {
    databaseConnection connection = new databaseConnection();
    Connection connectDB = connection.getConnection();
    //System.out.println(username);

    String Completed = "No";
    String HealthInfo = "Yes";
    String Work = descriptionTextField.getText();

    LocalDate localDate = datePicker.getValue();//For reference
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
    String Date = localDate.format(formatter);

    String insertfields = "INSERT INTO todolist_info(UserName,HealthInfo,Work,Date,Completed) VALUES ('";
    String insertvalues = getUsername() + "','" + HealthInfo + "','" + Work + "','" + Date + "','" + Completed + "')";
    String insertToRegister = insertfields + insertvalues;

    try {
      Statement statement = connectDB.createStatement();
      statement.executeUpdate(insertToRegister);

    } catch (Exception e) {
      e.printStackTrace();
      e.getCause();
    }
  }

  @FXML
  public void GoToChildInstruction()
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
        instructionLabel.setText("Not authenticated to give instructions as parent!");
      }

      else {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ToDoList/ChildToDo.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage registerStage = (Stage) ChildInstruction.getScene().getWindow();
        registerStage.setScene(new Scene(root1));
        registerStage.show();
      }

    }
    catch(Exception event2)
    {
      event2.printStackTrace();
      event2.getCause() ;
    }

  }

}