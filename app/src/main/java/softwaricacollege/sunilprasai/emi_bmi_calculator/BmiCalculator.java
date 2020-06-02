package softwaricacollege.sunilprasai.emi_bmi_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class BmiCalculator extends AppCompatActivity {

    private TextView bmiInfo;
    private EditText weightEt;
    private EditText heightEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);

        bmiInfo = findViewById(R.id.bmi_info);
        weightEt = findViewById(R.id.weight_et);
        heightEt = findViewById(R.id.height_et);
        Button calculateBMI = findViewById(R.id.calculate_bmi);
        calculateBMI.setOnClickListener(calculateBMIOnClickListener);
    }

    private View.OnClickListener calculateBMIOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(validate()){
                double weight = Double.parseDouble(weightEt.getText().toString().trim());
                double height = Double.parseDouble(heightEt.getText().toString().trim());
                double bmi = weight / (Math.pow(height, 2));
                String bmiCategory;
                if (bmi >= 25){
                    bmiCategory = "Overweight";
                }else if(bmi >= 18.5 && bmi <= 24.9 ){
                    bmiCategory = "Healthy/ Normal Weight";
                }else{
                    bmiCategory = "Underweight";
                }

                bmiInfo.setText(String.format(getString(R.string.info_format), new DecimalFormat("#.##").format(bmi), bmiCategory));
            }
        }
    };

    private boolean validate(){
        boolean isValidInput = true;

        String weight = weightEt.getText().toString().trim();
        String height = heightEt.getText().toString().trim();
        if(weight.isEmpty()){
            weightEt.setError("Weight field is left empty");
            isValidInput = false;
        }else{
            try{
                // 635kg is the weight of world's heaviest man Jon Brower Minnoch
                if(Double.parseDouble(weight) > 650){
                    weightEt.setError("Your weight cannot be more then Jon Brower Minnoch (world's heaviest person till date)");
                    isValidInput = false;
                }else if(Double.parseDouble(weight) <= 0){
                    weightEt.setError("Your weight cannot be less then or equals to zero");
                    isValidInput = false;
                }
            }catch(NumberFormatException e){
                    weightEt.setError("Please provide valid number for weight");
                    isValidInput = false;
            }
        }

        if(height.isEmpty()){
            heightEt.setError("Height field is left empty");
            isValidInput = false;
        }else{
            try{
                // 2.51m is the height of world's tallest person (Sultan Kösen).
                if(Double.parseDouble(height) > 2.55d){
                    heightEt.setError("Your height cannot be more then Sultan Kösen (world's tallest person till date)");
                    isValidInput = false;
                }else if(Double.parseDouble(height) <= 0){
                    heightEt.setError("Your height cannot be less then or equals to zero");
                    isValidInput = false;
                }
            }catch(NumberFormatException e){
                heightEt.setError("Please provide valid number for height");
                isValidInput = false;
            }


        }

        return isValidInput;
    }
}
