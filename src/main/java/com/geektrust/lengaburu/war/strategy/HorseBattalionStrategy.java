package com.geektrust.lengaburu.war.strategy;

import com.geektrust.lengaburu.war.entities.battalion.Battalion;
import com.geektrust.lengaburu.war.entities.battalion.BattalionStrength;

public class HorseBattalionStrategy extends BaseStrategy {

    @Override
    public Battalion getFelicorniaDeployment(BattalionStrength felicorniaDeployment) {
        return felicorniaDeployment.getHorses();
    }

    @Override
    public Battalion getLengaburuCapacity(BattalionStrength lengaburuStrength) {
        return lengaburuStrength.getHorses();
    }

    @Override
    public void updateDeployment(BattalionStrength.Builder deployment, int deploymentStrength) {
        deployment.withHorses(deploymentStrength);
    }

    @Override
    protected void updateLowerDeployment(BattalionStrength.Builder deployment, int deploymentStrength) {
        // Do nothing
    }

    @Override
    public Battalion getFelicorniaLowerDeployment(BattalionStrength felicorniaDeployment) {
        return null;
    }

    @Override
    public Battalion getLengaburuLowerCapacity(BattalionStrength lengaburuStrength) {
        return null;
    }
}
