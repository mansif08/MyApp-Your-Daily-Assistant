package ToDoList;

public interface ToDoListHealthSuggestionOverview {

    double IdealBMI_LowerRange=18.5;
    double IdealBMI_UpperRange=25;

    double Underweight_UpperRange=18.5;

    double Overweight_LowerRange=25;
    double Overweight_UpperRange=30;
    double ObesityClass1_LowerRange=30;
    double ObesityClass1_UpperRange=35;
    double ObesityClass2_LowerRange=35;

    double NormalPulse_LowerRange=60;
    double NormalPulse_UpperRange=130;

    double HighPressureStart=160;
    double HigherMidPressureStart=140;
    double LowerMidPressureStart=120;
    double LowPressureStart=90;

    void HealthSuggestionGoBack();
    void fetchData();
}
