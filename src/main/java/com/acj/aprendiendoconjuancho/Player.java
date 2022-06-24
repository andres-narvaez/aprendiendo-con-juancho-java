package com.acj.aprendiendoconjuancho;

/**
 * This class handles the Player info
 */
public final class Player {
    private static Player instance;
    private String name;

    /**
     * Get the name of the player
     * @return String name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the player
     * @param name Name of player registered from the start
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Creates a singleton instance of Player
     */
    public static Player getInstance() {
        if (instance == null ) {
            instance = new Player();
        }

        return instance;
    }
}
