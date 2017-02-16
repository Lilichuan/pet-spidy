package com.taiwanandroidapp.tim.pet_spidy;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.taiwanandroidapp.tim.pet_spidy.Manager.Manager;

/**
 * Created by tim on 2017/2/15.
 */

public class SpiderView extends View {

    private Manager manager;


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
        manager = new Manager();

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                manager.setMotionEvent(motionEvent);
                return false;
            }
        });
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        manager.draw(canvas);
    }


}
