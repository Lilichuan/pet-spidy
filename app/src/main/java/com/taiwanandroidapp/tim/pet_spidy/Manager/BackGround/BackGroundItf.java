package com.taiwanandroidapp.tim.pet_spidy.Manager.BackGround;

import android.graphics.Canvas;
import android.graphics.Color;

/**
 * Created by tim on 2017/2/15.
 * 背景interface 讓背景可擴充種類
 */

public abstract class BackGroundItf {

    private String m_bg_color;

    public BackGroundItf(String backGround_color){
        m_bg_color = backGround_color;
    }

    public void drawBackground(Canvas canvas){
        drawBG_color(canvas);
        draw(canvas);
    }

    abstract void draw(Canvas canvas);

    private void drawBG_color(Canvas canvas){
        canvas.drawColor(Color.parseColor(m_bg_color));
    }
}
