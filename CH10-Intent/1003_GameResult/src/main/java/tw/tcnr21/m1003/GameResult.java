package tw.tcnr21.m1003;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GameResult extends AppCompatActivity implements View.OnClickListener {


    private EditText mEdtCountSet,mEdtCountPlayerWin,mEdtCountComWin,mEdtCountDraw;
    private Button btnBackToGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_result);
        setupViewComponent();
        showResult();
    }

    private void setupViewComponent() {
        mEdtCountSet = (EditText)findViewById(R.id.edtCountSet);
        mEdtCountPlayerWin = (EditText)findViewById(R.id.edtCountPlayerWin);
        mEdtCountComWin = (EditText)findViewById(R.id.edtCountComWin);
        mEdtCountDraw = (EditText)findViewById(R.id.edtCountDraw);
        btnBackToGame = (Button)findViewById(R.id.btnBackToGame);

        btnBackToGame.setOnClickListener(this);


    }
    private void showResult() {
        Bundle bundle=this.getIntent().getExtras();

        int iCountSet=bundle.getInt("KEY_COUNT_SET");
        int iCountPlayerWin = bundle.getInt("KEY_COUNT_PLAYER_WIN");
        int iCountComWin = bundle.getInt("KEY_COUNT_COM_WIN");
        int iCountDraw = bundle.getInt("KEY_COUNT_DRAW");

        mEdtCountSet.setText(Integer.toString(iCountSet));
        mEdtCountPlayerWin.setText(Integer.toString(iCountPlayerWin));
        mEdtCountComWin.setText(Integer.toString(iCountComWin));
        mEdtCountDraw.setText(Integer.toString(iCountDraw));


    }

    @Override
    public void onClick(View v) {
        GameResult.this.finish();

    }

}

