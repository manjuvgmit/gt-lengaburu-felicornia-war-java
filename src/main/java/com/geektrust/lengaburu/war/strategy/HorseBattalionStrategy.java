package com.geektrust.lengaburu.war.strategy;

import com.geektrust.lengaburu.war.DeploymentBuilder;
import com.geektrust.lengaburu.war.entities.battalion.Battalion;

public class HorseBattalionStrategy extends BaseStrategy {

    @Override
    protected Battalion getAttackerDeployment(DeploymentBuilder deploymentBuilder) {
        return deploymentBuilder.getAttackerDeployment().getHorses();
    }

    @Override
    protected Battalion getDefenderCapacity(DeploymentBuilder deploymentBuilder) {
        return deploymentBuilder.getDefenderStrength().getHorses();
    }

    @Override
    protected Battalion getAttackerLowerDeployment(DeploymentBuilder deploymentBuilder) {
        return null;
    }

    @Override
    protected Battalion getDefenderLowerCapacity(DeploymentBuilder deploymentBuilder) {
        return null;
    }

    @Override
    protected void updateDefenderDeployment(DeploymentBuilder deploymentBuilder, int deploymentStrength) {
        deploymentBuilder.getDefenderDeployment().withHorses(deploymentBuilder.getDefenderDeployment().getHorses() + deploymentStrength);
    }

    @Override
    protected void updateDefenderLowerDeployment(DeploymentBuilder deploymentBuilder, int deploymentStrength) {
        // Do nothing
    }
}
