package com.commonutils;

/**
 * Created by b on 6/22/2016.
 */

public abstract class AbstractGetRequest extends AbstractRequest {
    @Override
    public int getRequestType() {
        return RequestType.GET;
    }

    @Override
    public String getPostRequestParams() {
        return null;
    }

}
