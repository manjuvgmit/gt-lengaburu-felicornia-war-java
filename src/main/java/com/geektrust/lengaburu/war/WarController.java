package com.geektrust.lengaburu.war;

import com.geektrust.lengaburu.war.entities.PlanetFelicornia;
import com.geektrust.lengaburu.war.entities.PlanetLengaburu;
import com.geektrust.lengaburu.war.entities.Planets;
import com.geektrust.lengaburu.war.entities.battalion.BattalionType;
import com.geektrust.lengaburu.war.entities.battalion.DeploymentBuilder;

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
    public String getPlanetLengaburuDeploymentAndResult(String felicorniaDeploymentAsString) {
        try {
            DeploymentBuilder lengaburuDeploymentBuilder = new DeploymentBuilder(
                    Planets.LENGABURU.getPlanet().getTotalStrength(),
                    Planets.FELICORNIA.getPlanet().buildUpDeployment(felicorniaDeploymentAsString),
                    PlanetFelicornia.getInstance().getPowerFactor() / PlanetLengaburu.getInstance().getPowerFactor()
            );
            BattalionType.getBattalionsOnOrderOfStrength().forEach(battalionType -> battalionType.getStrategy().apply(lengaburuDeploymentBuilder));
            return determinePossibleResult(lengaburuDeploymentBuilder);
        } catch (Exception exception) {
            return exception.getMessage();
        }
    }

    /**
     * This method determines the result of the war depending on the deployment of troops from Felicornia and Lengaburu
     *
     * @return Result of the war and Lengaburu deployment ex: [WINS/LOSES] NNH NNE NNAT NNSG
     */
    private String determinePossibleResult(DeploymentBuilder lengaburuDeploymentBuilder) {
        return ((lengaburuDeploymentBuilder.getDeployment().build().getTotalBattalionStrength() * PlanetLengaburu.getInstance().getPowerFactor()
                >= lengaburuDeploymentBuilder.getTargetDeployment().build().getTotalBattalionStrength() * PlanetFelicornia.getInstance().getPowerFactor())
                ? WINS : LOSES
        ) + EMPTY_STRING + lengaburuDeploymentBuilder.getDeployment().build().toStringCustom();
    }

}
