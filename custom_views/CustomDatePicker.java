package custom_views;


import android.content.res.Resources;
import android.support.annotation.ColorRes;
import android.widget.DatePicker;
import android.widget.NumberPicker;

import utils.PickerUtils;

import static utils.DiskLogUtils.log;

public class CustomDatePicker {

    private static int getYearId() {
        Resources system = Resources.getSystem();
        int y = system.getIdentifier("year", "id", "android");
        if (y == 0) {
            log("wrong year id datePicker");
        }
        return y;
    }

    private static int getMonthId() {
        Resources system = Resources.getSystem();
        int m = system.getIdentifier("month", "id", "android");
        if (m == 0) {
            log("wrong month id datePicker");
        }
        return m;
    }

    private static int getDayId() {
        Resources system = Resources.getSystem();
        int d = system.getIdentifier("day", "id", "android");
        if (d == 0) {
            log("wrong day id datePicker");
        }
        return d;
    }
    private static NumberPicker getMonthNumberPickerView(DatePicker datePicker) {

        int monthId = getMonthId();
        if (monthId != 0) {
            NumberPicker numberPicker =
                    (NumberPicker) datePicker.findViewById(monthId);
            if (numberPicker != null) {
                return numberPicker;
            } else {
                log("month number picker view not found in date picker");
            }
        }
        return null;
    }

    private static NumberPicker getYearNumberPickerView(DatePicker datePicker) {

        int yearId = getYearId();
        if (yearId != 0) {
            NumberPicker numberPicker =
                    (NumberPicker) datePicker.findViewById(yearId);
            if (numberPicker != null) {
                return numberPicker;
            } else {
                log("year number picker view not found in date picker");
            }
        }
        return null;
    }

    public static void setDividerColor(DatePicker datePicker, @ColorRes int color) {
        setDayDividerColor(datePicker,color);
        setMonthDividerColor(datePicker,color);
        setYearDividerColor(datePicker,color);
    }
    public static void setTextColor(DatePicker datePicker, @ColorRes int color) {
        setDayTextColor(datePicker, color);
        setMonthTextColor(datePicker, color);
        setYearTextColor(datePicker, color);
    }

    public static void setDayTextColor(DatePicker datePicker, @ColorRes int color) {
        NumberPicker dayNumberPicker = getDayNumberPickerView(datePicker);
        if (dayNumberPicker != null) {
            PickerUtils.setNumberPickerTextColor(dayNumberPicker, color);
        }

    }

    public static void setMonthTextColor(DatePicker datePicker, @ColorRes int color) {
        NumberPicker monthNumberPicker = getMonthNumberPickerView(datePicker);
        if (monthNumberPicker != null) {
            PickerUtils.setNumberPickerTextColor(monthNumberPicker, color);
        }
    }

    public static void setYearTextColor(DatePicker datePicker,
                                        @ColorRes int color) {
        NumberPicker yearNumberPicker = getYearNumberPickerView(datePicker);
        if (yearNumberPicker != null) {
            PickerUtils.setNumberPickerTextColor(yearNumberPicker, color);
        }
    }

    private static NumberPicker getDayNumberPickerView(DatePicker datePicker) {
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
    public static void setMonthDividerColor(DatePicker datePicker,@ColorRes int color) {
        NumberPicker monthNumberPicker = getMonthNumberPickerView(datePicker);
        PickerUtils.setDividerColor(monthNumberPicker,color);
    }

    public static void setYearDividerColor(DatePicker datePicker,@ColorRes int color) {
        NumberPicker yearNumberPicker = getYearNumberPickerView(datePicker);
        PickerUtils.setDividerColor(yearNumberPicker,color);
    }

    public static void setDayDividerColor(DatePicker datePicker, @ColorRes int color) {
        NumberPicker dayNumberPicker = getDayNumberPickerView(datePicker);
        PickerUtils.setDividerColor(dayNumberPicker,color);
    }
}
