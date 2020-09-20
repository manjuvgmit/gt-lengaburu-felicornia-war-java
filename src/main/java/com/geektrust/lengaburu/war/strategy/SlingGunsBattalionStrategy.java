package com.geektrust.lengaburu.war.strategy;

import com.geektrust.lengaburu.war.entities.battalion.DeploymentBuilder;

public class SlingGunsBattalionStrategy extends BaseStrategy {

    @Override
    protected int getFelicorniaDeployment(DeploymentBuilder deploymentBuilder) {
        return deploymentBuilder.getTargetDeployment().getSlingGuns();
    }

    @Override
    protected int getLengaburuCapacity(DeploymentBuilder deploymentBuilder) {
        return deploymentBuilder.getLengaburuStrength().getSlingGuns().getStrength();
    }

    @Override
    protected int getFelicorniaLowerDeployment(DeploymentBuilder deploymentBuilder) {
        return deploymentBuilder.getTargetDeployment().getArmouredTanks();
    }

    @Override
    protected int getLengaburuLowerCapacity(DeploymentBuilder deploymentBuilder) {
        return deploymentBuilder.getLengaburuStrength().getArmouredTanks().getStrength();
    }

    @Override
    protected void updateDeployment(DeploymentBuilder deploymentBuilder, int deploymentStrength) {
        deploymentBuilder.getDeployment().withSlingGuns(deploymentBuilder.getDeployment().getSlingGuns() + deploymentStrength);
//        deploymentBuilder.getTargetDeployment().withSlingGuns(deploymentBuilder.getTargetDeployment().getSlingGuns() - deploymentStrength);
    }

    @Override
    protected void updateLowerDeployment(DeploymentBuilder deploymentBuilder, int deploymentStrength) {
        deploymentBuilder.getDeployment().withArmouredTanks(deploymentBuilder.getDeployment().getArmouredTanks() + deploymentStrength);
//        deploymentBuilder.getTargetDeployment().withArmouredTanks(deploymentBuilder.getTargetDeployment().getArmouredTanks() - deploymentStrength);
    }
}
