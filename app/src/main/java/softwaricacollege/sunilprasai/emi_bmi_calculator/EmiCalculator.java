package softwaricacollege.sunilprasai.emi_bmi_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class EmiCalculator extends AppCompatActivity {

    private TextView emiInfo;
    private EditText principalEt;
    private EditText interestRateEt;
    private EditText installmentsEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emi_calculator);

        emiInfo = findViewById(R.id.emi_info);
        principalEt = findViewById(R.id.principal_et);
        interestRateEt =  findViewById(R.id.interest_et);
        installmentsEt = findViewById(R.id.installment_et);
        Button calculateEmi = findViewById(R.id.calculate_emi);
        calculateEmi.setOnClickListener(calculateEmiOnClickListener);
    }

    private View.OnClickListener calculateEmiOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(validate()){
                double principal = Double.parseDouble(principalEt.getText().toString().trim());
                double interestRate = Double.parseDouble(interestRateEt.getText().toString().trim());
                int installments = Integer.parseInt(installmentsEt.getText().toString().trim());
                Loan loan = new Loan(principal, interestRate, installments);
                double emi = loan.calculateEMI();
                emiInfo.setText(String.format("Your installment amount is: Rs. %s", new DecimalFormat("#.##").format(emi)));
            }
        }
    };

    private boolean validate(){
        boolean isValidInput = true;

        String principal = principalEt.getText().toString().trim();
        String interestRate = interestRateEt.getText().toString().trim();
        String installment = installmentsEt.getText().toString().trim();

        if(principal.isEmpty()){
            principalEt.setError("Loan amount is left empty");
            isValidInput = false;
        }else{
            try{
                if(Double.parseDouble(principal) <= 0){
                    principalEt.setError("Principal cannot be less then or equals to zero");
                    isValidInput = false;
                }
            }catch (NumberFormatException e){
                principalEt.setError("Please provide valid number for principal");
                isValidInput = false;
            }
        }

        if(interestRate.isEmpty()){
            interestRateEt.setError("Interest rate is left empty");
            isValidInput = false;
        }else{
            try{
                if(Double.parseDouble(interestRate) < 0 && Double.parseDouble(interestRate) > 100){
                    interestRateEt.setError("Interest rate cannot be less then zero and greater then hundred");
                    isValidInput = false;
                }
            }catch (NumberFormatException e){
                interestRateEt.setError("Please provide valid number for interest rate");
                isValidInput = false;
            }
        }


        if(installment.isEmpty()){
            installmentsEt.setError("Number of installment is left empty");
            isValidInput = false;
        }else{
            try{
                if(Integer.parseInt(installment) < 0){
                    installmentsEt.setError("Installments cannot be less then zero");
                    isValidInput = false;
                }
            }catch (NumberFormatException e){
                installmentsEt.setError("Please provide valid number for installment");
                isValidInput = false;
            }
        }
        return  isValidInput;
    }
}
