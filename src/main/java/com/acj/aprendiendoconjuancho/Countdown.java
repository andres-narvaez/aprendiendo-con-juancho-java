package com.acj.aprendiendoconjuancho;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Countdown to be used in each level,
 * to limit the time to complete a challenge.
 */
public class Countdown {
    private final EventBus eventBus = ServiceLocator.INSTANCE.getService(EventBus.class);
    private Timer timer;
    private String count;

    public Countdown() {
        eventBus.addEventHandler(GameEvent.END_COUNTDOWN, event -> stop());
    }


    /**
     * Initializes the Countdown
     * @param time Integer length time of the countdown
     */
    public void start(Integer time) {
        timer = new java.util.Timer();
        TimerTask task = new TimerTask() {
            int i = time;

            @Override
            public void run() {
                if (i >= 0) {
                    count = formatTime(i--);
                    GameEvent event = new GameEvent(GameEvent.UPDATE_COUNTDOWN);
                    event.setCount(count);
                    eventBus.fireEvent(event);
                    if(i == 0) {
                        GameEvent endEvent = new GameEvent(GameEvent.END_COUNTDOWN);
                        eventBus.fireEvent(endEvent);
                    }
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    /**
     * Stops the countdown
     */
    public void stop() {
        if(timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
        }
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
