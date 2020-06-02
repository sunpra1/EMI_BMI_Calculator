package softwaricacollege.sunilprasai.emi_bmi_calculator;

public class Loan {
    private double principal;
    private double interestRate;
    private int installments;

    Loan(double principal, double interestRate, int installments) {
        this.principal = principal;
        this.interestRate = interestRate/100;
        this.installments = installments;
    }

    double calculateEMI(){
        return principal*interestRate*((Math.pow((1+interestRate), installments)) / (Math.pow((1+interestRate), installments) - 1));
    }
}
