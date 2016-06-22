package com.commonutils;

import android.content.Context;

/**
 * Created by b on 6/22/2016.
 */

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
