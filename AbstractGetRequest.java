package gyanani.harish.myandroidbasefiles.android_base_files;


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
