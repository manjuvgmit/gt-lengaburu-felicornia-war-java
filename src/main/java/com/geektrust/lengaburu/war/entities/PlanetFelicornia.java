package com.geektrust.lengaburu.war.entities;

public class PlanetFelicornia extends BasePlanet {

    private static final PlanetFelicornia instance = new PlanetFelicornia(300, 200, 40, 20);

    private PlanetFelicornia(int horses, int elephants, int armouredTanks, int slingGuns) {
        super(horses, elephants, armouredTanks, slingGuns, 1.0);
    }

    public static PlanetFelicornia getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PlanetFelicornia{");
        sb.append("totalStrength=").append(totalStrength);
        sb.append(", deployment=").append(deployment);
        sb.append('}');
        return sb.toString();
    }
}
