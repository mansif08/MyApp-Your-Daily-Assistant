package MyAppControllers;

import BudgetTracker.LocalEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

public class IncomeController {
    @FXML
    Button enterButton;
    @FXML
    TextField IncomeSource;
    @FXML
    TextField IncomeAmount;
    @FXML
    ListView<LocalEvent> eventList;

    @FXML
    Label validInput;



    ObservableList<LocalEvent> list= FXCollections.observableArrayList();

    @FXML
    private  Button IncomeGoBack;

    @FXML
    void GoBackToBudgetMenu() {
        try
        {
            // System.out.println(1);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Menu/BudgetDesign.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            //Stage stage=new Stage();
            Stage stage = (Stage) IncomeGoBack.getScene().getWindow();
            stage.setScene(new Scene(root1));

            stage.show();

        }
        catch(Exception e)
        {
            System.out.println("Can not open BMI Window");
        }

    }

    @FXML
    private void enterEvent(Event e)
    {

        try {
            validInput.setText("");
            list.add(new LocalEvent(IncomeSource.getText(), IncomeAmount.getText()));
            eventList.setItems(list);
            //System.out.println(list.size());

            refresh();
        }
        catch(Exception exception)
        {
            //System.out.println(exception);
            validInput.setText("enter valid input");
        }

    }

    private void refresh()
    {
        IncomeSource.setText("");
        IncomeAmount.setText("");
    }



}
