package tw.tcnr21.m1301;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import android.app.AlertDialog;
import android.content.DialogInterface;



public class M1301 extends AppCompatActivity {

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
        setContentView(R.layout.m1301);
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
        //第一層
        SubMenu subMenu=menu.addSubMenu(0,MENU_MUSIC,0,getString(R.string.m1301_menu_music))
                .setIcon(android.R.drawable.ic_media_ff);
        menu.add(0,MENU_ABOUT,1,getString(R.string.m1301_menu_about))
                .setIcon(android.R.drawable.ic_dialog_info);
        menu.add(0,MENU_EXIT,2,getString(R.string.action_settings))
                .setIcon(android.R.drawable.ic_menu_close_clear_cancel);

        //第二層
        subMenu.add(0,MENU_PLAY_MUSIC,0,getString(R.string.m1301_menu_playmusic));
        subMenu.add(0,MENU_STOP_PLAYING_MUSIC,1,getString(R.string.m1301_menu_stopmusic));

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case MENU_PLAY_MUSIC:
                music=MediaPlayer.create(M1301.this,R.raw.song);
                music.start();
                break;
            case MENU_STOP_PLAYING_MUSIC:
                if(music.isPlaying())music.stop();
                break;
            case MENU_ABOUT:
                new AlertDialog.Builder(M1301.this)
                        .setTitle(getString(R.string.m1301_menu_about))
                        .setMessage(getString(R.string.m1301_menu_message))
                        .setCancelable(false)
                        .setIcon(android.R.drawable.star_big_on)
                        .setPositiveButton(getString(R.string.m1301_menu_ok),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                        .show();
                break;
            case MENU_EXIT:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
