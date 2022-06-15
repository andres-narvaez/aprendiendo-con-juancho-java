package com.acj.aprendiendoconjuancho;

/**
 * This class handles the Player info
 */
public class Player {
    private String name;

    /**
     * Creates a new instance of Player
     * @param name Name of player registered from the start
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Get the name of the player
     * @return String name of the player
     */
    public String getName() {
        return name;
    }
}
