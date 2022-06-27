package BudgetTracker;


public class LocalEvent {

    private String IncomeSource ;
    private String IncomeAmount;


    public String getIncomeSource() {
        return IncomeSource;
    }
    public void setIncomeSource(String IncomeSource) {
        this.IncomeSource = IncomeSource;
    }

    public String getIncomeAmount() {
        return IncomeAmount;
    }
    public void setIncomeAmount(String IncomeAmount) {
        this.IncomeAmount = IncomeAmount;
    }


    public LocalEvent()
    {

    }

    public LocalEvent(String IncomeSource,String IncomeAmount) throws Exception
    {
        this.setIncomeSource(IncomeSource);
        this.setIncomeAmount(IncomeAmount);

        String Income=this.getIncomeAmount();

        perIncome=Integer.parseInt(Income);

        if(perIncome<0)throw new RuntimeException();
        //   System.out.println(total+" "+perIncome);
        total=total+perIncome;
        //       System.out.println(total+" "+perIncome);
        TotalIncome=String.valueOf(total);
        //     System.out.println(TotalIncome);

    }

    static int total;
    int perIncome;
    String TotalIncome;
    @Override
    public String toString()
    {

        return this.getIncomeSource() +": "+this.getIncomeAmount()+" Taka Added." + " Now Net Total: "+TotalIncome+  "Taka";

    }
}