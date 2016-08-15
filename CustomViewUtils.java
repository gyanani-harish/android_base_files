package gyanani.harish.myandroidbasefiles.android_base_files;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import gyanani.harish.myandroidbasefiles.android_base_files.custom_views.AppButton;
import gyanani.harish.myandroidbasefiles.android_base_files.custom_views.AppEditText;
import gyanani.harish.myandroidbasefiles.android_base_files.custom_views.AppRecyclerView;
import gyanani.harish.myandroidbasefiles.android_base_files.custom_views.AppTextView;
import gyanani.harish.myandroidbasefiles.android_base_files.custom_views.CustomSpinner;

/**
 * Created by a on 8/15/2016.
 */
public class CustomViewUtils extends Utils {

    public static AppEditText getAppEditText(@NonNull View contentView, @IdRes int id) {
        View view = getView(contentView, id);
        if (view instanceof AppEditText)
            return (AppEditText) view;
        else if (view instanceof EditText)
            throw new IllegalArgumentException("this view is a edittext insteadof AppEditText");
        else if (view instanceof TextView)
            throw new IllegalArgumentException("this view is a textview insteadof AppEditText");
        else
            throw new IllegalArgumentException("this view is not a AppEditText");
    }
    public static CustomSpinner getAppSpinner(View contentView, int id) {
        View view = getView(contentView, id);
        if (view instanceof CustomSpinner)
            return (CustomSpinner) view;
        else if (view instanceof Spinner)
            throw new IllegalArgumentException("this view is a spinner insteadof CustomSpinner");
        else
            throw new IllegalArgumentException("this view is not a AppEditText");
    }
    public static AppRecyclerView getAppRecyclerView(@NonNull View contentView, @IdRes int id) {
        View view = getView(contentView, id);
        if (view instanceof AppRecyclerView) {
            AppRecyclerView appRecyclerView = (AppRecyclerView) view;
            appRecyclerView.setLayoutManager(new LinearLayoutManager(contentView.getContext()));
            return appRecyclerView;
        } else if (view instanceof RecyclerView)
            throw new IllegalArgumentException("this view is a RecyclerView insteadof AppRecyclerView");
        else
            throw new IllegalArgumentException("this view is not a button ");
    }
    public static AppButton getAppButton(@NonNull View contentView, @IdRes int id) {
        View view = getView(contentView, id);
        if (view instanceof AppButton)
            return (AppButton) view;
        else if (view instanceof Button)
            throw new IllegalArgumentException("this view is a button insteadof AppButton");
        else if (view instanceof TextView)
            throw new IllegalArgumentException("this view is a textview insteadof AppButton");
        else
            throw new IllegalArgumentException("this view is not a button ");
    }
    public static AppTextView getAppTextView(@NonNull View contentView, @IdRes int id) {
        View view = getView(contentView, id);
        if (view instanceof AppTextView)
            return (AppTextView) view;
        else if (view instanceof TextView)
            throw new IllegalArgumentException("this view is a textview insteadof AppEditText");
        else
            throw new IllegalArgumentException("this view is not a AppEditText");
    }
}
