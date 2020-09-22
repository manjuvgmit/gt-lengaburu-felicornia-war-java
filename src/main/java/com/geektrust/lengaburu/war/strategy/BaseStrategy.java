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
        Battalion attackerBattalionDeployment = getAttackerDeployment(deploymentBuilder);
        Battalion attackerLowerBattalionDeployment = getAttackerLowerDeployment(deploymentBuilder);
        Battalion defenderBattalionCapacity = getDefenderCapacity(deploymentBuilder);
        Battalion defenderLowerBattalionCapacity = getDefenderLowerCapacity(deploymentBuilder);
        int battalionPowerFactor = Objects.nonNull(defenderLowerBattalionCapacity) ? defenderBattalionCapacity.getType().getOrderOfStrength() / defenderLowerBattalionCapacity.getType().getOrderOfStrength() : 1;
        if (defenderBattalionCapacity.getStrength() >= attackerBattalionDeployment.getStrength()) {
            handleExcessCapacityScenario(deploymentBuilder, attackerBattalionDeployment, attackerLowerBattalionDeployment, defenderBattalionCapacity, defenderLowerBattalionCapacity, battalionPowerFactor);
        } else {
            handleShortageCapacityScenario(deploymentBuilder, attackerBattalionDeployment, attackerLowerBattalionDeployment, defenderBattalionCapacity, defenderLowerBattalionCapacity, battalionPowerFactor);
        }
    }

    private void handleShortageCapacityScenario(DeploymentBuilder deploymentBuilder, Battalion felicorniaBattalionDeployment, Battalion felicorniaLowerBattalionDeployment, Battalion lengaburuBattalionCapacity, Battalion lengaburuLowerBattalionCapacity, int battalionPowerFactor) {
        updateDefenderDeployment(deploymentBuilder, lengaburuBattalionCapacity.getStrength());
        int lowerBattalionExcess = getValueOrDefault(lengaburuLowerBattalionCapacity) - getValueOrDefault(felicorniaLowerBattalionDeployment);
        if (lowerBattalionExcess > 0) {
            int currentBattalionShortage = getValueOrDefault(felicorniaBattalionDeployment) - getValueOrDefault(lengaburuBattalionCapacity);
            updateDefenderLowerDeployment(deploymentBuilder, Math.min(currentBattalionShortage * battalionPowerFactor, lowerBattalionExcess));
        }
    }

    private void handleExcessCapacityScenario(DeploymentBuilder deploymentBuilder, Battalion felicorniaBattalionDeployment, Battalion felicorniaLowerBattalionDeployment, Battalion lengaburuBattalionCapacity, Battalion lengaburuLowerBattalionCapacity, int battalionPowerFactor) {
        updateDefenderDeployment(deploymentBuilder, felicorniaBattalionDeployment.getStrength());
        int lowerBattalionShortage = getValueOrDefault(felicorniaLowerBattalionDeployment) - getValueOrDefault(lengaburuLowerBattalionCapacity);
        if (lowerBattalionShortage > 0) {
            int currentBattalionExcess = getValueOrDefault(lengaburuBattalionCapacity) - getValueOrDefault(felicorniaBattalionDeployment);
            updateDefenderDeployment(deploymentBuilder, Math.min(currentBattalionExcess, (int)Math.round(1.0 * lowerBattalionShortage / battalionPowerFactor)));
        }
    }

    abstract protected Battalion getAttackerDeployment(DeploymentBuilder deploymentBuilder);

    abstract protected Battalion getDefenderCapacity(DeploymentBuilder deploymentBuilder);

    abstract protected Battalion getAttackerLowerDeployment(DeploymentBuilder deploymentBuilder);

    abstract protected Battalion getDefenderLowerCapacity(DeploymentBuilder deploymentBuilder);

    abstract protected void updateDefenderDeployment(DeploymentBuilder deploymentBuilder, int deploymentStrength);

    abstract protected void updateDefenderLowerDeployment(DeploymentBuilder deploymentBuilder, int deploymentStrength);

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
