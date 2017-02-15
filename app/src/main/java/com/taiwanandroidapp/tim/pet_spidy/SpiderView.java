package com.taiwanandroidapp.tim.pet_spidy;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by tim on 2017/2/15.
 */

public class SpiderView extends View {


    public SpiderView(Context context) {
        super(context);
        init();
    }

    public SpiderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SpiderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
