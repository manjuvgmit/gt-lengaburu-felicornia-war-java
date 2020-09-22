package com.geektrust.lengaburu.war;

import com.geektrust.lengaburu.war.entities.battalion.BattalionStrength;

import static com.geektrust.lengaburu.war.utils.MiscUtils.getFactoredValue;

/**
 * This class is a placeholder for deployment of enemy and possible deployment fo lengaburu to match the enemy.
 */
public class DeploymentBuilder {
    private final BattalionStrength defenderStrength;
    private final BattalionStrength attackerDeployment;
    private final BattalionStrength.Builder defenderDeployment;

    public DeploymentBuilder(BattalionStrength defenderStrength, BattalionStrength attackerDeployment, Double powerFactor) {
        this.defenderStrength = defenderStrength;
        this.attackerDeployment = new BattalionStrength.Builder()
                .withHorses(getFactoredValue(attackerDeployment.getHorses().getStrength(), powerFactor))
                .withElephants(getFactoredValue(attackerDeployment.getElephants().getStrength(), powerFactor))
                .withArmouredTanks(getFactoredValue(attackerDeployment.getArmouredTanks().getStrength(), powerFactor))
                .withSlingGuns(getFactoredValue(attackerDeployment.getSlingGuns().getStrength(), powerFactor))
                .build();
        this.defenderDeployment = new BattalionStrength.Builder();
    }

    public BattalionStrength getDefenderStrength() {
        return defenderStrength;
    }

    public BattalionStrength getAttackerDeployment() {
        return attackerDeployment;
    }

    public BattalionStrength.Builder getDefenderDeployment() {
        return defenderDeployment;
    }
}
