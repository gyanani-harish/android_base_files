package gyanani.harish.myandroidbasefiles.android_base_files.custom_views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;


public class AppRecyclerView extends RecyclerView {
    public AppRecyclerView(Context context) {
        super(context);
    }

    public AppRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AppRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
