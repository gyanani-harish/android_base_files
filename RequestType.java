package gyanani.harish.myandroidbasefiles.android_base_files;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;



@IntDef({gyanani.harish.myandroidbasefiles.android_base_files.RequestType.GET,
        gyanani.harish.myandroidbasefiles.android_base_files.RequestType.POST})
@Retention(RetentionPolicy.SOURCE)
public @interface RequestType {

    public static final int GET = 0;
    public static final int POST = 1;
}