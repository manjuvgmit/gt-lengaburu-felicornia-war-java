package com.geektrust.lengaburu.war.entities.planet;

import com.geektrust.lengaburu.war.entities.battalion.BattalionStrength;

/**
 * This is an abstraction of Planet abstracting basic functionalities are contracted.
 */
public interface Planet {

    /**
     * This method returns the total battalion strength for the planet
     * @return BattalionStrength
     */
    BattalionStrength getTotalStrength();

    /**
     * This method returns the power factor of the planet lie 1.0, 2.0 etc
     * Ex: Planet one is twice the powerful as other then they will have 2.0 and 1.0 respectively
     * @return Double
     */
    Double getPowerFactor();

    /**
     * This method takes deployment as string in the format given below and builds object model of it and returns
     *
     * @param deploymentAsString deployment in string ex: 'NNH NNE NNAT NNSG'
     * @return BattalionStrength object model of the deployment
     */
    BattalionStrength buildUpDeployment(String deploymentAsString) throws Exception;

    /**
     * This method takes deployment as string in the format given below and builds object model of it and returns
     * @param horses
     * @param elephants
     * @param armouredTanks
     * @param slingGuns
     * @return BattalionStrength object model of the deployment
     * @throws Exception
     */
    BattalionStrength buildUpDeployment(int horses, int elephants, int armouredTanks, int slingGuns) throws Exception;
}
