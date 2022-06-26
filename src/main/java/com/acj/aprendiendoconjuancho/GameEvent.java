package com.acj.aprendiendoconjuancho;

import javafx.event.Event;
import javafx.event.EventType;

public class GameEvent extends Event {
    // remove
    public static final EventType<GameEvent> GAME_EVENT = new EventType<>(Event.ANY, "GAME_EVENT");

    public static final EventType<GameEvent> START_COUNTDOWN = new EventType<>(Event.ANY, "START_COUNTDOWN");
    public static final EventType<GameEvent> UPDATE_COUNTDOWN = new EventType<>(Event.ANY, "UPDATE_COUNTDOWN");

    public GameEvent(EventType<GameEvent> type) {
        super(type);
    }
}
