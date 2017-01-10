package custom_views;


import android.content.res.Resources;
import android.support.annotation.ColorRes;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import utils.PickerUtils;

import static utils.DiskLogUtils.log;

public class CustomTimePicker {

    public static void setDividerColor(TimePicker timePicker, @ColorRes int color) {
        setHourDividerColor(timePicker, color);
        setMinuteDividerColor(timePicker, color);
        setAMPMDividerColor(timePicker, color);
    }


    public static void setTextColor(TimePicker timePicker, @ColorRes int color) {
        setHourTextColor(timePicker, color);
        setMinuteTextColor(timePicker, color);
        setAMPMTextColor(timePicker, color);
    }


    public static void setHourDividerColor(TimePicker timePicker, @ColorRes int color) {
        NumberPicker monthNumberPicker = getHourNumberPickerView(timePicker);
        PickerUtils.setDividerColor(monthNumberPicker, color);
    }

    public static void setMinuteDividerColor(TimePicker timePicker, @ColorRes int color) {
        NumberPicker yearNumberPicker = getMinuteNumberPickerView(timePicker);
        PickerUtils.setDividerColor(yearNumberPicker, color);
    }

    public static void setHourTextColor(TimePicker timePicker, @ColorRes int color) {
        NumberPicker hourNumberPicker = getHourNumberPickerView(timePicker);
        if (hourNumberPicker != null) {
            PickerUtils.setNumberPickerTextColor(hourNumberPicker, color);
        }

    }

    public static void setMinuteTextColor(TimePicker timePicker, @ColorRes int color) {
        NumberPicker minuteNumberPicker = getMinuteNumberPickerView(timePicker);
        if (minuteNumberPicker != null) {
            PickerUtils.setNumberPickerTextColor(minuteNumberPicker, color);
        }

    }

    public static void setAMPMTextColor(TimePicker timePicker, @ColorRes int color) {
        NumberPicker amPmNumberPicker = getAMPMNumberPickerView(timePicker);
        if (amPmNumberPicker != null) {
            PickerUtils.setNumberPickerTextColor(amPmNumberPicker, color);
        }
    }

    private static void setAMPMDividerColor(TimePicker timePicker, @ColorRes int color) {
        NumberPicker ampmNumberPicker = getAMPMNumberPickerView(timePicker);
        PickerUtils.setDividerColor(ampmNumberPicker, color);
    }

    private static NumberPicker getAMPMNumberPickerView(TimePicker timePicker) {
        int ampmId = getAMPMId();
        if (ampmId != 0) {
            NumberPicker numberPicker =
                    (NumberPicker) timePicker.findViewById(ampmId);
            if (numberPicker != null) {
                return numberPicker;
            } else {
                log("AM PM number picker view not found in time picker");
            }
        }
        return null;

    }

    private static NumberPicker getHourNumberPickerView(TimePicker timePicker) {

        int hourId = getHourId();
        if (hourId != 0) {
            NumberPicker numberPicker =
                    (NumberPicker) timePicker.findViewById(hourId);
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


    private static NumberPicker getMinuteNumberPickerView(TimePicker timePicker) {

        int minuteId = getMinuteId();
        if (minuteId != 0) {
            NumberPicker numberPicker =
                    (NumberPicker) timePicker.findViewById(minuteId);
            if (numberPicker != null) {
                return numberPicker;
            } else {
                log("minute number picker view not found in time picker");
            }
        }
        return null;
    }

    public static int getAMPMId() {
        Resources system = Resources.getSystem();
        int d = system.getIdentifier("amPm", "id", "android");
        if (d == 0) {
            log("wrong amPm id timePicker");
        }
        return d;
    }
}
