package gyanani.harish.myandroidbasefiles.android_base_files;

import android.app.Application;


import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class MyApplication extends Application {

    public static File sExternalStorageDirectory = null;

    @Override
    public void onCreate() {
        super.onCreate();
        sExternalStorageDirectory = getExternalFilesDir(null);


    }


}
