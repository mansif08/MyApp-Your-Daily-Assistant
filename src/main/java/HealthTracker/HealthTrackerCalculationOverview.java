package HealthTracker;

import javafx.fxml.FXML;

public interface HealthTrackerCalculationOverview  {

    double Inch_Per_Meter=39.3701;
    double IdealBMI_LowerRange=18.5;
    double IdealBMI_UpperRange=25;

    double Underweight_UpperRange=18.5;

    double Overweight_LowerRange=25;
    double Overweight_UpperRange=30;

    double ObesityClass1_LowerRange=30;
    double ObesityClass1_UpperRange=35;
    double ObesityClass2_LowerRange=35;
    double ObesityClass2_UpperRange=40;
    double ObesityClass3_LowerRange=40;


    double NormalPulse_LowerRange=60;
    double NormalPulse_UpperRange=130;
    double NormalPressure_LowerRange=145;
    double NormalPressure_UpperRange=110;


    @FXML
    void HealthTrackerGoBack();
    void AddHealthReportToDatabase();

    default void CalculateBMI()
    {
        System.out.println("Not Implemented Yet");
    }
    default void CalculatePulseAndPressure()
    {
        System.out.println("Not Implemented Yet");
    }
}
