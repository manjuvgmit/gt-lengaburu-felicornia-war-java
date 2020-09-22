package com.geektrust.lengaburu.war;

import com.geektrust.lengaburu.war.entities.battalion.BattalionStrength;

import static com.geektrust.lengaburu.war.utils.MiscUtils.getFactoredValue;

/**
 * This class is a placeholder for deployment of enemy and possible deployment fo lengaburu to match the enemy.
 */
public class DeploymentBuilder {
    private final BattalionStrength lengaburuStrength;
    private final BattalionStrength targetDeployment;
    private final BattalionStrength.Builder deployment;

    public DeploymentBuilder(BattalionStrength lengaburuStrength, BattalionStrength felicorniaDeployment, Double powerFactor) {
        this.lengaburuStrength = lengaburuStrength;
        this.targetDeployment = new BattalionStrength.Builder()
                .withHorses(getFactoredValue(felicorniaDeployment.getHorses().getStrength(), powerFactor))
                .withElephants(getFactoredValue(felicorniaDeployment.getElephants().getStrength(), powerFactor))
                .withArmouredTanks(getFactoredValue(felicorniaDeployment.getArmouredTanks().getStrength(), powerFactor))
                .withSlingGuns(getFactoredValue(felicorniaDeployment.getSlingGuns().getStrength(), powerFactor))
                .build();
        this.deployment = new BattalionStrength.Builder();
    }

    public BattalionStrength getLengaburuStrength() {
        return lengaburuStrength;
    }

    public BattalionStrength getTargetDeployment() {
        return targetDeployment;
    }

    public BattalionStrength.Builder getDeployment() {
        return deployment;
    }
}
