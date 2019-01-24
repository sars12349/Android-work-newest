package tw.tcnr21.m0805;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class M0805 extends AppCompatActivity {

    private SeekBar sb;
    private RatingBar rb;
    private Button b1,b2;
    private TextView tsb,trb1,trb2;
    private MediaPlayer music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0805);
        setupViewComponent();
    }

    private void setupViewComponent() {
        sb=(SeekBar)findViewById(R.id.m0805_seekBar);
        rb=(RatingBar)findViewById(R.id.m0805_ratBar);
        tsb=(TextView)findViewById(R.id.m0805_t001);
        trb1=(TextView)findViewById(R.id.m0805_Rat01);
        trb2=(TextView)findViewById(R.id.m0805_Rat02);
        b1=(Button)findViewById(R.id.m0805_b001);
        b2=(Button)findViewById(R.id.m0805_b002);
        music=MediaPlayer.create(M0805.this,R.raw.attack2);

        sb.setOnSeekBarChangeListener(sbonchangelistener);
        rb.setOnRatingBarChangeListener(rbonchangelistener);
    }

    SeekBar.OnSeekBarChangeListener sbonchangelistener=new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            String s=getString(R.string.m0805_SeekBar);
            tsb.setText(s+Integer.toString(progress));
            b1.getBackground().setAlpha(progress*255/100);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            music.stop();
            music.start();
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            music.pause();
        }
    };
    RatingBar.OnRatingBarChangeListener rbonchangelistener=new RatingBar.OnRatingBarChangeListener() {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            String s=getString(R.string.m0805_RatBar1);
            trb1.setText(s+Float.toString(rating));
            s=getString(R.string.m0805_RatBar2);
            trb2.setText(s+Integer.toString(rb.getProgress()));
            b2.getBackground().setAlpha(rb.getProgress()*255/10);
        }
    };

}
