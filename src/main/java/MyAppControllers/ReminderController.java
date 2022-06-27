package MyAppControllers;

import MyAppDatabase.databaseConnection;
import ToDoList.LocalEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class ReminderController extends MenuController implements Initializable {

    @FXML
    private Button MoveButton;

    public void initialize(URL url, ResourceBundle rb)
    {
        fetchData();
    }

    @FXML
    ListView<LocalEvent> eventList;
    ObservableList<LocalEvent> list= FXCollections.observableArrayList();

    @FXML
    public void MoveToToDoList() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Menu/Gui.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = (Stage) MoveButton.getScene().getWindow();
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
                String Completed=rs.getString("Completed");
                String cur_date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

                int date=(cur_date.charAt(0)-'0')*10+cur_date.charAt(1)-'0';
                int month=(cur_date.charAt(3)-'0')*10+cur_date.charAt(4)-'0';
                int year=(cur_date.charAt(6)-'0')*1000+(cur_date.charAt(7)-'0')*100+(cur_date.charAt(8)-'0')*10+cur_date.charAt(9)-'0';

                int c=0;


                String database_date=rs.getString("Date");
                int data_date=(database_date.charAt(0)-'0')*10+database_date.charAt(1)-'0';
                int data_month=0;

                String data_Stringmonth="";

                int pos;
                for(pos=3;(database_date.charAt(pos)>='A' &&database_date.charAt(pos)<='Z')
                             ||(database_date.charAt(pos)>='a' &&database_date.charAt(pos)<='z');pos++ )
                {
                    data_Stringmonth=data_Stringmonth+database_date.charAt(pos);
                }
                pos++;


                if(data_Stringmonth.equals("January"))
                {
                    data_month=1;
                }
                else if( data_Stringmonth.equals("February"))
                {
                    data_month=2;
                }
                else if( data_Stringmonth.equals("March"))
                {
                    data_month=3;

                }
                else if( data_Stringmonth.equals("April"))
                {
                    data_month=4;
                }
                else if( data_Stringmonth.equals("May"))
                {
                    data_month=5;
                }
                else if( data_Stringmonth.equals("June"))
                {
                    data_month=6;
                }
                else if( data_Stringmonth.equals("July"))
                {
                    data_month=7;
                }
                else if( data_Stringmonth.equals("August"))
                {
                    data_month=8;
                }
                else if( data_Stringmonth.equals("September"))
                {
                    data_month=9;
                }
                else if( data_Stringmonth.equals("October"))
                {
                    data_month=10;
                }
                else if( data_Stringmonth.equals("November"))
                {
                    data_month=11;
                }
                else if( data_Stringmonth.equals("December"))
                {
                    data_month=12;
                }

                int data_year=(database_date.charAt(pos)-'0')*1000+(database_date.charAt(pos+1)-'0')*100+(database_date.charAt(pos+2)-'0')*10+(database_date.charAt(pos+3)-'0');


                if(name.equals(getUsername()) && Completed.equals("No") && date==data_date && month==data_month && year==data_year) {
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
