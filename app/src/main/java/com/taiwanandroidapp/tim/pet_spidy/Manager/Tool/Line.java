package com.taiwanandroidapp.tim.pet_spidy.Manager.Tool;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by tim on 2017/2/15.
 */

public class Line {

    public float startX, startY, endX, endY;

    public void continueFromOtherLine(Line frontLine){
        startX = frontLine.endX;
        startY = frontLine.endY;
    }

    public void draw(Canvas canvas , Paint paint){
        canvas.drawLine(startX, startY, endX, endY, paint);
    }
}
