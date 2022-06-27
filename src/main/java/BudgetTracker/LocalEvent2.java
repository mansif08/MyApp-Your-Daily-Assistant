package BudgetTracker;

import java.time.LocalDate;
import java.time.LocalTime;


public class LocalEvent2  extends LocalEvent{

    private String ExpenseSource ;

    private String ExpenseAmount;

    public String getExpenseSource() {
        return ExpenseSource;
    }
    public void setExpenseSource(String ExpenseSource) {
        this.ExpenseSource = ExpenseSource;
    }

    public String getExpenseAmount() {return ExpenseAmount;}
    public void setExpenseAmount(String ExpenseAmount) {
        this.ExpenseAmount = ExpenseAmount;
    }


    public LocalEvent2()
    {

    }

    public LocalEvent2(String ExpenseSource,String ExpenseAmount) throws Exception
    {
        this.setExpenseSource(ExpenseSource);
        this.setExpenseAmount(ExpenseAmount);

        String Expense=this.getExpenseAmount();

        perExpense=Integer.parseInt(Expense);
        //   System.out.println(total+" "+perIncome);
        if(perExpense<0)throw new RuntimeException();
        expensetotal=expensetotal+perExpense;

        //       System.out.println(total+" "+perIncome);
        TotalExpense=String.valueOf(expensetotal);
        //     System.out.println(TotalIncome);
        total=total-perExpense;
    }

    static int expensetotal;
    int perExpense;
    String TotalExpense;
    @Override
    public String toString()
    {
        return this.getExpenseSource() +": "+this.getExpenseAmount()+" Taka Spent." + " Now Total Expense: "+TotalExpense+  "Taka";
    }

}