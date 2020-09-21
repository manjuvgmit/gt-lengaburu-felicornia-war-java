package com.geektrust.lengaburu.war.entities.battalion;

/**
 * This is an abstract representation of each forces of battalion available to war like horse, elephant etc.
 */
public interface Battalion {
    BattalionType getType();

    Integer getStrength();

    Integer getTotalStrength();

    String toStringCustom();
}
