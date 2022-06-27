package ToDoList;

import MyAppControllers.MenuController;
import javafx.fxml.FXML;

abstract public class ToDoListOverview extends MenuController {
    abstract public void fetchData();
    @FXML
    abstract public void addEvent();
    @FXML
    abstract public void addHealthEvent();
    @FXML
    abstract public void popEvent();
    abstract public void refresh();
    abstract public void addEventToDatabase();
    abstract public void addEventToDatabase(String s);
    @FXML
    abstract public void ToDoGoBack();
    @FXML
    abstract public void GoToHealthSuggestion();

    @FXML
    public void GoToChildInstruction()
    {
        System.out.println("Not Authenticated to give instruction");
    }

}
