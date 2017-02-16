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

    private Paint paint;
    private Bitmap bitmap;
    private Canvas m_canvas;



    /*
    * 頭現在面向哪個角度。
    * 值的範圍：0~359
    * */
    private float angleFaceTo = 0;

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

    /*
    *
    * 轉身的速率：角度/影格
    * */
    //private int ROTATE_SPEED = 50;

    /*
    *
    * 移動的速率：像素/影格
    * */
    private float SPEED = 20;


    public BasicSpider(float body, float leg, String color){
        paint = new Paint();
        paint.setColor(Color.parseColor(color));
        paint.setStrokeWidth(5);
        positionX = -1;
        positionY = -1;
        drawBasic(body, leg);
    }

    /*
    *
    * 繪製基本蜘蛛原圖
    * 基本款蜘蛛的腳沒有關節，用八條線畫
    * 順時針，右上起算「第一隻腳」「第二隻腳」
    * */
    private void drawBasic(float body, float leg){

        int bitmapW = (int)(body + leg * 2 + 30);
        float center = bitmapW / 2;
        bitmap = Bitmap.createBitmap(bitmapW, bitmapW, Bitmap.Config.ARGB_4444);

        Line[] legs = new Line[8];
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

    public void draw(Canvas canvas,float targetX, float targetY){

        if(positionX < 0 || positionY < 0){
            positionX = canvas.getWidth() / 2;
            positionY = canvas.getHeight() / 2;
        }

        if(needToMove(targetX, targetY)){
            angleFaceTo = countAngle(positionX, positionY, targetX, targetY);
            move();
        }

        drawBody(canvas);
    }

    /*
    *
    * 計算面向角度
    * */
    private int countAngle(float startX, float startY, float targetX, float targetY){
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



    private void drawBody(Canvas canvas){
        Bitmap temp_bitmap;

        if(angleFaceTo > 0){
            temp_bitmap = Bitmap.createBitmap(bitmap);
            Canvas temp_canvas = new Canvas(temp_bitmap);
            temp_canvas.rotate(angleFaceTo);
            temp_canvas.save();
        }else {
            temp_bitmap = bitmap;
        }

        int half_w = temp_bitmap.getWidth() / 2;

        canvas.drawBitmap(temp_bitmap,
                positionX - half_w,
                positionY - half_w,
                paint);
    }

    private boolean needToMove(float targetX, float targetY){

        if(targetX > 0 && targetY > 0){//有效的目的地座標
            boolean ans = rememberTargetX != targetX || rememberTargetY != targetY ;
            if(ans){//前往新地點
                rememberTargetX = targetX;
                rememberTargetY = targetY;
                return ans;
            }else {//目標不變
                return rememberTargetX == positionX && rememberTargetY == positionY;
            }


        }else {//停在原地
            return false;
        }
    }

    private void move(){
        int angle = (int)angleFaceTo;

        switch (angle){
            case 0:
                if(positionY - SPEED < rememberTargetY){
                    arriveCalculate();
                }else {
                    positionY -= SPEED;
                }
                break;

            case 90:
                if(positionX + SPEED > rememberTargetX){
                    arriveCalculate();
                }else {
                    positionX += SPEED;
                }
                break;

            case 180:
                if(positionY + SPEED > rememberTargetY){
                    arriveCalculate();
                }else {
                    positionY += SPEED;
                }
                break;

            case 270:
                if(positionX - SPEED < rememberTargetX){
                    arriveCalculate();
                }else {
                    positionX -= SPEED;
                }
                break;
            default:
                move_calculate();
        }
    }

    private void move_calculate(){

        float corner_angle, xSpeed, ySpeed;

        if(angleFaceTo < 0 && angleFaceTo < 90){//第一象限
            corner_angle = 90 - angleFaceTo;
            xSpeed = count_X_Speed(corner_angle);
            ySpeed = count_Y_Speed(corner_angle);
            positionX += xSpeed;
            positionY -= ySpeed;
            if(positionX >= rememberTargetX){
                arriveCalculate();
            }

        }else if(angleFaceTo > 90 && angleFaceTo < 180){//第四象限
            corner_angle = angleFaceTo - 90;
            xSpeed = count_X_Speed(corner_angle);
            ySpeed = count_Y_Speed(corner_angle);
            positionX += xSpeed;
            positionY += ySpeed;
            if(positionX >= rememberTargetX){
                arriveCalculate();
            }

        }else if(angleFaceTo > 180 && angleFaceTo < 270){//第三象限
            corner_angle = 270 - angleFaceTo;
            xSpeed = count_X_Speed(corner_angle);
            ySpeed = count_Y_Speed(corner_angle);
            positionX -= xSpeed;
            positionY += ySpeed;
            if(positionX <= rememberTargetX){
                arriveCalculate();
            }
        }else {//第二象限
            corner_angle = angleFaceTo - 270;
            xSpeed = count_X_Speed(corner_angle);
            ySpeed = count_Y_Speed(corner_angle);
            positionX -= xSpeed;
            positionY -= ySpeed;
            if(positionX <= rememberTargetX){
                arriveCalculate();
            }
        }
    }

    //斜線前進，求X每格速率
    private float count_X_Speed(float angle){
        return (float) (Math.cos(angle) * SPEED);
    }

    //斜線前進，求Y每格速率
    private float count_Y_Speed(float angle){
        return (float) (Math.sin(angle) * SPEED);
    }

    private void arriveCalculate(){
        positionX = rememberTargetX;
        positionY = rememberTargetY;
    }

}
