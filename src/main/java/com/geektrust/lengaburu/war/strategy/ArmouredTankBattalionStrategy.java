package com.geektrust.lengaburu.war.strategy;

import com.geektrust.lengaburu.war.entities.battalion.Battalion;
import com.geektrust.lengaburu.war.entities.battalion.BattalionStrength;

public class ArmouredTankBattalionStrategy extends BaseStrategy {

    @Override
    public Battalion getFelicorniaDeployment(BattalionStrength felicorniaDeployment) {
        return felicorniaDeployment.getArmouredTanks();
    }

    @Override
    public Battalion getLengaburuCapacity(BattalionStrength lengaburuStrength) {
        return lengaburuStrength.getArmouredTanks();
    }

    @Override
    public void updateDeployment(BattalionStrength.Builder deployment, int deploymentStrength) {
        deployment.withArmouredTanks(deploymentStrength);
    }

    @Override
    protected void updateLowerDeployment(BattalionStrength.Builder deployment, int deploymentStrength) {
        deployment.withElephants(deployment.getElephants() + deploymentStrength);
    }

    @Override
    public Battalion getFelicorniaLowerDeployment(BattalionStrength felicorniaDeployment) {
        return felicorniaDeployment.getElephants();
    }

    @Override
    public Battalion getLengaburuLowerCapacity(BattalionStrength lengaburuStrength) {
        return lengaburuStrength.getElephants();
    }
}
