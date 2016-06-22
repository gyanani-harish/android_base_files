package com.commonutils;

/**
 * Created by b on 6/22/2016.
 */

public abstract class AbstractRequest implements IRequest {
    @Override
    public boolean shouldShowLoading() {
        return Loading.SHOW_LOADING;
    }

    @Override
    public String getWebServiceLoadingMessage() {
        return "Loading...";
    }
}
