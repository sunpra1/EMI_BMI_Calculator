package softwaricacollege.sunilprasai.emi_bmi_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView startEMICalcImage;
    private ImageView startBMICalcImage;
    private ImageView startCalcImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startEMICalcImage = findViewById(R.id.startEMICalc);
        startBMICalcImage = findViewById(R.id.startBMICalc);
        startCalcImage = findViewById(R.id.startCalc);

        startEMICalcImage.setOnClickListener(this);
        startBMICalcImage.setOnClickListener(this);
        startCalcImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == startEMICalcImage.getId()){
            Intent startEmiCalc = new Intent(getBaseContext(), EmiCalculator.class);
            startActivity(startEmiCalc);
        }else if(v.getId() == startBMICalcImage.getId()){
            Intent startBmiCalc = new Intent(getBaseContext(), BmiCalculator.class);
            startActivity(startBmiCalc);
        }else if(v.getId() == startCalcImage.getId()){
            Intent startCalc = new Intent(getBaseContext(), Calculator.class);
            startActivity(startCalc);
        }
    }
}
