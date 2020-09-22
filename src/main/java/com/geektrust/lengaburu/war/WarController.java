package com.geektrust.lengaburu.war;

import com.geektrust.lengaburu.war.entities.Planet;
import com.geektrust.lengaburu.war.entities.Planets;
import com.geektrust.lengaburu.war.entities.battalion.BattalionStrength;
import com.geektrust.lengaburu.war.entities.battalion.BattalionType;

import java.util.Optional;

/***
 * This class orchestrate the war strategy, determines deployment and returns end result
 */
public class WarController {

    public static final String WINS = "WINS";
    public static final String LOSES = "LOSES";
    public static final String EMPTY_STRING = " ";

    /**
     * This method takes input given from CLI which is Felicornia deployment in the format given below, parses it, determines lengaburu
     * deployment based on Felicornia deployment and decides the result of the war.
     *
     * @param felicorniaDeploymentAsString Felicornia deployment in string ex: 'NNH NNE NNAT NNSG'
     * @return Result of the war and Lengaburu deployment ex: [WINS/LOSES] NNH NNE NNAT NNSG
     */
    public String getPlanetLengaburuDeploymentAndResultForWar(String felicorniaDeploymentAsString) {
        try {
            Optional<Planet> attackingPlanet = Optional.of(felicorniaDeploymentAsString.split(EMPTY_STRING))
                    .filter(strings -> strings.length > 0)
                    .map(strings -> strings[0])
                    .map(string -> string.split("_"))
                    .filter(strings -> strings.length > 0)
                    .map(strings -> strings[0])
                    .map(Planets::valueOf)
                    .map(Planets::getPlanet);
            return attackingPlanet.isPresent()
                    ? getPlanetLengaburuDeploymentAndResultForWar(attackingPlanet.get(), attackingPlanet.get().buildUpDeployment(felicorniaDeploymentAsString), Planets.LENGABURU.getPlanet())
                    : "Attacking planet not found.";
        } catch (Exception exception) {
            return exception.getMessage();
        }
    }

    public String getPlanetLengaburuDeploymentAndResultForWar(Planet attackingPlanet, BattalionStrength deployment, Planet planetUnderAttack) {
        DeploymentBuilder lengaburuDeploymentBuilder = new DeploymentBuilder(
                planetUnderAttack.getTotalStrength(), deployment, attackingPlanet.getPowerFactor() / planetUnderAttack.getPowerFactor()
        );
        BattalionType.getBattalionsOnOrderOfStrength().forEach(battalionType -> battalionType.getStrategy().apply(lengaburuDeploymentBuilder));
        return determinePossibleResult(attackingPlanet, lengaburuDeploymentBuilder, planetUnderAttack);
    }

    /**
     * This method determines the result of the war depending on the deployment of troops from Felicornia and Lengaburu
     *
     * @return Result of the war and Lengaburu deployment ex: [WINS/LOSES] NNH NNE NNAT NNSG
     */
    private String determinePossibleResult(Planet attackingPlanet, DeploymentBuilder lengaburuDeploymentBuilder, Planet planetUnderAttack) {
        return ((lengaburuDeploymentBuilder.getDeployment().build().getTotalBattalionStrength()
                >= lengaburuDeploymentBuilder.getTargetDeployment().getTotalBattalionStrength())
                ? WINS : LOSES
        ) + EMPTY_STRING + lengaburuDeploymentBuilder.getDeployment().build().toStringCustom();
    }

}
