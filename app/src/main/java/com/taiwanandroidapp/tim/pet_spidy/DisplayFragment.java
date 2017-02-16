package com.taiwanandroidapp.tim.pet_spidy;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Timer;
import java.util.TimerTask;


public class DisplayFragment extends Fragment {

    private static SpiderView spiderView;
    private Timer timer;

    public DisplayFragment() {
        // Required empty public constructor
    }


    public static DisplayFragment newInstance() {
        DisplayFragment fragment = new DisplayFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display, container, false);

        spiderView = (SpiderView)view.findViewById(R.id.my_spider_view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.obtainMessage().sendToTarget();
            }
        },200,200);
    }

    private static Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            spiderView.invalidate();
            super.handleMessage(msg);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        if(timer != null){
            timer.purge();
            timer.cancel();
            timer = null;
        }
    }
}
