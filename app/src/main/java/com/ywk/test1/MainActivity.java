package com.ywk.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final  Bitmap printphoto = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888);


        final String dir = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "potato" + File.separator;
        try {
            File folder = new File(dir);
            if(!folder.exists()){
                folder.mkdir();
            }
            File file = new File(dir + "summer" + ".jpg");
            if(file.exists()){
                file.delete();
            }
            if(!file.exists()){
                file.createNewFile();
            }
            final FileOutputStream out = new FileOutputStream(file);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    printphoto.compress(Bitmap.CompressFormat.PNG, 100, out);
                }
            }).start();
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
