package com.geektrust.lengaburu.war.strategy;

import com.geektrust.lengaburu.war.DeploymentBuilder;
import com.geektrust.lengaburu.war.entities.battalion.Battalion;

import java.util.Objects;

import static com.geektrust.lengaburu.war.utils.MiscUtils.getValueOrDefault;

/**
 * This is base class of all deployment strategies to match the enemy deployment.
 * This covers some of the common stuff and depends on some of concrete methods from each of the battalion
 * to orchestrate the deployment strategy.
 */
public abstract class BaseStrategy {

    public void apply(DeploymentBuilder deploymentBuilder) {
        Battalion felicorniaBattalionDeployment = getFelicorniaDeployment(deploymentBuilder);
        Battalion felicorniaLowerBattalionDeployment = getFelicorniaLowerDeployment(deploymentBuilder);
        Battalion lengaburuBattalionCapacity = getLengaburuCapacity(deploymentBuilder);
        Battalion lengaburuLowerBattalionCapacity = getLengaburuLowerCapacity(deploymentBuilder);
        int battalionPowerFactor = Objects.nonNull(lengaburuLowerBattalionCapacity) ? lengaburuBattalionCapacity.getType().getOrderOfStrength() / lengaburuLowerBattalionCapacity.getType().getOrderOfStrength() : 1;
        if (lengaburuBattalionCapacity.getStrength() >= felicorniaBattalionDeployment.getStrength()) {
            handleExcessCapacityScenario(deploymentBuilder, felicorniaBattalionDeployment, felicorniaLowerBattalionDeployment, lengaburuBattalionCapacity, lengaburuLowerBattalionCapacity, battalionPowerFactor);
        } else {
            handleShortageCapacityScenario(deploymentBuilder, felicorniaBattalionDeployment, felicorniaLowerBattalionDeployment, lengaburuBattalionCapacity, lengaburuLowerBattalionCapacity, battalionPowerFactor);
        }
    }

    private void handleShortageCapacityScenario(DeploymentBuilder deploymentBuilder, Battalion felicorniaBattalionDeployment, Battalion felicorniaLowerBattalionDeployment, Battalion lengaburuBattalionCapacity, Battalion lengaburuLowerBattalionCapacity, int battalionPowerFactor) {
        updateDeployment(deploymentBuilder, lengaburuBattalionCapacity.getStrength());
        int lowerBattalionExcess = getValueOrDefault(lengaburuLowerBattalionCapacity) - getValueOrDefault(felicorniaLowerBattalionDeployment);
        if (lowerBattalionExcess > 0) {
            int currentBattalionShortage = getValueOrDefault(felicorniaBattalionDeployment) - getValueOrDefault(lengaburuBattalionCapacity);
            updateLowerDeployment(deploymentBuilder, Math.min(currentBattalionShortage * battalionPowerFactor, lowerBattalionExcess));
        }
    }

    private void handleExcessCapacityScenario(DeploymentBuilder deploymentBuilder, Battalion felicorniaBattalionDeployment, Battalion felicorniaLowerBattalionDeployment, Battalion lengaburuBattalionCapacity, Battalion lengaburuLowerBattalionCapacity, int battalionPowerFactor) {
        updateDeployment(deploymentBuilder, felicorniaBattalionDeployment.getStrength());
        int lowerBattalionShortage = getValueOrDefault(felicorniaLowerBattalionDeployment) - getValueOrDefault(lengaburuLowerBattalionCapacity);
        if (lowerBattalionShortage > 0) {
            int currentBattalionExcess = getValueOrDefault(lengaburuBattalionCapacity) - getValueOrDefault(felicorniaBattalionDeployment);
            updateDeployment(deploymentBuilder, Math.min(currentBattalionExcess, lowerBattalionShortage / battalionPowerFactor));
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
