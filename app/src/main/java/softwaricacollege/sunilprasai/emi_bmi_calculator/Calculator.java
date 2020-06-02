package softwaricacollege.sunilprasai.emi_bmi_calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Calculator extends AppCompatActivity {
    private EditText result;
    private EditText newNumber;
    private TextView operation;

    //variable to hold operands and type of calculations.
    private Double operand1 = null;
    private Double operand2 = null;
    private String pendingOperation = "=";


    //index name for saving the state
    public static final String STATE_PENDING_OPERATION = "PendingOperation";
    public static final String STATE_OPERAND1 = "Operand1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        result =  findViewById(R.id.result);
        newNumber =  findViewById(R.id.newNumber);
        operation =  findViewById(R.id.operation);

        Button button0 =  findViewById(R.id.button0);
        Button button1 =  findViewById(R.id.button1);
        Button button2 =  findViewById(R.id.button2);
        Button button3 =  findViewById(R.id.button3);
        Button button4 =  findViewById(R.id.button4);
        Button button5 =  findViewById(R.id.button5);
        Button button6 =  findViewById(R.id.button6);
        Button button7 =  findViewById(R.id.button7);
        Button button8 =  findViewById(R.id.button8);
        Button button9 =  findViewById(R.id.button9);
        Button buttonDot =  findViewById(R.id.buttonDot);

        Button buttonDivide =  findViewById(R.id.buttonDivide);
        Button buttonMultiply =  findViewById(R.id.buttonMultiply);
        Button buttonMinus =  findViewById(R.id.buttonMinus);
        Button buttonPlus =  findViewById(R.id.buttonPlus);
        Button buttonEquals=  findViewById(R.id.buttonEquals);

        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Button b =  (Button) v;
                newNumber.append(b.getText().toString());
            }
        };

        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttonDot.setOnClickListener(listener);

        View.OnClickListener opListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Button b =  (Button) v;
                String op = b.getText().toString();

                try {
                    Double value = Double.valueOf(newNumber.getText().toString());
                    performOperation(value, op);

                }catch (NumberFormatException e){
                    newNumber.setText("");
                }

                pendingOperation = op;
                operation.setText(op);
            }
        };

        buttonEquals.setOnClickListener(opListener);
        buttonDivide.setOnClickListener(opListener);
        buttonMultiply.setOnClickListener(opListener);
        buttonPlus.setOnClickListener(opListener);
        buttonMinus.setOnClickListener(opListener);


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(STATE_PENDING_OPERATION, pendingOperation);
        if(operand1 != null){
            outState.putDouble(STATE_OPERAND1, operand1);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pendingOperation = savedInstanceState.getString(STATE_PENDING_OPERATION);
        operand1 = savedInstanceState.getDouble(STATE_OPERAND1);
        operation.setText(pendingOperation);
    }

    private void performOperation(Double value, String op){
        if(null==operand1){
            operand1 = value;
        }else{
            operand2 = value;

            if(pendingOperation.equals("\"\"")){
                pendingOperation = op;
            }

            switch (pendingOperation){
                case "=":
                    operand1 = operand2;
                    break;

                case "/":
                    if(operand2 == 0){
                        operand1 = 0.0;
                    }else{
                        operand1 /= operand2;
                    }
                    break;
                case "*":
                    operand1 *= operand2;
                    break;

                case "+":
                    operand1 += operand2;
                    break;

                case "-":
                    operand1 -= operand2;
                    break;
            }

        }

        result.setText(String.valueOf(operand1));
        newNumber.setText("");
    }
}
