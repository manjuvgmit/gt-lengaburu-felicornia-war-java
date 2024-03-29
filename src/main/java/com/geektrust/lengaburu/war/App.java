/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.geektrust.lengaburu.war;

import com.geektrust.lengaburu.war.entities.planet.Planets;
import com.geektrust.lengaburu.war.utils.MiscUtils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Main app and entry point for the problem
 */
public class App {

    /**
     * Main entry point processing multiple input patterns
     * @param args VM args passed in from CLI
     * @throws Exception throws exception if there any issues with input parameters
     */
    public static void main(String[] args) throws Exception {
        App app = new App();
        if (args.length == 1) {
            System.out.println(String.join("\n", app.processInputFromFile(args[0])));
        } else if (args.length == 5) {
            System.out.println(app.processInputFromCli(args));
        } else {
            System.out.println("Invalid Parameters.");
        }
    }

    /**
     * Assumes passed CLI parameters are program arguments directly and processes them
     * @param args program arguments
     * @return output from the program
     */
    public String processInputFromCli(String[] args) {
        return getPlanetLengaburuDeploymentAndResultForWar(String.join(" ", args));
    }

    private WarController getController() {
        return new WarController();
    }

    /**
     * Assumes passed CLI parameter is path where program arguments are stored.
     * Reads the file and processed each line of the txt file as program argument.
     * @param arg program arguments like 'inputs/sample-01.txt' , '~/Downloads/sample-02.txt'
     * @return output from the program
     */
    public List<String> processInputFromFile(String arg) throws IOException {
        return MiscUtils.getInputFromFile(arg).stream()
                .map(commands -> getPlanetLengaburuDeploymentAndResultForWar(commands))
                .collect(Collectors.toList());
    }

    private String getPlanetLengaburuDeploymentAndResultForWar(String command) {
        return getController().getDefenderDeploymentAndResultForWar(command, Planets.LENGABURU.getPlanet());
    }
}
