package gyanani.harish.myandroidbasefiles.android_base_files;

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
