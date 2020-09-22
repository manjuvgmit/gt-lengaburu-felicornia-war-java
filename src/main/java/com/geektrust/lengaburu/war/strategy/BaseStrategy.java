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

    private void handleShortageCapacityScenario(DeploymentBuilder deploymentBuilder, Battalion attackerBattalionDeployment, Battalion attackerLowerBattalionDeployment, Battalion defenderBattalionCapacity, Battalion defenderLowerBattalionCapacity, int battalionPowerFactor) {
        updateDefenderDeployment(deploymentBuilder, defenderBattalionCapacity.getStrength());
        int lowerBattalionExcess = getValueOrDefault(defenderLowerBattalionCapacity) - getValueOrDefault(attackerLowerBattalionDeployment);
        if (lowerBattalionExcess > 0) {
            int currentBattalionShortage = getValueOrDefault(attackerBattalionDeployment) - getValueOrDefault(defenderBattalionCapacity);
            updateDefenderLowerDeployment(deploymentBuilder, Math.min(currentBattalionShortage * battalionPowerFactor, lowerBattalionExcess));
        }
    }

    private void handleExcessCapacityScenario(DeploymentBuilder deploymentBuilder, Battalion attackerBattalionDeployment, Battalion attackerLowerBattalionDeployment, Battalion defenderBattalionCapacity, Battalion defenderLowerBattalionCapacity, int battalionPowerFactor) {
        updateDefenderDeployment(deploymentBuilder, attackerBattalionDeployment.getStrength());
        int lowerBattalionShortage = getValueOrDefault(attackerLowerBattalionDeployment) - getValueOrDefault(defenderLowerBattalionCapacity);
        if (lowerBattalionShortage > 0) {
            int currentBattalionExcess = getValueOrDefault(defenderBattalionCapacity) - getValueOrDefault(attackerBattalionDeployment);
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
