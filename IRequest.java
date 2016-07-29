package gyanani.harish.myandroidbasefiles.android_base_files;

import android.content.Context;

public interface IRequest {
    String getPostRequestParams();
    String getURLParams();
    String getWebServiceName();
    String getCompleteWebServiceURL();
    int getRequestType();
    Context getContext();
    Class getCallBack();
    boolean shouldShowLoading();
    String getWebServiceLoadingMessage();
}
