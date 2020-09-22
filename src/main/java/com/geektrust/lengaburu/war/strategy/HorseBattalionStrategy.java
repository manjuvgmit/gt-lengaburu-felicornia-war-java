package com.geektrust.lengaburu.war.strategy;

import com.geektrust.lengaburu.war.DeploymentBuilder;
import com.geektrust.lengaburu.war.entities.battalion.Battalion;

public class HorseBattalionStrategy extends BaseStrategy {

    @Override
    protected Battalion getFelicorniaDeployment(DeploymentBuilder deploymentBuilder) {
        return deploymentBuilder.getTargetDeployment().getHorses();
    }

    @Override
    protected Battalion getLengaburuCapacity(DeploymentBuilder deploymentBuilder) {
        return deploymentBuilder.getLengaburuStrength().getHorses();
    }

    @Override
    protected Battalion getFelicorniaLowerDeployment(DeploymentBuilder deploymentBuilder) {
        return null;
    }

    @Override
    protected Battalion getLengaburuLowerCapacity(DeploymentBuilder deploymentBuilder) {
        return null;
    }

    @Override
    protected void updateDeployment(DeploymentBuilder deploymentBuilder, int deploymentStrength) {
        deploymentBuilder.getDeployment().withHorses(deploymentBuilder.getDeployment().getHorses() + deploymentStrength);
    }

    @Override
    protected void updateLowerDeployment(DeploymentBuilder deploymentBuilder, int deploymentStrength) {
        // Do nothing
    }
}
