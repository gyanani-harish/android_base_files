package com.commonutils;


import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.commonutils.RequestType.GET;
import static com.commonutils.RequestType.POST;

@IntDef({GET, POST})
@Retention(RetentionPolicy.SOURCE)
public @interface RequestType {

    public static final int GET = 0;
    public static final int POST = 1;
}