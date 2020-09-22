package com.geektrust.lengaburu.war.entities.planet;

import com.geektrust.lengaburu.war.entities.battalion.BattalionStrength;
import com.geektrust.lengaburu.war.entities.battalion.BattalionType;

import static com.geektrust.lengaburu.war.utils.MiscUtils.getIntegerValue;

/**
 * Base implementation of Planet covering common attributes and functionalities.
 */
public class BasePlanet implements Planet {
    private static final String EMPTY_STRING = " ";
    protected final String name;
    protected final BattalionStrength totalStrength;
    protected final Double powerFactor;

    public BasePlanet(String name, BattalionStrength totalStrength, Double powerFactor) {
        this.name = name;
        this.totalStrength = totalStrength;
        this.powerFactor = powerFactor;
    }

    public BasePlanet(String name, int horses, int elephants, int armouredTanks, int slingGuns, double powerFactor) {
        this(name, new BattalionStrength(horses, elephants, armouredTanks, slingGuns), powerFactor);
    }

    @Override
    public BattalionStrength getTotalStrength() {
        return totalStrength;
    }

    @Override
    public Double getPowerFactor() {
        return powerFactor;
    }

    @Override
    public BattalionStrength buildUpDeployment(String deploymentAsString) throws Exception {
        String[] parameters = deploymentAsString.split(EMPTY_STRING);
        return buildUpDeployment(
                getIntegerValue(parameters[1], BattalionType.HORSE.getShortName()),
                getIntegerValue(parameters[2], BattalionType.ELEPHANT.getShortName()),
                getIntegerValue(parameters[3], BattalionType.ARMOURED_TANK.getShortName()),
                getIntegerValue(parameters[4], BattalionType.SLING_GUN.getShortName())
        );
    }

    @Override
    public BattalionStrength buildUpDeployment(int horses, int elephants, int armouredTanks, int slingGuns) throws Exception {
        return validateDeploymentAndReturn(new BattalionStrength(horses, elephants, armouredTanks, slingGuns));
    }

    private BattalionStrength validateDeploymentAndReturn(BattalionStrength deployment) throws Exception {
        if (deployment.getHorses().getStrength() > getTotalStrength().getHorses().getStrength()
                || deployment.getElephants().getStrength() > getTotalStrength().getElephants().getStrength()
                || deployment.getArmouredTanks().getStrength() > getTotalStrength().getArmouredTanks().getStrength()
                || deployment.getSlingGuns().getStrength() > getTotalStrength().getSlingGuns().getStrength() ) {
            throw new Exception(new StringBuilder()
                    .append("Deployment exceeds capacity.")
                    .append("Capacity: ")
                    .append(getTotalStrength().toStringCustom())
                    .append(", Deployment: ")
                    .append(deployment.toStringCustom()).toString());
        }
        return deployment;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BasePlanet{");
        sb.append("name='").append(name).append('\'');
        sb.append(", totalStrength=").append(totalStrength);
        sb.append(", powerFactor=").append(powerFactor);
        sb.append('}');
        return sb.toString();
    }
}
