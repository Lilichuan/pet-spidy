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
    private Line[] lines, axis;

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

        for (Line line : lines){
            line.draw(canvas, paint);
        }
    }



    private void initLines(Canvas canvas){

        if(axis == null){
            float w = canvas.getWidth();
            float h = canvas.getHeight();

            float half_w = w / 2;
            float half_h = h / 2;
            float temp = Math.max(w, h);

            axis = new Line[4];
            for (int i = 0;i < axis.length ;i++){
                axis[i] = new Line();
            }

            axis[0].startX = 0;
            axis[0].startY = half_h;
            axis[0].endX = w;
            axis[0].endY = half_h;

            axis[1].startX = half_w;
            axis[1].startY = 0;
            axis[1].endX = half_w;
            axis[1].endY = h;

            axis[2].startX = half_w - temp;
            axis[2].startY = half_h - temp;
            axis[2].endX = half_w + temp;
            axis[2].endY = half_h + temp;

            axis[3].startX = half_w + temp;
            axis[3].startY = axis[0].startY;
            axis[3].endX = half_w - temp;
            axis[3].endY = axis[0].endY;

        }

        if(lines == null){
            lines = new Line[16];
            for (int i = 0;i < lines.length ;i++){
                lines[i] = new Line();
            }

            

        }


    }


    private void drawLines(){

    }


}
