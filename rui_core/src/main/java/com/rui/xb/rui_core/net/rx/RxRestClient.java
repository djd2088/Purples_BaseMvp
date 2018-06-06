package com.rui.xb.rui_core.net.rx;

import android.content.Context;

import com.rui.xb.rui_core.net.ot.HttpMethod;
import com.rui.xb.rui_core.net.ot.RestCreator;
import com.rui.xb.rui_core.ui.loader.LoadStyle;
import com.rui.xb.rui_core.ui.loader.RuiLoader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;


/**
 * Created by Rui on 2018/4/14.
 */

public class RxRestClient {

    private String mUrl;
    private Map<String,Object> mParams = new HashMap<>();
    private Context mContext;
    private RequestBody mBody;
    private LoadStyle mLoadStyle;
    private File mFile;

    public RxRestClient(String mUrl, Map<String, Object> mParams, Context mContext, RequestBody
            mBody, LoadStyle mLoadStyle, File mFile) {
        this.mUrl = mUrl;
        this.mParams = mParams;
        this.mContext = mContext;
        this.mBody = mBody;
        this.mLoadStyle = mLoadStyle;
        this.mFile = mFile;
    }

    public static RxRestClientBuilder builder(){
        return new RxRestClientBuilder();
    }

    public final Observable<String> get(){
        return request(HttpMethod.GET).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public final Observable<String> post() {
        if (mBody == null) {
            return  request(HttpMethod.POST);
        } else {
            if (!mParams.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            return  request(HttpMethod.POST_RAW);
        }
    }

    public final Observable<String> put() {
        if (mBody == null) {
            return request(HttpMethod.PUT);
        } else {
            if (!mParams.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            return  request(HttpMethod.PUT_RAW);
        }
    }

    public final Observable<String> delete() {
        return request(HttpMethod.DELETE);
    }

    public final Observable<String> upload() {
        return  request(HttpMethod.UPLOAD);
    }

    public final Observable<ResponseBody> download() {
        final Observable<ResponseBody> responseBodyObservable = RestCreator.getRxRestService().
                download(mUrl,mParams);

        return responseBodyObservable;
    }


    private Observable<String> request(HttpMethod method) {

        final IRxRestService service = RestCreator.getRxRestService();
        Observable<String> observable = null;
        RuiLoader.showLoading(mContext, mLoadStyle);
        switch (method) {
            case GET:
                observable = service.get(mUrl, mParams);
                break;
            case POST:
                observable = service.post(mUrl, mParams);
                break;
            case POST_RAW:
                observable = service.postRaw(mUrl, mBody);
                break;
            case PUT:
                observable = service.put(mUrl, mParams);
                break;
            case PUT_RAW:
                observable = service.putRaw(mUrl, mBody);
                break;
            case DELETE:
                observable = service.delete(mUrl, mParams);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody
                        .FORM.toString()), mFile);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file", mFile
                        .getName(), requestBody);
                observable = service.upload(mUrl, body);
                //call = RestCreator.getRestService().upload(URL,body);
                break;
            default:
                break;
        }

        return observable;

    }



}
