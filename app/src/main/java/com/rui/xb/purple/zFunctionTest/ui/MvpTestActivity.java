package com.rui.xb.purple.zFunctionTest.ui;

import android.widget.Button;

import com.rui.xb.purple.R;
import com.rui.xb.purple.base.BaseActivity;
import com.rui.xb.purple.mvp.presenter.PreTest;
import com.rui.xb.purple.mvp.view.ViewTest;

import butterknife.BindView;
import butterknife.OnClick;

public class MvpTestActivity extends BaseActivity implements ViewTest {

    @BindView(R.id.btn_im_test)
    Button btnTest;

    private PreTest preTest = new PreTest(this,this);


    @Override
    protected void initTitleBar() {
        letfClose();
        setTvTitle("我是个Title");
        setLlTitleBgColor(R.color.colorPrimary);
        showIvRightSetRes(R.mipmap.arrow_right); //一般图片文字二选一
        showTvRightSetText("提交");
    }

    @Override
    protected int initMainView() {
        return R.layout.activity_mvp_test;
    }

    @Override
    protected void initDataAndView() {

    }

    @Override
    public Button getBtnImButton() {
        return btnTest;
    }

    @OnClick(R.id.btn_im_test)
    void clickBtn(){
        preTest.requestBaiduChangeData();
    }
}
