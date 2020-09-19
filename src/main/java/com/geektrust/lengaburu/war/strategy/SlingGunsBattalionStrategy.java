package com.geektrust.lengaburu.war.strategy;

import com.geektrust.lengaburu.war.entities.battalion.Battalion;
import com.geektrust.lengaburu.war.entities.battalion.BattalionStrength;

public class SlingGunsBattalionStrategy extends BaseStrategy {

    @Override
    public Battalion getFelicorniaDeployment(BattalionStrength felicorniaDeployment) {
        return felicorniaDeployment.getSlingGuns();
    }

    @Override
    public Battalion getLengaburuCapacity(BattalionStrength lengaburuStrength) {
        return lengaburuStrength.getSlingGuns();
    }

    @Override
    public void updateDeployment(BattalionStrength.Builder deployment, int deploymentStrength) {
        deployment.withSlingGuns(deploymentStrength);
    }

    @Override
    protected void updateLowerDeployment(BattalionStrength.Builder deployment, int deploymentStrength) {
        deployment.withArmouredTanks(deployment.getArmouredTanks() + deploymentStrength);
    }

    @Override
    public Battalion getFelicorniaLowerDeployment(BattalionStrength felicorniaDeployment) {
        return felicorniaDeployment.getArmouredTanks();
    }

    @Override
    public Battalion getLengaburuLowerCapacity(BattalionStrength lengaburuStrength) {
        return lengaburuStrength.getArmouredTanks();
    }
}
