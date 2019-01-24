package tw.tcnr21.m0706;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.widget.TextView;

import java.net.URI;

public class M0706 extends AppCompatActivity {

    private TextView t001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0706);
        setupViewComponent();
    }

    private void setupViewComponent() {
        t001=(TextView)findViewById(R.id.m0706_t001);
        SpannableString sp=new SpannableString(getString(R.string.m0706_t001));
        sp.setSpan(new URLSpan("http://www.google.com.tw"), 5,11,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        sp.setSpan(new BackgroundColorSpan(Color.MAGENTA),12,14,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        sp.setSpan(new ForegroundColorSpan(Color.YELLOW),15,20,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        sp.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),31,34,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ImageSpan imageSpan=new ImageSpan(this,R.drawable.a123);
        sp.setSpan(imageSpan,18,21,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
        ImageSpan imageSpans=new ImageSpan(this,R.drawable.a123);
        sp.setSpan(imageSpans,22,23,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
        //Spanned.SPAN_EXCLUSIVE_EXCLUSIVE(前後都不包括)、
        // Spanned.SPAN_INCLUSIVE_EXCLUSIVE(前麵包括，後面不包括)、
        // Spanned.SPAN_EXCLUSIVE_INCLUSIVE(前面不包括，後麵包括)、
        // Spanned.SPAN_INCLUSIVE_INCLUSIVE(前後都包括)。



        t001.setText(sp);
        t001.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
