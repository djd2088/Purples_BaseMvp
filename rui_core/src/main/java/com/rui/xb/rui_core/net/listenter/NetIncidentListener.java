package com.rui.xb.rui_core.net.listenter;

/**
 * Created by Rui on 2018/5/28.
 */

public interface NetIncidentListener {

    /**
     * 事件成功
     */
    void Success(Object data);

    /**
     * 事件失败
     */
    void Failed(String message);
}
