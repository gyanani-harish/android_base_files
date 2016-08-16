package gyanani.harish.myandroidbasefiles.android_base_files.custom_views;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.widget.EditText;

import gyanani.harish.myandroidbasefiles.R;


public class AppEditText extends EditText {
    public AppEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AppEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AppEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.AppButton);
            String fontName = a.getString(R.styleable.AppButton_buttonFont);
            if (fontName != null) {
                Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/" + fontName);
                setTypeface(myTypeface);
            }
            a.recycle();
        }
    }

    public AppEditText(Context context) {
        super(context);
    }

    public String getTextString() {
        if (getText() != null)
            return getText().toString().trim();
        return "";
    }

    public void correctPasswordHintFont() {
        setTypeface(Typeface.DEFAULT);
        setTransformationMethod(new PasswordTransformationMethod());
    }
}
