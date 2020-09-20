package com.geektrust.lengaburu.war.strategy;

import com.geektrust.lengaburu.war.entities.battalion.DeploymentBuilder;

public abstract class BaseStrategy {

    public void apply(DeploymentBuilder deploymentBuilder) {
        int felicorniaBattalionDeployment = getFelicorniaDeployment(deploymentBuilder);
        int felicorniaLowerBattalionDeployment = getFelicorniaLowerDeployment(deploymentBuilder);
        int lengaburuBattalionCapacity = getLengaburuCapacity(deploymentBuilder);
        int lengaburuLowerBattalionCapacity = getLengaburuLowerCapacity(deploymentBuilder);
        if (lengaburuBattalionCapacity >= felicorniaBattalionDeployment) {
            handleExcessStrengthScenario(deploymentBuilder, felicorniaBattalionDeployment, felicorniaLowerBattalionDeployment, lengaburuBattalionCapacity, lengaburuLowerBattalionCapacity);
        } else {
            handleShortageStrengthScenario(deploymentBuilder, felicorniaBattalionDeployment, felicorniaLowerBattalionDeployment, lengaburuBattalionCapacity, lengaburuLowerBattalionCapacity);
        }
    }

    private void handleShortageStrengthScenario(DeploymentBuilder deploymentBuilder, int felicorniaBattalionDeployment, int felicorniaLowerBattalionDeployment, int lengaburuBattalionCapacity, int lengaburuLowerBattalionCapacity) {
        updateDeployment(deploymentBuilder, lengaburuBattalionCapacity);
        int lowerBattalionExcess = lengaburuLowerBattalionCapacity - felicorniaLowerBattalionDeployment;
        if (lowerBattalionExcess > 0) {
            int currentBattalionShortage = felicorniaBattalionDeployment - lengaburuBattalionCapacity;
            updateLowerDeployment(deploymentBuilder, (int)Math.round(Math.min(currentBattalionShortage * 2.0, lowerBattalionExcess)));
        }
    }

    private void handleExcessStrengthScenario(DeploymentBuilder deploymentBuilder, int felicorniaBattalionDeployment, int felicorniaLowerBattalionDeployment, int lengaburuBattalionCapacity, int lengaburuLowerBattalionCapacity) {
        updateDeployment(deploymentBuilder, felicorniaBattalionDeployment);
        int lowerBattalionShortage = felicorniaLowerBattalionDeployment - lengaburuLowerBattalionCapacity;
        if (lowerBattalionShortage > 0) {
            int currentBattalionExcess = lengaburuBattalionCapacity - felicorniaBattalionDeployment;
            updateDeployment(deploymentBuilder, (int)Math.round(Math.min(currentBattalionExcess, lowerBattalionShortage/2.0)));
        }
    }

    abstract protected int getFelicorniaDeployment(DeploymentBuilder deploymentBuilder);

    abstract protected int getLengaburuCapacity(DeploymentBuilder deploymentBuilder);

    abstract protected int getFelicorniaLowerDeployment(DeploymentBuilder deploymentBuilder);

    abstract protected int getLengaburuLowerCapacity(DeploymentBuilder deploymentBuilder);

    abstract protected void updateDeployment(DeploymentBuilder deploymentBuilder, int deploymentStrength);

    abstract protected void updateLowerDeployment(DeploymentBuilder deploymentBuilder, int deploymentStrength);

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
