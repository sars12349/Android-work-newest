package tw.tcnr21.m0607;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class M0505 extends AppCompatActivity {

    private Button btnAddAutoCompleteText,btnClrAutoCompleteText;
    private AutoCompleteTextView autCompTextView;
    private ArrayAdapter<String> adapAutoCompText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0505);
        setupViewCompoent();
    }

    private void setupViewCompoent() {

        btnAddAutoCompleteText=(Button)findViewById(R.id.m0505_b001);
        btnClrAutoCompleteText=(Button)findViewById(R.id.m0505_b002);
        autCompTextView=(AutoCompleteTextView)findViewById(R.id.m0505_a001);

        adapAutoCompText=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line);
        autCompTextView.setAdapter(adapAutoCompText);

        btnAddAutoCompleteText.setOnClickListener(btnAddAutoCompleteTextOn);
        btnAddAutoCompleteText.setOnClickListener(btnClrAutoCompleteTextOn);

        Intent intent=this.getIntent();
        String mode_title=intent.getStringExtra("class_title");
        this.setTitle(mode_title);
    }
private Button.OnClickListener btnAddAutoCompleteTextOn= new Button.OnClickListener() {
    @Override
    public void onClick(View v) {
        String s=autCompTextView.getText().toString();
        adapAutoCompText.add(s);
        autCompTextView.setText("");

    }
};

    private  Button.OnClickListener btnClrAutoCompleteTextOn=new  Button.OnClickListener(){


        @Override
        public void onClick(View v) {
            adapAutoCompText.clear();
        }
    };





}
