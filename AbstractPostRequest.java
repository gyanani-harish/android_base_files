package gyanani.harish.myandroidbasefiles.android_base_files;

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
