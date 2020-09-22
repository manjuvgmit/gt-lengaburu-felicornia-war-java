package com.geektrust.lengaburu.war.strategy;

import com.geektrust.lengaburu.war.DeploymentBuilder;
import com.geektrust.lengaburu.war.entities.battalion.Battalion;

public class ArmouredTankBattalionStrategy extends BaseStrategy {

    @Override
    protected Battalion getAttackerDeployment(DeploymentBuilder deploymentBuilder) {
        return deploymentBuilder.getTargetDeployment().getArmouredTanks();
    }

    @Override
    protected Battalion getDefenderCapacity(DeploymentBuilder deploymentBuilder) {
        return deploymentBuilder.getLengaburuStrength().getArmouredTanks();
    }

    @Override
    protected Battalion getAttackerLowerDeployment(DeploymentBuilder deploymentBuilder) {
        return deploymentBuilder.getTargetDeployment().getElephants();
    }

    @Override
    protected Battalion getDefenderLowerCapacity(DeploymentBuilder deploymentBuilder) {
        return deploymentBuilder.getLengaburuStrength().getElephants();
    }

    @Override
    protected void updateDefenderDeployment(DeploymentBuilder deploymentBuilder, int deploymentStrength) {
        deploymentBuilder.getDeployment().withArmouredTanks(deploymentBuilder.getDeployment().getArmouredTanks() + deploymentStrength);
    }

    @Override
    protected void updateDefenderLowerDeployment(DeploymentBuilder deploymentBuilder, int deploymentStrength) {
        deploymentBuilder.getDeployment().withElephants(deploymentBuilder.getDeployment().getElephants() + deploymentStrength);
    }
}
