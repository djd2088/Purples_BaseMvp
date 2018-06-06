package com.rui.xb.purple.zFunctionTest.ui;

import android.os.Handler;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.rui.xb.purple.R;
import com.rui.xb.rui_core.net.rx.RxRestClient;
import com.rui.xb.rui_core.ui.loader.RuiLoader;
import com.rui.xb.rui_core.utils.SingletonUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class NetTestActivity extends RxAppCompatActivity {



    @BindView(R.id.tv_main)
    TextView tvMain;

    @BindView(R.id.iv_main)
    ImageView ivMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_test);

        ButterKnife.bind(this);

    }


    @OnClick(R.id.btn_getImg)
    void clickImg(){
        String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523896865748&di=8201a267582ac84a713903f8ce2b0799&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F14%2F82%2F09%2F15R58PICirn_1024.jpg";
        /** glide使用*/
        Glide.with(this)
                .load(url)
                .apply(SingletonUtil.getRequestOptions())
                .into(ivMain);
    }

    @OnClick(R.id.btn_getMsg)
    void clickNet() {
        RxRestClient.builder().url("https://www.baidu.com/").loader(this).build()
                .get()
                //.compose(this.<String>bindToLifecycle())   //解决Rxjava可能导致的内存泄漏
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Toast.makeText(NetTestActivity.this,s,Toast.LENGTH_SHORT).show();
                        tvMain.setText(s);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                RuiLoader.stopLoading();
                            }
                        },2000);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}

