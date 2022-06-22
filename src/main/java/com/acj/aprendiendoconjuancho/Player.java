package com.acj.aprendiendoconjuancho;

/**
 * This class handles the Player info
 */
public final class Player {
    private static Player instance;
    private final String name;

    /**
     * Creates a new instance of Player
     * @param name Name of player registered from the start
     */
    private Player(String name) {
        this.name = name;
    }

    /**
     * Get the name of the player
     * @return String name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Creates a singleton instance of Player
     * @param name Name of player registered from the start
     */
    public static  Player getInstance(String name) {
        if (instance == null) {
            instance = new Player(name);
        }

        return instance;
    }
}
