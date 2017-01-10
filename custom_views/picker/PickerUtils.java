package utils;


import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import java.lang.reflect.Field;

import static utils.DiskLogUtils.log;

public class PickerUtils {
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
}
