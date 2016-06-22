package com.commonutils;

/**
 * Created by b on 6/22/2016.
 */

public abstract class AbstractPostRequest extends AbstractRequest {
    @Override
    public String getURLParams() {
        return null;
    }

    @Override
    public int getRequestType() {
        return RequestType.POST;
    }
}
