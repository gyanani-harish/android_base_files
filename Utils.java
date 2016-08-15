package gyanani.harish.myandroidbasefiles.android_base_files;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;

import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import gyanani.harish.myandroidbasefiles.R;
import retrofit2.Call;


public class Utils {

    private static ProgressDialog progressDialog;


    public static void openLinkInBrowser(Context context, String url) {
        if (checkValidURL(url)) {
            try {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                context.startActivity(myIntent);
            } catch (ActivityNotFoundException e) {
                log("No application can handle this request."
                        + " Please install a webbrowser");

            }
        }
    }

    /**
     * rules (@link https://creditcardjs.com/credit-card-type-detection)
     *
     * @param accNo
     * @return
     */
    public static
    @CreditCardType
    String getCreditCardType(String accNo) {

        String visaPattern = "^4.*$";
        String masterCardPattern = "^5[0-5].*$";
        String americalExpressPattern = "^3[47].*$";
        String discoverPattern = "^6[0245].*$";
        if (accNo.matches(visaPattern))
            return CreditCardType.VISA;
        else if (accNo.matches(masterCardPattern))
            return CreditCardType.MASTER_CARD;
        else if (accNo.matches(americalExpressPattern))
            return CreditCardType.AMERICAN_EXPRESS;
        else
            return CreditCardType.DISCOVER;
    }



