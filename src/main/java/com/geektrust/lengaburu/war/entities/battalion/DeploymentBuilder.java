package com.geektrust.lengaburu.war.entities.battalion;

import static com.geektrust.lengaburu.war.utils.MiscUtils.getFactoredValue;

public class DeploymentBuilder {
    private final BattalionStrength lengaburuStrength;
    private final BattalionStrength.Builder targetDeployment;
    private final BattalionStrength.Builder deployment;

    public DeploymentBuilder(BattalionStrength lengaburuStrength, BattalionStrength felicorniaDeployment, double powerFactor) {
        this.lengaburuStrength = lengaburuStrength;
        this.targetDeployment = new BattalionStrength.Builder()
                .withHorses(getFactoredValue(felicorniaDeployment.getHorses().getStrength(), powerFactor))
                .withElephants(getFactoredValue(felicorniaDeployment.getElephants().getStrength(), powerFactor))
                .withArmouredTanks(getFactoredValue(felicorniaDeployment.getArmouredTanks().getStrength(), powerFactor))
                .withSlingGuns(getFactoredValue(felicorniaDeployment.getSlingGuns().getStrength(), powerFactor));
        this.deployment = new BattalionStrength.Builder();
    }

    public BattalionStrength getLengaburuStrength() {
        return lengaburuStrength;
    }

    public BattalionStrength.Builder getTargetDeployment() {
        return targetDeployment;
    }

    public BattalionStrength.Builder getDeployment() {
        return deployment;
    }
}
