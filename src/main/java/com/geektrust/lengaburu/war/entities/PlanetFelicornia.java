package com.geektrust.lengaburu.war.entities;

public class PlanetFelicornia extends BasePlanet {

    private static final PlanetFelicornia instance = new PlanetFelicornia(PlanetFelicornia.class.getSimpleName(), 300, 200, 40, 20);

    private PlanetFelicornia(String name, int horses, int elephants, int armouredTanks, int slingGuns) {
        super(name, horses, elephants, armouredTanks, slingGuns, 1.0);
    }

    public static PlanetFelicornia getInstance() {
        return instance;
    }

}
