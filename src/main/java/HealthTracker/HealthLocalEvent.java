package HealthTracker;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class HealthLocalEvent {

    private String test_type;
    private double value;
    private String date;


    public String getTestType() {
        return test_type;
    }

    public void setTest_type(String test_type) {
        this.test_type = test_type;
    }

    public double getValue() {return value;}

    public void setValue(double value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public HealthLocalEvent(String test_type,double value,String date)
    {
        this.setTest_type(test_type);

        this.setValue(value);

        this.setDate(date);

    }
    @Override
    public String toString()
    {

        return "On : " + this.getDate() +" "+this.getTestType()+" "+this.getValue();
    }
}