package com.geektrust.lengaburu.war.entities;

public enum Planets {
    LENGABURU(new BasePlanet("LENGABURU", 100, 50, 10, 5, 2.0)),
    FELICORNIA(new BasePlanet("FELICORNIA", 300, 200, 40, 20, 1.0));

    private Planet planet;

    Planets(Planet planet) {
        this.planet = planet;
    }

    public Planet getPlanet() {
        return planet;
    }
}
