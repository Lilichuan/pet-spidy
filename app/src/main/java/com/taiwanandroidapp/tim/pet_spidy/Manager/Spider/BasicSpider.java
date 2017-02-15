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
    private Canvas m_canvas;

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

        drawBasic(body, leg);

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

        m_canvas = new Canvas(bitmap);

        for (Line line : legs){
            line.draw(m_canvas, paint);
        }
        m_canvas.save();
    }

    private int checkDirection(float startX, float startY, float targetX, float targetY){
        float d_x = targetX - startX;
        float d_y = targetY - startY;

        if(d_x == 0 && d_y == 0){
            return 0;
        }

        if(d_x == 0){
            if(d_y > 0){
                return 180;
            }else if(d_y < 0){
                return 0;
            }
        }

        if(d_y == 0){
            if(d_x > 0){
                return 90;
            }else if(d_x < 0){
                return 270;
            }
        }

        //以下是x, y 不等於 0的情況
        int ans;

        float temp_x = Math.abs(d_x);
        float temp_y = Math.abs(d_y);

        int tan2 = (int)Math.atan2(temp_y, temp_x);

        if(d_x > 0){//右邊
            if(d_y > 0){//右下
                ans = 90 + tan2;
            }else {//右上
                ans = 90 - tan2;
            }
        }else {//左邊
            if(d_y > 0){//左下
                ans = 270 - tan2;
            }else {//左上
                ans = 270 + tan2;
            }
        }

        return ans;
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
