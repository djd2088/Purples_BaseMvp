package com.rui.xb.rui_core.net.ot;

import com.rui.xb.rui_core.app.Rui;
import com.rui.xb.rui_core.app.enums.EAppConfigure;
import com.rui.xb.rui_core.net.rx.IRxRestService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Rui on 2018/4/14.
 */

public class RestCreator {


    private static class RetrofitHolder{
        private static String BASE_URL = Rui.getConfigWithKey(EAppConfigure.API_HOST
                .name());
        private static final Retrofit RETROFIT_SINGLETON_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpHolder.OK_HTTP_SINGLETON_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static class OkHttpHolder{
        private static final long TIME_OUT = 60;
        private static final OkHttpClient OK_HTTP_SINGLETON_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    public static IRxRestService getRxRestService(){
        return RxRestServiceHolder.RX_REST_SERVICE_SINGLETON;
    }

    private static class RxRestServiceHolder{
        private static final IRxRestService RX_REST_SERVICE_SINGLETON = RetrofitHolder
                .RETROFIT_SINGLETON_CLIENT.create(IRxRestService.class);
    }

}
