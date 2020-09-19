package com.geektrust.lengaburu.war.strategy;

import com.geektrust.lengaburu.war.entities.PlanetFelicornia;
import com.geektrust.lengaburu.war.entities.PlanetLengaburu;
import com.geektrust.lengaburu.war.entities.battalion.Battalion;
import com.geektrust.lengaburu.war.entities.battalion.BattalionStrength;

public abstract class BaseStrategy {
    public void apply(BattalionStrength felicorniaDeployment, BattalionStrength lengaburuStrength, BattalionStrength.Builder lengaburuDeployment) {
        Battalion felicorniaBattalionDeployment = getFelicorniaDeployment(felicorniaDeployment);
        Battalion felicorniaLowerBattalionDeployment = getFelicorniaLowerDeployment(felicorniaDeployment);
        Battalion lengaburuBattalionCapacity = getLengaburuCapacity(lengaburuStrength);
        Battalion lengaburuLowerBattalionCapacity = getLengaburuLowerCapacity(felicorniaDeployment);
        Double lengaburuPlanetFactor = PlanetLengaburu.getInstance().getFactor() / PlanetFelicornia.getInstance().getFactor();

        if (lengaburuBattalionCapacity.getStrength() * lengaburuPlanetFactor >= felicorniaBattalionDeployment.getStrength()) {
            updateDeployment(lengaburuDeployment, (int) Math.round(felicorniaBattalionDeployment.getStrength() / lengaburuPlanetFactor));
        } else {
            updateDeployment(lengaburuDeployment, lengaburuBattalionCapacity.getStrength());
            int shortage = (int) Math.round(felicorniaBattalionDeployment.getStrength() - lengaburuBattalionCapacity.getStrength() * lengaburuPlanetFactor);
            if (felicorniaLowerBattalionDeployment != null && felicorniaLowerBattalionDeployment.getStrength() > 0) {
                if (lengaburuLowerBattalionCapacity.getStrength() * lengaburuPlanetFactor > felicorniaLowerBattalionDeployment.getStrength()) {
                    int excess = (int) Math.round((lengaburuLowerBattalionCapacity.getStrength() * lengaburuPlanetFactor - felicorniaLowerBattalionDeployment.getStrength()) / lengaburuPlanetFactor);
                    updateLowerDeployment(lengaburuDeployment, (int)Math.round(Math.min(excess, shortage * 2.0)));
                }
            } else {
                updateDeployment(lengaburuDeployment, Math.round(lengaburuBattalionCapacity.getStrength()));
            }
        }
    }

    abstract protected Battalion getFelicorniaDeployment(BattalionStrength felicorniaDeployment);

    abstract protected Battalion getLengaburuCapacity(BattalionStrength lengaburuStrength);

    abstract protected void updateDeployment(BattalionStrength.Builder deployment, int deploymentStrength);

    abstract protected void updateLowerDeployment(BattalionStrength.Builder deployment, int deploymentStrength);

    abstract protected Battalion getFelicorniaLowerDeployment(BattalionStrength felicorniaDeployment);

    abstract protected Battalion getLengaburuLowerCapacity(BattalionStrength lengaburuStrength);

}
