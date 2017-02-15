package com.taiwanandroidapp.tim.pet_spidy.Manager;

import android.graphics.Canvas;

import com.taiwanandroidapp.tim.pet_spidy.Manager.BackGround.BackGroundItf;
import com.taiwanandroidapp.tim.pet_spidy.Manager.BackGround.WebBackGround;

/**
 * Created by tim on 2017/2/15.
 */

public class Manager {

    private BackGroundItf backGround;

    public Manager(){
        backGround = new WebBackGround("#000000");
    }

    public void draw(Canvas canvas){
        backGround.drawBackground(canvas);
    }
}
