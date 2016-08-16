package gyanani.harish.myandroidbasefiles.android_base_files;

import android.support.annotation.NonNull;

import java.util.Calendar;

public class DateTimeUtils extends Utils {
    /**
     * @return
     */
    public static int getCurrentMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1/*calendar months starts from 0 to 11*/;
    }

    /**
     * @param month
     * @return
     */
    @NonNull
    public static String getMonthWithPrefix(int month) {
        return month < 10 ? "0" : "";
    }

    /**
     * @return
     */
    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     *
     * @return
     */
    public static int getCurrentDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }
}
