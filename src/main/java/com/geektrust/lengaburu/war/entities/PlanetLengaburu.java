package com.geektrust.lengaburu.war.entities;

public class PlanetLengaburu extends BasePlanet {

    private static final PlanetLengaburu instance = new PlanetLengaburu(100, 50, 10, 5);

    public PlanetLengaburu(int horses, int elephants, int armouredTanks, int slingGuns) {
        super(horses, elephants, armouredTanks, slingGuns, 2.0);
    }

    public static PlanetLengaburu getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PlanetLengaburu{");
        sb.append("totalStrength=").append(totalStrength);
        sb.append(", deployment=").append(deployment);
        sb.append('}');
        return sb.toString();
    }
}
