package com.rui.xb.purple.mvp.model;


import com.rui.xb.rui_core.net.listenter.NetIncidentListener;
import com.rui.xb.rui_core.net.rx.RxRestClient;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Rui on 2018/5/28.
 */

public class ModelTest {

    private RxAppCompatActivity context;


    public ModelTest(RxAppCompatActivity context) {
        this.context = context;
    }

    public void requestBaidu(final NetIncidentListener netIncidentListener){

        RxRestClient.builder().url("https://www.baidu.com/").loader(context).build()
                .get()
//                .compose(context.<String>bindToLifecycle())   //解决Rxjava可能导致的内存泄漏
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        netIncidentListener.Success(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        netIncidentListener.Failed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
