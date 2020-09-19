package com.geektrust.lengaburu.war.strategy;

import com.geektrust.lengaburu.war.entities.battalion.Battalion;
import com.geektrust.lengaburu.war.entities.battalion.BattalionStrength;

public class ElephantBattalionStrategy extends BaseStrategy {

    @Override
    public Battalion getFelicorniaDeployment(BattalionStrength felicorniaDeployment) {
        return felicorniaDeployment.getElephants();
    }

    @Override
    public Battalion getLengaburuCapacity(BattalionStrength lengaburuStrength) {
        return lengaburuStrength.getElephants();
    }

    @Override
    public void updateDeployment(BattalionStrength.Builder deployment, int deploymentStrength) {
        deployment.withElephants(deploymentStrength);
    }

    @Override
    protected void updateLowerDeployment(BattalionStrength.Builder deployment, int deploymentStrength) {
        deployment.withHorses(deployment.getHorses() + deploymentStrength);
    }

    @Override
    public Battalion getFelicorniaLowerDeployment(BattalionStrength felicorniaDeployment) {
        return felicorniaDeployment.getHorses();
    }

    @Override
    public Battalion getLengaburuLowerCapacity(BattalionStrength lengaburuStrength) {
        return lengaburuStrength.getHorses();
    }
}
