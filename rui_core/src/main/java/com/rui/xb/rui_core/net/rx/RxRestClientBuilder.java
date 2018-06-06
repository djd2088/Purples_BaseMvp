package com.rui.xb.rui_core.net.rx;

import android.content.Context;

import com.rui.xb.rui_core.app.Rui;
import com.rui.xb.rui_core.app.enums.EAppConfigure;
import com.rui.xb.rui_core.ui.loader.LoadStyle;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * Created by Rui on 2018/4/14.
 */

public class RxRestClientBuilder {


    private String mUrl;
    private Map<String,Object> mParams = new HashMap<>();
    private Context mContext;
    private RequestBody mBody;
    private LoadStyle mLoadStyle;
    private File mFile;


    public final RxRestClientBuilder url(String url){
        this.mUrl = url;
        return this;
    }

    public final RxRestClientBuilder params(Map<String,Object> params){
        this.mParams = params;
        return this;
    }

    public final RxRestClientBuilder body(RequestBody body){
        this.mBody = body;
        return this;
    }

    public final RxRestClientBuilder file(File file){
        this.mFile = file;
        return this;
    }

    public final RxRestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    public final RxRestClientBuilder loader(LoadStyle loadStyle,Context context){
        this.mLoadStyle = loadStyle;
        this.mContext = context;
        return this;
    }

    public final RxRestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoadStyle = Rui.getConfigWithKey(EAppConfigure.DEFAULT_LOADER_STYLE.name());
        return this;
    }


    public final RxRestClient build(){
        return new RxRestClient(mUrl,mParams,mContext,mBody,mLoadStyle,mFile);
    }
}
