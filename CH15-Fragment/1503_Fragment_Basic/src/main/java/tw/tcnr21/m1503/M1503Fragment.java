package tw.tcnr21.m1503;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class M1503Fragment extends Fragment {

    private View view;
    private Button button;
    private TextView output;
    private EditText txtHeight,txtWeight;

    public M1503Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_m1503,container,false);
        setupViewComponent();
        return inflater.inflate(R.layout.fragment_m1503, container, false);
    }

    private void setupViewComponent() {
            txtHeight = (EditText) view.findViewById(R.id.txtHeight);
            txtWeight = (EditText) view.findViewById(R.id.txtWeight);
            button = (Button) view.findViewById(R.id.button);
            output = (TextView) view.findViewById(R.id.lblOutput);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonClicked(v);
                }
            });
    }

    private void buttonClicked(View v){
        double height, weight, bmi;
        // 取得輸入值
        if (txtHeight.getText().toString().trim().length() == 0 || txtWeight.getText().toString().trim().length() == 0) {
            bmi = 0;
        } else {
            height = Double.parseDouble(txtHeight.getText().toString());
            weight = Double.parseDouble(txtWeight.getText().toString());
            // 計算BMI
            height = height / 100.00;
            bmi = weight / (height * height);
        };
        String result_bmi = null;
        if (bmi == 0) {
            result_bmi = getString(R.string.whnull);//體重及身高必須輸入
        } else {
            String Tbmi = getString(R.string.yourbmi) + String.format("%.4f", bmi);//你的BMI值:
            if (bmi > 25) {
                result_bmi = Tbmi + "\n\n" + getString(R.string.advice_heavy);//你該節食了
            } else if (bmi < 20) {
                result_bmi = Tbmi + "\n\n" + getString(R.string.advice_light);//你該多吃點
            } else {
                result_bmi = Tbmi + "\n\n" + getString(R.string.advice_average);//體型很棒喔
            }
        };
        output.setText(result_bmi);
    };
}

