package com.taiwanandroidapp.tim.pet_spidy.Manager.Tool;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by tim on 2017/2/15.
 */

public class Line {

    public float startX, startY, endX, endY;

    public void setStart(float x, float y){
        startX = x;
        startY = y;
    }

    public void setEnd(float x, float y){
        endX = x;
        endY = y;
    }

    public void continueFromOtherLine(Line frontLine){
        setStart(frontLine.getEndX(), frontLine.getEndY());
    }

    public void draw(Canvas canvas , Paint paint){
        canvas.drawLine(startX, startY, endX, endY, paint);
    }

    public float getEndX() {
        return endX;
    }

    public float getEndY() {
        return endY;
    }

    public float getStartX() {
        return startX;
    }

    public float getStartY() {
        return startY;
    }
}
