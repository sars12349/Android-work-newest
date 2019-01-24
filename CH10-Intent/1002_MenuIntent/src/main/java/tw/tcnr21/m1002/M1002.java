package tw.tcnr21.m1002;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.Contacts;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.os.EnvironmentCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class M1002 extends AppCompatActivity implements View.OnClickListener {

    private Button btnweb,btnmp3,btnpicture,btnphone,btnemail,btnfriend,btnmsg,btnaddress,btncamera,btnsetting;
    private Uri uri;
    private Intent it;
    private File file;
    private String[] sdcardpath = null;
    private MediaPlayer music;
    private String TAG="tcnr21=>";
    private TextView myname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m1002);
        setupViewComponent();

        sdcardpath = getExternalStorageDirectories();

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


    }

    private void setupViewComponent() {
        btnweb=(Button)findViewById(R.id.m1002_b001);
        btnmp3=(Button)findViewById(R.id.m1002_b002);
        btnpicture=(Button)findViewById(R.id.m1002_b003);
        btnphone=(Button)findViewById(R.id.m1002_b004);
        btnmsg=(Button)findViewById(R.id.m1002_b005);
        btnaddress=(Button)findViewById(R.id.m1002_b006);
        btnemail=(Button)findViewById(R.id.m1002_b007);
        btnfriend=(Button)findViewById(R.id.m1002_b008);
        btncamera=(Button)findViewById(R.id.m1002_b009);
        btnsetting=(Button)findViewById(R.id.m1002_b010);
        myname=(TextView)findViewById(R.id.myname);

        btnweb.setOnClickListener(this);
        btnmp3.setOnClickListener(this);
        btnpicture.setOnClickListener(this);
        btnphone.setOnClickListener(this);
        btnmsg.setOnClickListener(this);
        btnaddress.setOnClickListener(this);
        btnemail.setOnClickListener(this);
        btnfriend.setOnClickListener(this);
        btncamera.setOnClickListener(this);
        btnsetting.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_m1002,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item01:
                intentsel(1);
                break;
            case R.id.item02:
                intentsel(2);
                break;
            case R.id.item03:
                intentsel(3);
                break;
            case R.id.item04:
                intentsel(4);
                break;
            case R.id.item05:
                intentsel(5);
                break;
            case R.id.item06:
                intentsel(6);
                break;
            case R.id.item07:
                intentsel(7);
                break;
            case R.id.item08:
                intentsel(8);
                break;
            case R.id.item09:
                intentsel(9);
                break;
            case R.id.item10:
                intentsel(10);
                break;
            case R.id.action_settings:
                this.finish();
                break;
        }      

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.m1002_b001:  //打開網頁
                intentsel(1);
                break;
            case R.id.m1002_b002:  //播放mp3
                intentsel(2);
                break;
            case R.id.m1002_b003:  //顯示圖片
                intentsel(3);
                break;
            case R.id.m1002_b004: //到撥打電話畫面
                intentsel(4);
                break;
            case R.id.m1002_b005:  //到傳簡訊畫面
                intentsel(5);
            case R.id.m1002_b006: //地址顯示
                intentsel(6);
            case R.id.m1002_b007: //寄email
                intentsel(7);
                break;
            case R.id.m1002_b008:  //開啟聯絡人
                intentsel(8);
                break;
            case R.id.m1002_b009:  //打開相機
                intentsel(9);
                break;
            case R.id.m1002_b010:  //開啟設定
                intentsel(10);
                break;

        }
        
        
    }

    private void intentsel(int i) {

        switch (i)
        {
            case 1: //打開網頁
                uri=Uri.parse("http://developer.android.com/");
                it=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(it);
                break;
            case 2:  //播放mp3
                it = new Intent();
                it.setAction(android.content.Intent.ACTION_VIEW);
                try {
                    uri = Uri.parse(sdcardpath[0] + "/tcnr/song.mp3");//替換成audiopath
                    it.setDataAndType(uri, "audio/*");
                    startActivity(it);
                } catch (Exception e) {
                    Toast.makeText(getApplication(), "file not found", Toast.LENGTH_LONG).show();
                }
                break;
            case 3:  //顯示圖片
                it = new Intent();
                file = new File(sdcardpath[0] + "/tcnr/image.png");
                it.setDataAndType(Uri.fromFile(file), "image/*");
                try {
                    startActivity(it);
                } catch (Exception e) {
                    Toast.makeText(getApplication(), "file not found", Toast.LENGTH_LONG).show();
                }
                break;
            case 4: //到撥打電話畫面
                uri=Uri.parse("tel:0988499513");
                it=new Intent(Intent.ACTION_DIAL,uri);
                startActivity(it);
                break;
            case 5:  //到傳簡訊畫面
                uri = Uri.parse("smsto:0800000123");
                it = new Intent(Intent.ACTION_SENDTO, uri);
                it.putExtra("sms_body", "The SMS text");
                startActivity(it);
            case 6: //地址顯示
                uri=Uri.parse("geo:38.899533,-77.036476");
                it =new Intent(Intent.ACTION_VIEW,uri);
                startActivity(it);
                break;
            case 7: //寄email
                uri = Uri.parse("mailto:sars12349@gmail.com");
                it = new Intent(Intent.ACTION_SENDTO, uri);
                startActivity(it);
                break;
            case 8:  //開啟聯絡人
                it = new Intent(Intent.ACTION_VIEW, Contacts.People.CONTENT_URI);
                startActivity(it);
                break;
            case 9:  //打開相機
                it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(it, 0);
                break;
            case 10:  //開啟設定
                it= new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                startActivityForResult(it, 0);
                break;
            case R.id.action_settings:
                break;
        }
    }



    public String[] getExternalStorageDirectories() {

        List<String> results = new ArrayList<>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { //Method 1 for KitKat & above
            File[] externalDirs = getExternalFilesDirs(null);

            for (File file : externalDirs) {
                String path = file.getPath().split("/Android")[0];

                boolean addPath = false;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    addPath = Environment.isExternalStorageRemovable(file);
                } else {
                    addPath = Environment.MEDIA_MOUNTED.equals(EnvironmentCompat.getStorageState(file));
                }

                if (addPath) {
                    results.add(path);
                }
            }
        }

        if (results.isEmpty()) { //Method 2 for all versions
            // better variation of: http://stackoverflow.com/a/40123073/5002496
            String output = "";
            try {
                final Process process = new ProcessBuilder().command("mount | grep /dev/block/vold")
                        .redirectErrorStream(true).start();
                process.waitFor();
                final InputStream is = process.getInputStream();
                final byte[] buffer = new byte[1024];
                while (is.read(buffer) != -1) {
                    output = output + new String(buffer);
                }
                is.close();
            } catch (final Exception e) {
                e.printStackTrace();
            }
            if (!output.trim().isEmpty()) {
                String devicePoints[] = output.split("\n");
                for (String voldPoint : devicePoints) {
                    results.add(voldPoint.split(" ")[2]);
                }
            }
        }

        //Below few lines is to remove paths which may not be external memory card, like OTG (feel free to comment them out)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (int i = 0; i < results.size(); i++) {
                if (!results.get(i).toLowerCase().matches(".*[0-9a-f]{4}[-][0-9a-f]{4}")) {
                    Log.d(TAG, results.get(i) + " might not be extSDcard");
                    results.remove(i--);
                }
            }
        } else {
            for (int i = 0; i < results.size(); i++) {
                if (!results.get(i).toLowerCase().contains("ext") && !results.get(i).toLowerCase().contains("sdcard")) {
                    Log.d(TAG, results.get(i) + " might not be extSDcard");
                    results.remove(i--);
                }
            }
        }

        String[] storageDirectories = new String[results.size()];
        for (int i = 0; i < results.size(); ++i) storageDirectories[i] = results.get(i);

        return storageDirectories;
    }


}
