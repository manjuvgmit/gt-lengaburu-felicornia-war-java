package com.geektrust.lengaburu.war;

import com.geektrust.lengaburu.war.entities.PlanetFelicornia;
import com.geektrust.lengaburu.war.entities.PlanetLengaburu;
import com.geektrust.lengaburu.war.entities.battalion.BattalionStrength;
import com.geektrust.lengaburu.war.entities.battalion.BattalionType;

import static com.geektrust.lengaburu.war.utils.MiscUtils.getIntegerValue;

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
     * @param felicorniaDeploymentAsString Felicornia deployment in string ex: 'NNH NNE NNAT NNSG'
     * @return Result of the war and Lengaburu deployment ex: [WINS/LOSES] NNH NNE NNAT NNSG
     */
    public String getPlanetLengaburuDeploymentAndResult(String felicorniaDeploymentAsString) {
        BattalionStrength felicorniaDeployment = extractFelicorniaDeployment(felicorniaDeploymentAsString).getDeployment();
        BattalionStrength.Builder lengaburuDeploymentBuilder = new BattalionStrength.Builder();
        BattalionStrength lengaburuTotalStrength = PlanetLengaburu.getInstance().getTotalStrength();
        BattalionType.getBattalionsOnOrderOfStrength().forEach(battalionType -> battalionType.getStrategy().apply(
                felicorniaDeployment, lengaburuTotalStrength, lengaburuDeploymentBuilder)
        );
        PlanetLengaburu.getInstance().setDeployment(lengaburuDeploymentBuilder.build());
        return determinePossibleResult();
    }

    /**
     * This method determines the result of the war depending on the deployment of troops from Felicornia and Lengaburu
     * @return Result of the war and Lengaburu deployment ex: [WINS/LOSES] NNH NNE NNAT NNSG
     */
    private String determinePossibleResult() {
        return ((PlanetLengaburu.getInstance().getDeployment().getTotalBattalionStrength() * PlanetLengaburu.getInstance().getFactor()
                >= PlanetFelicornia.getInstance().getDeployment().getTotalBattalionStrength())
                ? WINS : LOSES
        ) + EMPTY_STRING + PlanetLengaburu.getInstance().getDeployment().toStringCustom();
    }

    /**
     * This method takes Felicornia deployment as string in the format given below and builds object model
     * @param felicorniaDeploymentAsString Felicornia deployment in string ex: 'NNH NNE NNAT NNSG'
     * @return PlanetFelicornia object model
     */
    private PlanetFelicornia extractFelicorniaDeployment(String felicorniaDeploymentAsString) {
        String[] parameters = felicorniaDeploymentAsString.split(EMPTY_STRING);
        PlanetFelicornia felicornia = PlanetFelicornia.getInstance();
        felicornia.buildUpDeployment(
                getIntegerValue(parameters[1], BattalionType.HORSE.getShortName()),
                getIntegerValue(parameters[2], BattalionType.ELEPHANT.getShortName()),
                getIntegerValue(parameters[3], BattalionType.ARMOURED_TANK.getShortName()),
                getIntegerValue(parameters[4], BattalionType.SLING_GUN.getShortName())
        );
        return felicornia;
    }
}
