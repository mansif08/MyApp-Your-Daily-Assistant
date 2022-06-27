package MyAppControllers;
import BudgetTracker.LocalEvent2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

public class ExpenseController {
    @FXML
    Button enterButton;
    @FXML
    TextField ExpenseSource;
    @FXML
    TextField ExpenseAmount;
    @FXML
    ListView<LocalEvent2> eventList;
    @FXML
    Label validInput;

    ObservableList<LocalEvent2> list= FXCollections.observableArrayList();

    @FXML
    private  Button ExpenseGoBack;

    @FXML
    void GoBackToBudgetMenu() {
        try
        {
            // System.out.println(1);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Menu/BudgetDesign.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            //Stage stage=new Stage();
            Stage stage = (Stage) ExpenseGoBack.getScene().getWindow();
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
            list.add(new LocalEvent2(ExpenseSource.getText(), ExpenseAmount.getText()));
            eventList.setItems(list);
            //System.out.println(list.size());

            refresh();
        }
        catch(Exception exception)
        {
            validInput.setText("enter valid input");
        }

    }

    private void refresh()
    {
        ExpenseAmount.setText("");
        ExpenseSource.setText("");
    }


}
