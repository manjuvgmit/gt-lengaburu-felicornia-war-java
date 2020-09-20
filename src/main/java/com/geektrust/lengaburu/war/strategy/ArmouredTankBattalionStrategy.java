package com.geektrust.lengaburu.war.strategy;

import com.geektrust.lengaburu.war.entities.battalion.Battalion;
import com.geektrust.lengaburu.war.entities.battalion.BattalionStrength;
import com.geektrust.lengaburu.war.entities.battalion.DeploymentBuilder;

public class ArmouredTankBattalionStrategy extends BaseStrategy {

    @Override
    protected int getFelicorniaDeployment(DeploymentBuilder deploymentBuilder) {
        return deploymentBuilder.getTargetDeployment().getArmouredTanks();
    }

    @Override
    protected int getLengaburuCapacity(DeploymentBuilder deploymentBuilder) {
        return deploymentBuilder.getLengaburuStrength().getArmouredTanks().getStrength();
    }

    @Override
    protected int getFelicorniaLowerDeployment(DeploymentBuilder deploymentBuilder) {
        return deploymentBuilder.getTargetDeployment().getElephants();
    }

    @Override
    protected int getLengaburuLowerCapacity(DeploymentBuilder deploymentBuilder) {
        return deploymentBuilder.getLengaburuStrength().getElephants().getStrength();
    }

    @Override
    protected void updateDeployment(DeploymentBuilder deploymentBuilder, int deploymentStrength) {
        deploymentBuilder.getDeployment().withArmouredTanks(deploymentBuilder.getDeployment().getArmouredTanks() + deploymentStrength);
//        deploymentBuilder.getTargetDeployment().withArmouredTanks(deploymentBuilder.getTargetDeployment().getArmouredTanks() - deploymentStrength);
    }

    @Override
    protected void updateLowerDeployment(DeploymentBuilder deploymentBuilder, int deploymentStrength) {
        deploymentBuilder.getDeployment().withElephants(deploymentBuilder.getDeployment().getElephants() + deploymentStrength);
//        deploymentBuilder.getTargetDeployment().withElephants(deploymentBuilder.getTargetDeployment().getElephants() - deploymentStrength);
    }
}
