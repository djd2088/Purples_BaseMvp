package com.rui.xb.purple.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rui.xb.purple.R;
import com.rui.xb.purple.zFunctionTest.ui.HeadImageActivity;
import com.rui.xb.purple.zFunctionTest.ui.MvpTestActivity;
import com.rui.xb.purple.zFunctionTest.ui.NetTestActivity;
import com.rui.xb.purple.zFunctionTest.ui.RecycleViewTestActivity;
import com.rui.xb.purple.zFunctionTest.ui.WebViewActivity;
import com.rui.xb.rui_core.utils.UiUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {


    private static final String TAG = "FirstFragment";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.first_fragment, container, false);
        ButterKnife.bind(this,rootView);
        return rootView;

    }


    @OnClick(R.id.btn_net_test)
    void click() {
        UiUtil.startIntent(getContext(), NetTestActivity.class);
    }

    @OnClick(R.id.btn_recycle_view_test)
    void click2(){
        UiUtil.startIntent(getContext(), RecycleViewTestActivity.class);
    }

    @OnClick(R.id.btn_web_view_test)
    void click3(){
        UiUtil.startIntent(getContext(), WebViewActivity.class);
    }

    @OnClick(R.id.btn_head_img_test)
    void click4(){
        UiUtil.startIntent(getContext(), HeadImageActivity.class);
    }

    @OnClick(R.id.btn_mvp_test)
    void click6(){
        UiUtil.startIntent(getContext(), MvpTestActivity.class);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG,"onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG,"onDetach");
    }
}
