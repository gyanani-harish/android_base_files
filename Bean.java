package com.commonutils;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by b on 6/22/2016.
 */

public class Bean  {
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