    public static void showMessage(final Context context, final String message) {
        if (context instanceof Activity)
            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                }
            });
        else
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

        log("Context=" + context.getClass().getSimpleName() + "Toast" + message);
    }



    public static Button getButton(@NonNull View contentView, @IdRes int id) {
        View view = getView(contentView, id);
        if (view instanceof Button)
            return (Button) view;
        else if (view instanceof TextView)
            throw new IllegalArgumentException("this view is a textview insteadof Button");
        else
            throw new IllegalArgumentException("this view is not a button");
    }





    public static EditText getEditText(@NonNull View contentView, @IdRes int id) {
        View view = getView(contentView, id);
        if (view instanceof EditText)
            return (EditText) view;
        else if (view instanceof TextView)
            throw new IllegalArgumentException("this view is a textview insteadof EditText");
        else
            throw new IllegalArgumentException("this view is not a edittext");
    }

    public static TextView getTextView(@NonNull View contentView, @IdRes int id) {
        if (contentView == null)
            throw new NullPointerException("contentView is null");

        View view = getView(contentView, id);
        if (view instanceof TextView)
            return (TextView) view;
        else
            throw new IllegalArgumentException("this view is not a textview");
    }

    public static View getView(@NonNull View contentView, @IdRes int id) {
        View view = contentView.findViewById(id);
        if (view == null)
            throw new IllegalArgumentException("wrong id. contentView does not contain this view");
        return view;
    }

    public static float getScreenDensity(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        if (context instanceof Activity) {
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
            return metrics.density;
        } else
            return 0;
    }

    public static int convertDpToPx(Context context, int dps) {
        DisplayMetrics metrics = new DisplayMetrics();
        if (context instanceof Activity) {
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
            return (int) (metrics.density * dps);
        } else
            return 0;
    }

    public static DisplayMetrics getScreenDisplayMetrics(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        if (context instanceof Activity) {
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
            return metrics;
        } else
            return null;
    }

    public static boolean isOrientationPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    public static int getOrientation(Context context) {
        return context.getResources().getConfiguration().orientation;
    }

    public static boolean isValidOTP(@NonNull String otp) {
        return otp.length() > 2;
    }

    public static boolean isValidEmail(String email) {
        return email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidName(String name) {
        return name.length() > 2;
    }

    public static boolean isValidPassword(String password) {
        return password.length() > 5;
    }

    public static boolean isValidPhoneNumber(String phone) {
        return phone != null && Patterns.PHONE.matcher(phone).matches();
    }

    public static boolean isValidAddress(String address) {
        return address.length() > 2;
    }

    public static boolean isValidCountry(String country) {
        return country.length() > 2;
    }

    public static boolean isValidState(String state) {
        return state.length() > 1;
    }

    public static boolean isValidCity(String city) {
        return city.length() > 1;
    }

    public static boolean isValidZipCode(String zip) {
        try {
            Integer.parseInt(zip);
        } catch (Exception e) {
            return false;
        }
        return zip.length() == 6;
    }

    public static boolean isValidFileSize(File file) {
        long length;
        try {
            length = file.length() / 1024;
            System.out.println("File size in kb : " + length);
        } catch (Exception e) {
            return false;
        }
        return length <= 2000;
    }

    public static boolean isValidNumberString(String numberString) {
        try {
            Integer.parseInt(numberString);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean isValidFloatString(String numberString) {
        try {
            Float.parseFloat(numberString);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean isNetworkAvailable(Context context) {
        NetworkInfo activeNetwork = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static String convertStringIntoMD5(String password) {
        StringBuffer MD5Hash = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(password.getBytes());
            byte messageDigest[] = digest.digest();

            MD5Hash = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                MD5Hash.append(h);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            MD5Hash = null;
        }
        if (MD5Hash != null)
            return MD5Hash.toString();
        else
            return null;
    }

    public static void closeKeyboard(Context context, View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void closeKeyBoard(Context context) {
        if (context instanceof Activity) {
            View view = ((Activity) context).getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public static Typeface getTypefaceRobotoBold(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/roboto_bold.TTF");
    }

    public static Typeface getTypefaceRobotoRegular(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/roboto_regular.TTF");
    }

    public static Typeface getTypefaceRobotoLight(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/roboto_light.TTF");
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    public static void log(Object o, boolean doReflection) {
        log(Constants.APP_NAME, o == null ? "object is null"
                : ReflectionToStringBuilder.reflectionToString(o));
    }

    public static void log(String logTag, Throwable t) {
        log(logTag, "throwable=" + ReflectionToStringBuilder.reflectionToString(t), ErrorType.WEBSERVICE_RESPONSE_ERROR);
    }

    public static int getOccurrenceOfSubStringInString(String experience, String subStr) {
        /*int counter=0;
        for( int i=0; i<experience.length(); i++ ) {
            if( experience.charAt(i) == '.' ) {
                counter++;
            }
        }
        return counter;
*/
        return StringUtils.countMatches(experience, subStr);
    }

    public static void log(Exception e) {
        String s;
        if (e == null) {
            s = "Exception is null";
        } else {
            s = android.util.Log.getStackTraceString(e);
        }
        log(Constants.APP_NAME, s, ErrorType.CLIENT_SIDE_ERROR);
    }



    public static void log(Object o) {
        log(Constants.APP_NAME, o == null ? "object is null" : o.toString());
    }

    public static void log(String s, @ErrorType int errorType) {
        log(Constants.APP_NAME, s, errorType);
    }

    public static void log(String s) {
        log(Constants.APP_NAME, s);
    }

    public static void log(String tag, String s) {
        log(tag, s, ErrorType.NORMAL);
    }

    public static void log(String tag, String s, @ErrorType int error) {
        Log.d(tag, s);
        if (MyApplication.sExternalStorageDirectory == null) {
            new NullPointerException("sExternalStorageDirectory is null")
                    .printStackTrace();
            return;
        }
        String fileName = "logs";
        String fileExtension = ".txt";
        if (error == ErrorType.WEBSERVICE_RESPONSE_ERROR) {
            fileName = "web_api_error_log";
        } else if (error == ErrorType.CLIENT_SIDE_ERROR) {
            fileName = "client_log";
        }
        File f = new File(MyApplication.sExternalStorageDirectory, fileName + fileExtension);
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            BufferedWriter bfw = new BufferedWriter(new FileWriter(f, true));
            bfw.append(tag + "->" + s);
            bfw.newLine();
            bfw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showProgressBar(@NonNull Context context) {
        final ViewGroup layout = (ViewGroup) ((Activity) context).findViewById(android.R.id.content).getRootView();
        ProgressBar progressBar = new ProgressBar(context);
        progressBar.setIndeterminate(true);
        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        final RelativeLayout rl = new RelativeLayout(context);
        rl.setTag("#$UniqueProgressBar");
        ViewGroup.LayoutParams params2 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                200);
        rl.setGravity(Gravity.CENTER);
        rl.addView(progressBar, params2);

        layout.addView(rl, params);
    }

    public static void hideProgressBar(@NonNull Context context) {
        final ViewGroup layout = (ViewGroup) ((Activity) context).findViewById(android.R.id.content).getRootView();
        if (layout != null && layout.getChildCount() > 0) {
            for (int i = 0; i < layout.getChildCount(); i++) {
                View v = layout.getChildAt(i);
                if (v == null)
                    return;
                Object o = v.getTag();
                if (o != null && o instanceof String) {
                    String s = (String) o;
                    if (s.equalsIgnoreCase("#$UniqueProgressBar") && v instanceof RelativeLayout) {
                        if (((RelativeLayout) v).getChildCount() > 0 && ((RelativeLayout) v).getChildAt(0) instanceof ProgressBar) {
                            ((RelativeLayout) v).removeAllViews();
                            layout.removeView(v);
                        }
                        break;
                    }
                }
            }
        }

    }

    public static void shortToast(Context context, String msg) {
        showToast(context, msg, Toast.LENGTH_SHORT);
    }

    public static void longToast(Context context, String msg) {
        showToast(context, msg, Toast.LENGTH_LONG);
    }

    public static void showToast(Context context, String msg, int length) {
        Toast.makeText(context, msg, length).show();
    }

    public static void showProgressDialog(Context context, Call<?> call) {
        showProgressDialog(context, context.getString(R.string.txt_loading));
        log(call);
    }

    public static void showProgressDialog(Context context) {
        showProgressDialog(context, context.getString(R.string.txt_loading));
    }

    public static void showProgressDialog(Context context, String msg) {
        Utils.closeKeyBoard(context);
        if (progressDialog == null)
            progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(msg);
        progressDialog.setCancelable(false);
        progressDialog.show();
        // Utils.log("ProgressDialog","show "+new Exception().getStackTrace()[3].toString());
    }

    public static void hideProgressDialog(Context context, Throwable t) {
        hideProgressDialog(context);

        log(BaseWebServiceExecutor.LOG_TAG, t);
    }

    public static void hideProgressDialog(Context context, retrofit2.Response response) {
        hideProgressDialog(context);
        log(response);
    }

    public static void hideProgressDialog(Context context, Call<?> call) {
        hideProgressDialog(context);
        log(call);
    }

    public static void hideProgressDialog(Context context) {
        Utils.closeKeyBoard(context);
        if (progressDialog == null)
            return;
        progressDialog.dismiss();
        progressDialog = null;
        //Utils.log("ProgressDialog","hide "+new Exception().getStackTrace()[3].toString());
    }

    public static boolean checkIdValidation(long id) {
        if (id > 0)
            return true;
        return false;
    }

    public static long safeParseLong(String value) {
        if (checkStringObject(value)) {
            try {
                return Long.parseLong(value);
            } catch (NumberFormatException e) {
                log(e);
                return Constants.DEFAULT_WRONG_ID_INT;
            }
        } else {
            return Constants.DEFAULT_WRONG_ID_INT;
        }
    }

    public static int safeParseInt(String value) {
        if (checkStringObject(value)) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                log(e);
                return Constants.DEFAULT_WRONG_ID_INT;
            }
        } else {
            return Constants.DEFAULT_WRONG_ID_INT;
        }
    }

    public static boolean checkStringObject(String str) {
        if (TextUtils.isEmpty(str)) {
            log(new IllegalArgumentException("String is null or empty"));
            return false;
        }
        return true;
    }

    public static boolean checkValidURL(String url) {
        try {
            if (checkStringObject(url)) {
                URL u = new URL(url);
            } else {
                return false;
            }
        } catch (MalformedURLException e) {
            log(e);
            return false;
        } catch (Exception e) {
            log(e);
            return false;
        }
        return true;
    }


    public static float safeParseFloat(String value) {
        if (checkStringObject(value)) {
            try {
                return Float.parseFloat(value);
            } catch (NumberFormatException e) {
                log(e);
                return Constants.DEFAULT_WRONG_ID_INT;
            }
        } else {
            return Constants.DEFAULT_WRONG_ID_INT;
        }
    }

    public static StringBuilder removeCommaFromLast(StringBuilder s) {
        if (s.charAt(s.length() - 1) == ',')
            return s.deleteCharAt(s.length() - 1);
        return s;
    }
    public static String convertMillisToDateTime(long millis, String dateFormatPattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        Date date = calendar.getTime();
        String dateTime;
        try {
            dateTime = (new SimpleDateFormat(dateFormatPattern)).format(date);
        } catch (Exception e) {
            throw new RuntimeException("Exception during date parsing in convertMillisToDateTime");
        }
        return dateTime;
    }
}
