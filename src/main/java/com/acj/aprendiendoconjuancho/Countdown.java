package com.acj.aprendiendoconjuancho;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Countdown to be used in each level,
 * to limit the time to complete a challenge.
 */
public class Countdown {
    private Timer timer = new java.util.Timer();
    private TimerTask task;
    private String count;

    /**
     * Initializes the Countdown
     * @param time Integer length time of the countdown
     */
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

    /**
     * Get the count, time formatted with minutes and seconds
     * @return count String
     */
    public String getCount () { return count; }

    /**
     * Format the count of the Countdown
     * @param time Integer
     * @return String formatted with minutes and seconds as mm:ss
     */
    private String formatTime (Integer time) {
        return String.format("%02d:%02d", time / 60, time % 60);
    }
}
