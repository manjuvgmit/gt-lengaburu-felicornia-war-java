package com.geektrust.lengaburu.war.strategy;

import com.geektrust.lengaburu.war.DeploymentBuilder;
import com.geektrust.lengaburu.war.entities.battalion.Battalion;

public class SlingGunsBattalionStrategy extends BaseStrategy {

    @Override
    protected Battalion getAttackerDeployment(DeploymentBuilder deploymentBuilder) {
        return deploymentBuilder.getTargetDeployment().getSlingGuns();
    }

    @Override
    protected Battalion getDefenderCapacity(DeploymentBuilder deploymentBuilder) {
        return deploymentBuilder.getLengaburuStrength().getSlingGuns();
    }

    @Override
    protected Battalion getAttackerLowerDeployment(DeploymentBuilder deploymentBuilder) {
        return deploymentBuilder.getTargetDeployment().getArmouredTanks();
    }

    @Override
    protected Battalion getDefenderLowerCapacity(DeploymentBuilder deploymentBuilder) {
        return deploymentBuilder.getLengaburuStrength().getArmouredTanks();
    }

    @Override
    protected void updateDefenderDeployment(DeploymentBuilder deploymentBuilder, int deploymentStrength) {
        deploymentBuilder.getDeployment().withSlingGuns(deploymentBuilder.getDeployment().getSlingGuns() + deploymentStrength);
    }

    @Override
    protected void updateDefenderLowerDeployment(DeploymentBuilder deploymentBuilder, int deploymentStrength) {
        deploymentBuilder.getDeployment().withArmouredTanks(deploymentBuilder.getDeployment().getArmouredTanks() + deploymentStrength);
    }
}
