package com.commonutils;



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
