package tw.tcnr21.m1501;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SecondFragment extends Fragment {
    private static final String MESSAGE = "MESSAGE";
    private String msg;
    // 類別方法來建立SecondFragment物件
    public static SecondFragment newInstance(String msg) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();   //  新增參數
        args.putString(MESSAGE, msg);
        fragment.setArguments(args);
        return fragment;
    }
    public SecondFragment() { } // 空建構
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) { // 如果有參數
            // 取出參數內容
            msg = getArguments().getString(MESSAGE);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 片段元件內容
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        // 取得TextView元件
        TextView output = (TextView) view.findViewById(R.id.lblPutput);
        output.setText(msg);  // 指定TextView元件內容
        return view;
    }
}

