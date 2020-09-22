package com.geektrust.lengaburu.war.strategy;

import com.geektrust.lengaburu.war.DeploymentBuilder;
import com.geektrust.lengaburu.war.entities.battalion.Battalion;

public class SlingGunsBattalionStrategy extends BaseStrategy {

    @Override
    protected Battalion getFelicorniaDeployment(DeploymentBuilder deploymentBuilder) {
        return deploymentBuilder.getTargetDeployment().getSlingGuns();
    }

    @Override
    protected Battalion getLengaburuCapacity(DeploymentBuilder deploymentBuilder) {
        return deploymentBuilder.getLengaburuStrength().getSlingGuns();
    }

    @Override
    protected Battalion getFelicorniaLowerDeployment(DeploymentBuilder deploymentBuilder) {
        return deploymentBuilder.getTargetDeployment().getArmouredTanks();
    }

    @Override
    protected Battalion getLengaburuLowerCapacity(DeploymentBuilder deploymentBuilder) {
        return deploymentBuilder.getLengaburuStrength().getArmouredTanks();
    }

    @Override
    protected void updateDeployment(DeploymentBuilder deploymentBuilder, int deploymentStrength) {
        deploymentBuilder.getDeployment().withSlingGuns(deploymentBuilder.getDeployment().getSlingGuns() + deploymentStrength);
    }

    @Override
    protected void updateLowerDeployment(DeploymentBuilder deploymentBuilder, int deploymentStrength) {
        deploymentBuilder.getDeployment().withArmouredTanks(deploymentBuilder.getDeployment().getArmouredTanks() + deploymentStrength);
    }
}
