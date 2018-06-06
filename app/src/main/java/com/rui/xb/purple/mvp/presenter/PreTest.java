package com.rui.xb.purple.mvp.presenter;

import android.widget.Toast;

import com.rui.xb.purple.mvp.model.ModelTest;
import com.rui.xb.purple.mvp.view.ViewTest;
import com.rui.xb.rui_core.app.Rui;
import com.rui.xb.rui_core.net.listenter.NetIncidentListener;
import com.rui.xb.rui_core.ui.loader.RuiLoader;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * Created by Rui on 2018/5/28.
 */

public class PreTest {

    private ModelTest modelTest;

    private ViewTest viewTest;


    public PreTest(RxAppCompatActivity context, ViewTest viewTest) {

        this.modelTest = new ModelTest(context);
        this.viewTest = viewTest;
    }


    public void requestBaiduChangeData(){
        modelTest.requestBaidu(new NetIncidentListener() {
            @Override
            public void Success(Object data) {
                Toast.makeText(Rui.getApplicationContext(),"得到了请求的返回",Toast.LENGTH_SHORT).show();
                //改变button的文字
                viewTest.getBtnImButton().setText("处理得到的数据完成");
                Rui.getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        RuiLoader.stopLoading();
                    }
                },2000);

            }

            @Override
            public void Failed(String message) {
                Toast.makeText(Rui.getApplicationContext(),"失败信息",Toast.LENGTH_SHORT).show();
            }
        });
    }


}
