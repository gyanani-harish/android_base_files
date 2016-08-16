package gyanani.harish.myandroidbasefiles.android_base_files.custom_views;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;

import gyanani.harish.myandroidbasefiles.R;

public class AppButton extends Button {
    public AppButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public AppButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AppButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null && !isInEditMode()) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.AppButton);
            String fontName = a.getString(R.styleable.AppButton_buttonFont);
            if (fontName != null) {
                Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/" + fontName);
                setTypeface(myTypeface);
            }
            a.recycle();
        }
    }

    public String getTextString() {
        if (getText() != null)
            return getText().toString().trim();
        return "";
    }
}