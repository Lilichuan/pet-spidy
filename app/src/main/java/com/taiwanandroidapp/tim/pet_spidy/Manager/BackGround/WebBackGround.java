package com.taiwanandroidapp.tim.pet_spidy.Manager.BackGround;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.taiwanandroidapp.tim.pet_spidy.Manager.Tool.Line;

/**
 * Created by tim on 2017/2/15.
 * 蜘蛛網背景
 */

public class WebBackGround extends BackGroundItf {

    private final String YELLOW = "#e0f417";
    private final int STROKE_W = 10;
    private Paint paint;
    private Line[] bigOctagon, smallOctagon, axis;

    public WebBackGround(String bg_color){
        super(bg_color);
        paint = new Paint();
        paint.setStrokeWidth(STROKE_W);
        paint.setColor(Color.parseColor(YELLOW));
    }

    @Override
    void draw(Canvas canvas) {

        initLines(canvas);

        for (Line line : axis){
            line.draw(canvas, paint);
        }

        for (Line line : bigOctagon){
            line.draw(canvas, paint);
        }

        for (Line line : smallOctagon){
            line.draw(canvas, paint);
        }
    }


    //初始化所有線條座標
    private void initLines(Canvas canvas){
        float w = canvas.getWidth();
        float h = canvas.getHeight();

        float half_w = w / 2;
        float half_h = h / 2;


        if(axis == null){

            float temp = Math.max(w, h);

            axis = new Line[4];
            for (int i = 0;i < axis.length ;i++){
                axis[i] = new Line();
            }

            axis[0].setStart(0, half_h);
            axis[0].setEnd(w, half_h);

            axis[1].setStart(half_w, 0);
            axis[1].setEnd(half_w, h);

            axis[2].setStart(half_w - temp, half_h - temp);
            axis[2].setEnd(half_w + temp, half_h + temp);

            axis[3].setStart(half_w + temp, axis[2].getStartY());
            axis[3].setEnd(half_w - temp, axis[2].getEndY());
        }

        float temp_length = Math.min(canvas.getHeight(), canvas.getWidth());//取得畫面最小邊長
        temp_length = temp_length / 2;//最小邊長轉換成圖形半徑的最大值
        float radius;
        if(bigOctagon == null){
            bigOctagon = new Line[8];
            radius = (float)(temp_length * 0.8);
            initOctagonLines(bigOctagon, radius, half_w, half_h);
        }

        if(smallOctagon == null){
            smallOctagon = new Line[8];
            radius = (float)(temp_length * 0.4);
            initOctagonLines(smallOctagon, radius, half_w, half_h);
        }

    }

    //計算八角形座標
    private void initOctagonLines(Line[] lines ,float radius, float centerX, float centerY){

        for (int i = 0;i < lines.length ;i++){
            lines[i] = new Line();
        }

        float temp_length = (float) (Math.sin(45)*radius);

        lines[0].setStart(centerX,  centerY - radius);
        lines[0].setEnd(centerX + temp_length, centerY - temp_length);
        lines[1].setEnd(centerX + radius, centerY);
        lines[2].setEnd(centerX + temp_length, centerY + temp_length);
        lines[3].setEnd(centerX, centerY + radius);
        lines[4].setEnd(centerX - temp_length, centerY + temp_length);
        lines[5].setEnd(centerX - radius, centerY);
        lines[6].setEnd(centerX - temp_length, centerY - temp_length);
        lines[7].setEnd(lines[0].getStartX(), lines[0].getStartY());

        for (int i = 1;i < lines.length ;i++){
            lines[i].continueFromOtherLine(lines[i - 1]);
        }
    }


}
