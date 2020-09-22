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
     * @param attackerDeploymentAsString Felicornia deployment in string ex: 'NNH NNE NNAT NNSG'
     * @return Result of the war and Lengaburu deployment ex: [WINS/LOSES] NNH NNE NNAT NNSG
     */
    public String getDefenderDeploymentAndResultForWar(String attackerDeploymentAsString, Planet defenderPlanet) {
        try {
            Optional<Planet> attackingPlanet = getAttackingPlanet(attackerDeploymentAsString);
            return attackingPlanet.isPresent()
                    ? getDefenderDeploymentAndResultForWar(attackingPlanet.get(), attackingPlanet.get().buildUpDeployment(attackerDeploymentAsString), defenderPlanet)
                    : "Attacking planet not found.";
        } catch (Exception exception) {
            return exception.getMessage();
        }
    }

    /**
     * This method takes input given from CLI which is Felicornia deployment in the format given below, parses it, determines lengaburu
     * deployment based on Felicornia deployment and decides the result of the war.
     *
     * @param attackingPlanet Planet : attacking planet
     * @param deployment      BattalionStrength : enemy deployment
     * @param defenderPlanet  Planet : planet under attack
     * @return Result of the war and Lengaburu deployment ex: [WINS/LOSES] NNH NNE NNAT NNSG
     */
    public String getDefenderDeploymentAndResultForWar(Planet attackingPlanet, BattalionStrength deployment, Planet defenderPlanet) {
        DeploymentBuilder lengaburuDeploymentBuilder = new DeploymentBuilder(
                defenderPlanet.getTotalStrength(), deployment, attackingPlanet.getPowerFactor() / defenderPlanet.getPowerFactor()
        );
        BattalionType.getBattalionsOnOrderOfStrength().forEach(battalionType -> battalionType.getStrategy().apply(lengaburuDeploymentBuilder));
        return determinePossibleResult(attackingPlanet, lengaburuDeploymentBuilder, defenderPlanet);
    }

    /**
     * This method determines the result of the war depending on the deployment of troops from Felicornia and Lengaburu
     *
     * @return Result of the war and Lengaburu deployment ex: [WINS/LOSES] NNH NNE NNAT NNSG
     */
    private String determinePossibleResult(Planet attackingPlanet, DeploymentBuilder lengaburuDeploymentBuilder, Planet planetUnderAttack) {
        return (lengaburuDeploymentBuilder.getDefenderDeployment().build().getTotalBattalionStrength() >= lengaburuDeploymentBuilder.getAttackerDeployment().getTotalBattalionStrength()
                ? WINS
                : LOSES
        ) + EMPTY_STRING + lengaburuDeploymentBuilder.getDefenderDeployment().build().toStringCustom();
    }

    /**
     * This method extracts attacking planet on Lengaburu
     *
     * @param felicorniaDeploymentAsString
     * @return
     */
    private Optional<Planet> getAttackingPlanet(String felicorniaDeploymentAsString) {
        return Optional.of(felicorniaDeploymentAsString.split(EMPTY_STRING))
                .filter(strings -> strings.length > 0)
                .map(strings -> strings[0])
                .map(string -> string.split("_"))
                .filter(strings -> strings.length > 0)
                .map(strings -> strings[0])
                .map(Planets::valueOf)
                .map(Planets::getPlanet);
    }
}
