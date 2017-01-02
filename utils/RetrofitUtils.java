package gyanani.harish.myandroidbasefiles.android_base_files;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * Created by a on 8/15/2016.
 */
public class RetrofitUtils extends Utils{
    public static void log(retrofit2.Response response) {
        log(BaseWebServiceExecutor.LOG_TAG, " Response-> raw=" + response.raw().toString() + "body="
                + ReflectionToStringBuilder.reflectionToString(response.body()));
    }

    public static void log(Call<?> call) {
        RequestBody requestBody = call.request().body();
        StringBuilder params = new StringBuilder("");
        if (requestBody instanceof FormBody) {
            FormBody formBody = (FormBody) requestBody;
            for (int i = 0; i < formBody.size(); i++) {
                params.append("key=" + formBody.encodedName(i) + " value=" + formBody.encodedValue(i) + "\n");
            }
        }
        log(BaseWebServiceExecutor.LOG_TAG, " Request-> url= "
                + call.request().url().url().toString() + " parameters = " + params.toString());
    }
}
