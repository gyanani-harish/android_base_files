package custom_views;


import android.content.res.Resources;
import android.support.annotation.ColorRes;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import utils.PickerUtils;

import static utils.DiskLogUtils.log;

public class CustomTimePicker {

    public static void setHourDividerColor(TimePicker timePicker, @ColorRes int color) {
        NumberPicker monthNumberPicker = getHourNumberPickerView(timePicker);
        PickerUtils.setDividerColor(monthNumberPicker, color);
    }

    private static NumberPicker getHourNumberPickerView(TimePicker timePicker) {

        int monthId = getHourId();
        if (monthId != 0) {
            NumberPicker numberPicker =
                    (NumberPicker) timePicker.findViewById(monthId);
            if (numberPicker != null) {
                return numberPicker;
            } else {
                log("hour number picker view not found in time picker");
            }
        }
        return null;
    }

    private static int getHourId() {
        Resources system = Resources.getSystem();
        int m = system.getIdentifier("hour", "id", "android");
        if (m == 0) {
            log("wrong hour id timePicker");
        }
        return m;
    }

    private static int getMinuteId() {
        Resources system = Resources.getSystem();
        int d = system.getIdentifier("minute", "id", "android");
        if (d == 0) {
            log("wrong minute id timePicker");
        }
        return d;
    }

    private static void setMinuteDividerColor(TimePicker timePicker, @ColorRes int color) {
        NumberPicker yearNumberPicker = getMinuteNumberPickerView(timePicker);
        PickerUtils.setDividerColor(yearNumberPicker, color);
    }

    private static NumberPicker getMinuteNumberPickerView(TimePicker timePicker) {

        int yearId = getMinuteId();
        if (yearId != 0) {
            NumberPicker numberPicker =
                    (NumberPicker) timePicker.findViewById(yearId);
            if (numberPicker != null) {
                return numberPicker;
            } else {
                log("minute number picker view not found in time picker");
            }
        }
        return null;
    }

}
