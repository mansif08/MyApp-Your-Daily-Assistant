package ToDoList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LocalEvent {

    private String description ;
    private String date;
    LocalTime currentTime = LocalTime.now();


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public LocalEvent(String date,String description)
    {
        this.setDate(date);
        this.setDescription(description);
    }
    @Override
    public String toString()
    {


        return "On : " + this.getDate() +" "+this.getDescription();

    }
}