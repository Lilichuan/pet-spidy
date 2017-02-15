package com.taiwanandroidapp.tim.pet_spidy.Manager.Spider;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.taiwanandroidapp.tim.pet_spidy.Manager.Tool.Line;

/**
 * Created by tim on 2017/2/15.
 * 基本款蜘蛛
 *
 * 腳沒有關節，用八條線畫八條腿
 * 身體是圓形，頭部不用繪製。
 */

public class BasicSpider {

    private float bodyLength, legLength;
    private Paint paint;
    private Bitmap bitmap;

    //基本款蜘蛛的腳沒有關節，用八條線畫
    //順時針，右上起算「第一隻腳」「第二隻腳」
    private Line[] legs;

    /*
    * 頭現在面向哪個角度。
    * 值的範圍：0~359
    * */
    private int angleFaceTo = 0;

    /*
    *
    * 當前位置
    *
    * */
    private float positionX, positionY;

    /*
    *
    * 得到的最後要前往的地點
    *
    * */
    private float rememberTargetX, rememberTargetY;
    private int rememberTargetAngle;

    /*
    *
    * 轉身的速率：角度/影格
    * */
    private int ROTATE_SPEED = 50;

    /*
    *
    * 移動的速率：像素/影格
    * */
    private float SPEED = 20;


    public BasicSpider(float body, float leg, String color){
        bodyLength = body;
        legLength = leg;
        paint = new Paint();
        paint.setColor(Color.parseColor(color));
        paint.setStrokeWidth(5);


    }

    private void drawBasic(float body, float leg){

        int bitmapW = (int)(body + leg * 2 + 30);
        float center = bitmapW / 2;
        bitmap = Bitmap.createBitmap(bitmapW, bitmapW, Bitmap.Config.ARGB_4444);

        legs = new Line[8];
        for (int i = 0 ;i < legs.length; i++){
            legs[i] = new Line();
            legs[i].setStart(center, center);
        }

        float radius = body / 2 + leg;

        float outsideLegX = (float) (radius * Math.cos(40));
        float outsideLegY = (float) (radius * Math.sin(40));
        float insideLegX = (float) (radius * Math.cos(20));
        float insideLegY = (float) (radius * Math.sin(20));

        legs[0].setEnd(center + outsideLegX, center - outsideLegY);
        legs[1].setEnd(center + insideLegX, center - insideLegY);
        legs[2].setEnd(center + insideLegX, center + insideLegY);
        legs[3].setEnd(center + outsideLegX, center + outsideLegY);
        legs[4].setEnd(center - outsideLegX, center + outsideLegY);
        legs[5].setEnd(center - insideLegX, center + insideLegY);
        legs[6].setEnd(center - insideLegX, center - insideLegY);
        legs[7].setEnd(center - outsideLegX, center - outsideLegY);

        Canvas canvas = new Canvas(bitmap);

        for (Line line : legs){
            line.draw(canvas, paint);
        }
        canvas.save();
    }

    public void draw(Canvas canvas,float targetX, float targetY){

    }

    private void drawBody(Canvas canvas){
        canvas.drawCircle(positionX, positionX, bodyLength / 2, paint);
    }

    private boolean isGoingNewPlace(float targetX, float targetY){
        boolean ans = rememberTargetX != targetX || rememberTargetY != targetY ;
        if(ans){
            rememberTargetX = targetX;
            rememberTargetY = targetY;
        }

        return ans;
    }

    private void calculateTargetAngle(float targetX, float targetY){

    }

}
