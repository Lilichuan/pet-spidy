package com.taiwanandroidapp.tim.pet_spidy.Manager;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.taiwanandroidapp.tim.pet_spidy.Manager.BackGround.BackGroundItf;
import com.taiwanandroidapp.tim.pet_spidy.Manager.BackGround.WebBackGround;
import com.taiwanandroidapp.tim.pet_spidy.Manager.Spider.BasicSpider;

/**
 * Created by tim on 2017/2/15.
 */

public class Manager {

    private BackGroundItf backGround;
    private BasicSpider spider;

    private MotionEvent motionEvent;

    public Manager(){
        backGround = new WebBackGround("#000000");
        spider = new BasicSpider(30, 10, "#00bfff");
    }

    public void draw(Canvas canvas){
        backGround.drawBackground(canvas);
        if(motionEvent == null){
            spider.draw(canvas, canvas.getWidth() / 2 , canvas.getHeight() / 2);
        }else {
            spider.draw(canvas, motionEvent.getX(), motionEvent.getY());
        }

    }

    public void setMotionEvent(MotionEvent motionEvent) {
        this.motionEvent = motionEvent;
    }
}
