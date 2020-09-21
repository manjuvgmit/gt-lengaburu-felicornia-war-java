package com.geektrust.lengaburu.war.entities;

public class PlanetLengaburu extends BasePlanet {

    private static final PlanetLengaburu instance = new PlanetLengaburu(PlanetLengaburu.class.getSimpleName(), 100, 50, 10, 5);

    private PlanetLengaburu(String name, int horses, int elephants, int armouredTanks, int slingGuns) {
        super(name, horses, elephants, armouredTanks, slingGuns, 2.0);
    }

    public static PlanetLengaburu getInstance() {
        return instance;
    }

}
