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
        // in case of excess strength
        if (lengaburuBattalionCapacity.getStrength() * lengaburuPlanetFactor >= felicorniaBattalionDeployment.getStrength()) {
            updateDeployment(lengaburuDeployment, (int) Math.round(felicorniaBattalionDeployment.getStrength() / lengaburuPlanetFactor));
            int excess = (int) Math.round(lengaburuBattalionCapacity.getStrength() * lengaburuPlanetFactor - felicorniaBattalionDeployment.getStrength());
            if (felicorniaLowerBattalionDeployment != null && felicorniaLowerBattalionDeployment.getStrength() > 0) {
                if (lengaburuLowerBattalionCapacity.getStrength() * lengaburuPlanetFactor < felicorniaLowerBattalionDeployment.getStrength()) {
                    int shortage = (int) Math.round((felicorniaLowerBattalionDeployment.getStrength()) / lengaburuPlanetFactor - lengaburuLowerBattalionCapacity.getStrength() * lengaburuPlanetFactor);
                    updateDeployment(lengaburuDeployment, (int)Math.round(Math.min(excess, shortage / 2.0)));
                }
            }
        // in case of shortage
        } else {
            updateDeployment(lengaburuDeployment, lengaburuBattalionCapacity.getStrength());
            int shortage = (int) Math.round(felicorniaBattalionDeployment.getStrength() - lengaburuBattalionCapacity.getStrength() * lengaburuPlanetFactor);
            if (felicorniaLowerBattalionDeployment != null && felicorniaLowerBattalionDeployment.getStrength() > 0) {
                if (lengaburuLowerBattalionCapacity.getStrength() * lengaburuPlanetFactor > felicorniaLowerBattalionDeployment.getStrength()) {
                    int excess = (int) Math.round((lengaburuLowerBattalionCapacity.getStrength() * lengaburuPlanetFactor - felicorniaLowerBattalionDeployment.getStrength()) / lengaburuPlanetFactor);
                    updateLowerDeployment(lengaburuDeployment, (int)Math.round(Math.min(excess, shortage * 2.0)));
                }
            }
        }
    }

    abstract protected Battalion getFelicorniaDeployment(BattalionStrength felicorniaDeployment);

    abstract protected Battalion getLengaburuCapacity(BattalionStrength lengaburuStrength);

    abstract protected Battalion getFelicorniaLowerDeployment(BattalionStrength felicorniaDeployment);

    abstract protected Battalion getLengaburuLowerCapacity(BattalionStrength lengaburuStrength);

    abstract protected void updateDeployment(BattalionStrength.Builder deployment, int deploymentStrength);

    abstract protected void updateLowerDeployment(BattalionStrength.Builder deployment, int deploymentStrength);

}
