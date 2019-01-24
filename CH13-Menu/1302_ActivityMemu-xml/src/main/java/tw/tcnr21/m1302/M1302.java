package tw.tcnr21.m1302;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;


public class M1302 extends AppCompatActivity {

    private static final int
            MENU_MUSIC             = Menu.FIRST,
            MENU_PLAY_MUSIC = Menu.FIRST + 1,
            MENU_STOP_PLAYING_MUSIC = Menu.FIRST + 2,
            MENU_ABOUT            = Menu.FIRST + 3,
            MENU_EXIT                  = Menu.FIRST + 4;
    private MediaPlayer music;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m1302);
        setupViewComponent();
    }

    @Override
    protected void onDestroy() {
        if(music.isPlaying())music.stop();
        super.onDestroy();
    }

    private void setupViewComponent() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_playmusic:
                music=MediaPlayer.create(M1302.this,R.raw.song);
                music.start();
                break;
            case R.id.menu_stopmusic:
                if(music.isPlaying())music.stop();
                break;
            case R.id.menu_about:
                new AlertDialog.Builder(M1302.this)
                        .setTitle(getString(R.string.m1302_menu_about))
                        .setMessage(getString(R.string.m1302_menu_message))
                        .setCancelable(false)
                        .setIcon(android.R.drawable.star_big_on)
                        .setPositiveButton(getString(R.string.m1302_menu_ok),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                        .show();
                break;
            case R.id.action_setting:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
