package MyAppControllers;

import DailyCalculator.EvaluateString;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class CalculatorController {

    @FXML
    private Label exp;
    @FXML
    private Label result;

    @FXML
    private Button calcGoBack;
    public void insertNumber(String number)
    {
        exp.setText(exp.getText()+number);
    }
    public void insertOperator(String operator)
    {
        exp.setText(exp.getText()+" "+operator+" ") ;
    }

    @FXML
    void handleGoBack(ActionEvent event) {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Menu/Menu (1).fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            //Stage stage=new Stage();
            Stage stage = (Stage) calcGoBack.getScene().getWindow();
            stage.setScene(new Scene(root1));

            stage.show();

        }
        catch(Exception e)
        {
            System.out.println("Can not open BMI Window");
        }

    }

    public void deleteLst()
    {
        if(!getExp().getText().isEmpty())
        {
            StringBuilder line=new StringBuilder(getExp().getText()) ;
            line.deleteCharAt(line.length()-1) ;
            getExp().setText(line.toString());
        }
    }
    public void clearExp()
    {
        exp.setText("");
        result.setText("");

    }

    public Label getExp() {
        return exp;
    }
    public Label getResult()
    {
        return result ;
    }
    public void setResult(String newResult)
    {
        this.result.setText("= " + newResult);
    }

    public void insertAnswer(String ans)
    {
        exp.setText(exp.getText()+ans);

    }

    public void OnMouseClick(MouseEvent mouseEvent)
    {
        Button button=(Button)mouseEvent.getSource() ;
        String buttonText=button.getText() ;

        switch(buttonText)
        {
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "0":
                insertNumber(buttonText);
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                insertOperator(buttonText);
                break;
            case "CLEAR":
                clearExp();
                break;
            case "=":

                try {
                    double result = EvaluateString.evaluate(this.getExp().getText());

                    setResult(String.valueOf(String.format("%.2f", result)));
                }
                catch(Exception e)
                {
                    setResult("Invalid");
                }
                break;
            case "ANS" :
                if(!getResult().getText().isEmpty())
                    insertAnswer(getResult().getText().substring(2));
                break;
            case "DELETE" :
                deleteLst();
                break;

        }
    }
}
