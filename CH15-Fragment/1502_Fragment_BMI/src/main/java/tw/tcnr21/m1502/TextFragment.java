package tw.tcnr21.m1502;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TextFragment extends Fragment {


    private TextView output;

    public TextFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        output = (TextView) view.findViewById(R.id.lblOutput);
        return view;

    }

    // 活動呼叫的Public方法
    public void changeBMIValue(double bmi) {

        String result_bmi = null;
        if (bmi == 0){
            result_bmi =getString(R.string.whnull);//體重及身高必須輸入
        }else {
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
    }


}
