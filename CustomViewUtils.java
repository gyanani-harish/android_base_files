package gyanani.harish.myandroidbasefiles.android_base_files;

import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Field;

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

    public static class Picker {
        public static void setDividerColor(DatePicker datePicker,@ColorRes int color) {
            setDayDividerColor(datePicker,color);
            setMonthDividerColor(datePicker,color);
            setYearDividerColor(datePicker,color);
        }

        private static void setMonthDividerColor(DatePicker datePicker,@ColorRes int color) {
            NumberPicker monthNumberPicker = getMonthNumberPickerView(datePicker);
            setDividerColor(monthNumberPicker,color);
        }

        private static void setYearDividerColor(DatePicker datePicker,@ColorRes int color) {
            NumberPicker yearNumberPicker = getYearNumberPickerView(datePicker);
            setDividerColor(yearNumberPicker,color);
        }

        public static void setDayDividerColor(DatePicker datePicker,@ColorRes int color) {
            NumberPicker dayNumberPicker = getDayNumberPickerView(datePicker);
            setDividerColor(dayNumberPicker,color);
        }

        public static void setDividerColor(NumberPicker numberPicker, @ColorRes int color) {
            Field[] pickerFields = NumberPicker.class.getDeclaredFields();
            for (Field pf : pickerFields) {
                if (pf.getName().equals("mSelectionDivider")) {
                    pf.setAccessible(true);
                    try {

                        pf.set(numberPicker, new ColorDrawable(
                                ContextCompat.getColor(numberPicker.getContext(), color)));
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (Resources.NotFoundException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }

        public static void setTextColor(DatePicker datePicker, @ColorRes int color) {
            setDayTextColor(datePicker, color);
            setMonthTextColor(datePicker, color);
            setYearTextColor(datePicker, color);
        }

        public static void setDayTextColor(DatePicker datePicker, @ColorRes int color) {
            NumberPicker dayNumberPicker = getDayNumberPickerView(datePicker);
            if (dayNumberPicker != null) {
                setNumberPickerTextColor(dayNumberPicker, color);
            }

        }

        public static void setMonthTextColor(DatePicker datePicker, @ColorRes int color) {
            NumberPicker monthNumberPicker = getMonthNumberPickerView(datePicker);
            if (monthNumberPicker != null) {
                setNumberPickerTextColor(monthNumberPicker, color);
            }
        }

        public static void setYearTextColor(DatePicker datePicker,
                                            @ColorRes int color) {
            NumberPicker yearNumberPicker = getYearNumberPickerView(datePicker);
            if (yearNumberPicker != null) {
                setNumberPickerTextColor(yearNumberPicker, color);
            }
        }

        public static NumberPicker getDayNumberPickerView(DatePicker datePicker) {
            int dayId = getDayId();
            if (dayId != 0) {
                NumberPicker numberPicker =
                        (NumberPicker) datePicker.findViewById(dayId);
                if (numberPicker != null) {
                    return numberPicker;
                } else {
                    log("day number picker view not found in date picker");
                }
            }
            return null;
        }

        public static NumberPicker getMonthNumberPickerView(DatePicker datePicker) {

            int dayId = getMonthId();
            if (dayId != 0) {
                NumberPicker numberPicker =
                        (NumberPicker) datePicker.findViewById(dayId);
                if (numberPicker != null) {
                    return numberPicker;
                } else {
                    log("month number picker view not found in date picker");
                }
            }
            return null;
        }

        public static NumberPicker getYearNumberPickerView(DatePicker datePicker) {

            int dayId = getYearId();
            if (dayId != 0) {
                NumberPicker numberPicker =
                        (NumberPicker) datePicker.findViewById(dayId);
                if (numberPicker != null) {
                    return numberPicker;
                } else {
                    log("year number picker view not found in date picker");
                }
            }
            return null;
        }

        public static int getYearId() {
            Resources system = Resources.getSystem();
            int y = system.getIdentifier("year", "id", "android");
            if (y == 0) {
                log("wrong year id datePicker");
            }
            return y;
        }

        public static int getMonthId() {
            Resources system = Resources.getSystem();
            int m = system.getIdentifier("month", "id", "android");
            if (m == 0) {
                log("wrong month id datePicker");
            }
            return m;
        }

        public static int getDayId() {
            Resources system = Resources.getSystem();
            int d = system.getIdentifier("day", "id", "android");
            if (d == 0) {
                log("wrong day id datePicker");
            }
            return d;
        }

        public static void setNumberPickerTextColor(NumberPicker number_picker, @ColorRes int colorRes) {
            final int count = number_picker.getChildCount();
            final int color = ContextCompat.getColor(
                    number_picker.getContext(), colorRes);
            for (int i = 0; i < count; i++) {
                View child = number_picker.getChildAt(i);

                try {
                    Field wheelpaint_field = number_picker.getClass().getDeclaredField("mSelectorWheelPaint");
                    wheelpaint_field.setAccessible(true);

                    ((Paint) wheelpaint_field.get(number_picker)).setColor(color);
                    ((EditText) child).setTextColor(color);
                    number_picker.invalidate();
                } catch (NoSuchFieldException e) {
                    log(e);
                } catch (IllegalAccessException e) {
                    log(e);
                }
            }
        }

    }
}
