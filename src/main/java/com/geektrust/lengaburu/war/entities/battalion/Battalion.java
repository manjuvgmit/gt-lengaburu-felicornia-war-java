package com.geektrust.lengaburu.war.entities.battalion;

public interface Battalion {
    BattalionType getType();
    Integer getStrength();
    Integer getTotalStrength();
    String toStringCustom();
}
