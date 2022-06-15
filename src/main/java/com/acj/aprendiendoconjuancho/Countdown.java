package com.acj.aprendiendoconjuancho;

import java.util.Timer;
import java.util.TimerTask;

public class Countdown {
    private Timer timer = new java.util.Timer();
    private TimerTask task;
    private String count;

    public void start(Integer time) {
        task = new TimerTask() {
            int i = time;
            @Override
            public void run() {
                if (i >= 0) {
                    count = formatTime(i--);
                    System.out.println(count);
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public String getCount () { return count; }

    private String formatTime (Integer time) {
        return String.format("%02d:%02d", time / 60, time % 60);
    }
}
