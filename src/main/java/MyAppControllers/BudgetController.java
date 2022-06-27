package MyAppControllers;


import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BudgetController {

    @FXML
    private Button IncomeButton;

    @FXML
    private Button ExpenseButton;

    @FXML
    private Button PlanButton;

    @FXML
    private  Button BudgetGoBack;

    @FXML
    void HandleBudgetGoBack(ActionEvent event) {
        try
        {
            System.out.println(1);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Menu/Menu (1).fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            //Stage stage=new Stage();
            Stage stage = (Stage) BudgetGoBack.getScene().getWindow();
            stage.setScene(new Scene(root1));

            stage.show();

        }
        catch(Exception e)
        {
            System.out.println("Can not open BMI Window");
        }

    }


    @FXML
    void handleButtonAction(ActionEvent event) {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/BudgetTracker/Income.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = (Stage) IncomeButton.getScene().getWindow();
            stage.setScene(new Scene(root1));
            stage.show();

        }
        catch(Exception e)
        {
            System.out.println("Can not open Income Window");
        }

    }

    @FXML
    void handleButtonAction2(ActionEvent event) {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/BudgetTracker/Expense.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = (Stage) ExpenseButton.getScene().getWindow();
            stage.setScene(new Scene(root1));
            stage.show();

        }
        catch(Exception e)
        {
            System.out.println("Can not open plan window");
        }

    }

    @FXML
    void handleButtonAction3(ActionEvent event) {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/BudgetTracker/Plan.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = (Stage)PlanButton.getScene().getWindow();
            stage.setScene(new Scene(root1));
            stage.show();

        }
        catch(Exception e)
        {
            System.out.println("Can not open plan window");
        }

    }

}