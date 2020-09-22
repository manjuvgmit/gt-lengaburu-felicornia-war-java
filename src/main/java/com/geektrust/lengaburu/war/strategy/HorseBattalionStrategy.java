package com.geektrust.lengaburu.war.strategy;

import com.geektrust.lengaburu.war.DeploymentBuilder;
import com.geektrust.lengaburu.war.entities.battalion.Battalion;

public class HorseBattalionStrategy extends BaseStrategy {

    @Override
    protected Battalion getAttackerDeployment(DeploymentBuilder deploymentBuilder) {
        return deploymentBuilder.getTargetDeployment().getHorses();
    }

    @Override
    protected Battalion getDefenderCapacity(DeploymentBuilder deploymentBuilder) {
        return deploymentBuilder.getLengaburuStrength().getHorses();
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
        deploymentBuilder.getDeployment().withHorses(deploymentBuilder.getDeployment().getHorses() + deploymentStrength);
    }

    @Override
    protected void updateDefenderLowerDeployment(DeploymentBuilder deploymentBuilder, int deploymentStrength) {
        // Do nothing
    }
}
