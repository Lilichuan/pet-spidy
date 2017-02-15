package com.taiwanandroidapp.tim.pet_spidy.Manager;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by tim on 2017/2/15.
 */

public class Setting {

    private final String SP_NAME = "SettingValue" ;

    private final String KEY_LAST_POSITION_X = "position_x";
    private final String KEY_LAST_POSITION_Y = "position_y";
    private final String KEY_LAST_ORIENTATION = "Orientation";

    private SharedPreferences sp;

    public Setting(Context context){
        sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    public void setLastPostion(float x, float y){
        SharedPreferences.Editor editor = sp.edit();

        editor.putFloat(KEY_LAST_POSITION_X, x);
        editor.putFloat(KEY_LAST_POSITION_Y, y);

        editor.apply();
    }
}
