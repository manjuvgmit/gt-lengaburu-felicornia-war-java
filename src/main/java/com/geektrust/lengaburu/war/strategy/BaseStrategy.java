package com.geektrust.lengaburu.war.strategy;

import com.geektrust.lengaburu.war.DeploymentBuilder;
import com.geektrust.lengaburu.war.entities.battalion.Battalion;

import static com.geektrust.lengaburu.war.utils.MiscUtils.getValueOrDefault;

public abstract class BaseStrategy {

    public void apply(DeploymentBuilder deploymentBuilder) {
        Battalion felicorniaBattalionDeployment = getFelicorniaDeployment(deploymentBuilder);
        Battalion felicorniaLowerBattalionDeployment = getFelicorniaLowerDeployment(deploymentBuilder);
        Battalion lengaburuBattalionCapacity = getLengaburuCapacity(deploymentBuilder);
        Battalion lengaburuLowerBattalionCapacity = getLengaburuLowerCapacity(deploymentBuilder);
        if (lengaburuBattalionCapacity.getStrength() >= felicorniaBattalionDeployment.getStrength()) {
            handleExcessStrengthScenario(deploymentBuilder, felicorniaBattalionDeployment, felicorniaLowerBattalionDeployment, lengaburuBattalionCapacity, lengaburuLowerBattalionCapacity);
        } else {
            handleShortageStrengthScenario(deploymentBuilder, felicorniaBattalionDeployment, felicorniaLowerBattalionDeployment, lengaburuBattalionCapacity, lengaburuLowerBattalionCapacity);
        }
    }

    private void handleShortageStrengthScenario(DeploymentBuilder deploymentBuilder, Battalion felicorniaBattalionDeployment, Battalion felicorniaLowerBattalionDeployment, Battalion lengaburuBattalionCapacity, Battalion lengaburuLowerBattalionCapacity) {
        updateDeployment(deploymentBuilder, lengaburuBattalionCapacity.getStrength());
        int lowerBattalionExcess = getValueOrDefault(lengaburuLowerBattalionCapacity) - getValueOrDefault(felicorniaLowerBattalionDeployment);
        if (lowerBattalionExcess > 0) {
            int currentBattalionShortage = getValueOrDefault(felicorniaBattalionDeployment) - getValueOrDefault(lengaburuBattalionCapacity);
            updateLowerDeployment(deploymentBuilder, (int)Math.round(Math.min(currentBattalionShortage * 2.0, lowerBattalionExcess)));
        }
    }

    private void handleExcessStrengthScenario(DeploymentBuilder deploymentBuilder, Battalion felicorniaBattalionDeployment, Battalion felicorniaLowerBattalionDeployment, Battalion lengaburuBattalionCapacity, Battalion lengaburuLowerBattalionCapacity) {
        updateDeployment(deploymentBuilder, felicorniaBattalionDeployment.getStrength());
        int lowerBattalionShortage = getValueOrDefault(felicorniaLowerBattalionDeployment) - getValueOrDefault(lengaburuLowerBattalionCapacity);
        if (lowerBattalionShortage > 0) {
            int currentBattalionExcess = getValueOrDefault(lengaburuBattalionCapacity) - getValueOrDefault(felicorniaBattalionDeployment);
            updateDeployment(deploymentBuilder, (int)Math.round(Math.min(currentBattalionExcess, lowerBattalionShortage/2.0)));
        }
    }

    abstract protected Battalion getFelicorniaDeployment(DeploymentBuilder deploymentBuilder);

    abstract protected Battalion getLengaburuCapacity(DeploymentBuilder deploymentBuilder);

    abstract protected Battalion getFelicorniaLowerDeployment(DeploymentBuilder deploymentBuilder);

    abstract protected Battalion getLengaburuLowerCapacity(DeploymentBuilder deploymentBuilder);

    abstract protected void updateDeployment(DeploymentBuilder deploymentBuilder, int deploymentStrength);

    abstract protected void updateLowerDeployment(DeploymentBuilder deploymentBuilder, int deploymentStrength);

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
