package com.geektrust.lengaburu.war.entities.planet;

/**
 * Enumeration defining all the planets existing int he lengaburu world.
 * Currently it has only Lengaburu and Falicornia and can be extended to include more.
 */
public enum Planets {
    LENGABURU(new BasePlanet("LENGABURU", 100, 50, 10, 5, 2.0)),
    FALICORNIA(new BasePlanet("FALICORNIA", 300, 200, 40, 20, 1.0));

    private Planet planet;

    Planets(Planet planet) {
        this.planet = planet;
    }

    public Planet getPlanet() {
        return planet;
    }
}
