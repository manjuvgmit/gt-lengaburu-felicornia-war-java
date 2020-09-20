package com.geektrust.lengaburu.war.strategy;

import com.geektrust.lengaburu.war.entities.battalion.DeploymentBuilder;

public class HorseBattalionStrategy extends BaseStrategy {

    @Override
    protected int getFelicorniaDeployment(DeploymentBuilder deploymentBuilder) {
        return deploymentBuilder.getTargetDeployment().getHorses();
    }

    @Override
    protected int getLengaburuCapacity(DeploymentBuilder deploymentBuilder) {
        return deploymentBuilder.getLengaburuStrength().getHorses().getStrength();
    }

    @Override
    protected int getFelicorniaLowerDeployment(DeploymentBuilder deploymentBuilder) {
        return 0;
    }

    @Override
    protected int getLengaburuLowerCapacity(DeploymentBuilder deploymentBuilder) {
        return 0;
    }

    @Override
    protected void updateDeployment(DeploymentBuilder deploymentBuilder, int deploymentStrength) {
        deploymentBuilder.getDeployment().withHorses(deploymentBuilder.getDeployment().getHorses() + deploymentStrength);
//        deploymentBuilder.getTargetDeployment().withHorses(deploymentBuilder.getTargetDeployment().getHorses() - deploymentStrength);
    }

    @Override
    protected void updateLowerDeployment(DeploymentBuilder deploymentBuilder, int deploymentStrength) {
        // Do nothing
    }
}
