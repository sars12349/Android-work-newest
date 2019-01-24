package tw.tcnr21.m1303;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class M1303 extends AppCompatActivity {

    private static final int
            MENU_MUSIC             = Menu.FIRST,
            MENU_PLAY_MUSIC = Menu.FIRST + 1,
            MENU_STOP_PLAYING_MUSIC = Menu.FIRST + 2,
            MENU_ABOUT            = Menu.FIRST + 3,
            MENU_EXIT                  = Menu.FIRST + 4;
    private MediaPlayer music;
    private RelativeLayout mRelativeLayout;
    private TextView mTxtView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m1303);
        setContentView();
    }

    @Override
    protected void onDestroy() {
        if(music.isPlaying())music.stop();
        super.onDestroy();
    }

    private void setContentView() {
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        registerForContextMenu(mRelativeLayout); //登入一個彈出式MENU
        mTxtView = (TextView) findViewById(R.id.m1303_t001);
        registerForContextMenu(mTxtView); //在註冊一個彈出式MENU
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        if (v==mRelativeLayout){
            if (menu.size()==0){
                MenuInflater inflater=getMenuInflater();
                inflater.inflate(R.menu.main,menu);
            }
        }else if (v==mTxtView){
            MenuInflater inflater=getMenuInflater();
            inflater.inflate(R.menu.content_menu_text_view,menu);
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.menu_playmusic:
                music=MediaPlayer.create(M1303.this,R.raw.song);
                music.start();
                break;
            case R.id.menu_stopmusic:
                if(music.isPlaying())music.stop();
                break;
            case R.id.menu_about:
                new AlertDialog.Builder(M1303.this)
                        .setTitle(getString(R.string.m1303_menu_about))
                        .setMessage(getString(R.string.m1303_menu_message))
                        .setCancelable(false)
                        .setIcon(android.R.drawable.star_big_on)
                        .setPositiveButton(getString(R.string.m1303_menu_ok),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                        .show();
                break;
            case R.id.menuItemAbout:
                new AlertDialog.Builder(M1303.this)
                        .setTitle(getString(R.string.m1303_menu_about))
                        .setMessage(getString(R.string.m1303_menu_message))
                        .setCancelable(false)
                        .setIcon(android.R.drawable.star_big_on)
                        .setPositiveButton(getString(R.string.m1303_menu_ok),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                        .show();
                break;
            case R.id.action_setting:
                finish();
                break;
        }


        return super.onContextItemSelected(item);
    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater=getMenuInflater();
//        inflater.inflate(R.menu.main,menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId())
//        {
//            case R.id.menu_playmusic:
//                music=MediaPlayer.create(M1303.this,R.raw.song);
//                music.start();
//                break;
//            case R.id.menu_stopmusic:
//                if(music.isPlaying())music.stop();
//                break;
//            case R.id.menu_about:
//                new AlertDialog.Builder(M1303.this)
//                        .setTitle(getString(R.string.m1303_menu_about))
//                        .setMessage(getString(R.string.m1303_menu_message))
//                        .setCancelable(false)
//                        .setIcon(android.R.drawable.star_big_on)
//                        .setPositiveButton(getString(R.string.m1303_menu_ok),
//                                new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                    }
//                                })
//                        .show();
//                break;
//            case R.id.action_setting:
//                finish();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }


}
