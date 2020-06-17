package com.wan.commonsdk.base;


import androidx.annotation.Keep;

import java.io.Serializable;

@Keep
public class BaseData<D> implements Serializable {
    public D res_data;
    public String message;
    public int code;
}
