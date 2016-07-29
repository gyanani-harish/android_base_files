package gyanani.harish.myandroidbasefiles.android_base_files;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Bean  {
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
