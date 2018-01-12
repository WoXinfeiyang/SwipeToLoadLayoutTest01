package com.fxj.swipetoloadlayouttest01;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

/**
 * Created by fuxianjin-hj on 2018/1/10.
 */

public class CustomRefreshHeader extends TextView implements SwipeRefreshTrigger,SwipeTrigger {
    public CustomRefreshHeader(Context context) {
        super(context);
    }

    public CustomRefreshHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onPrepare() {
        setText("==onPrepare");
        Log.i("fxj0110","==onPrepare");
    }

    @Override
    public void onRefresh() {
        setText("正在刷新中……==onRefresh");
        Log.i("fxj0110","正在刷新中……==onRefresh");
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        setText("==onMove");
        Log.i("fxj0110","==onMove");
    }

    @Override
    public void onRelease() {
        setText("==onRelease");
        Log.i("fxj0110","==onRelease");
    }

    @Override
    public void onComplete() {
        setText("==onComplete");
        Log.i("fxj0110","==onComplete");
    }

    @Override
    public void onReset() {
        setText("==onReset");
        Log.i("fxj0110","==onReset");
    }
}
