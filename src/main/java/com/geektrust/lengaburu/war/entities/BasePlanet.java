package com.geektrust.lengaburu.war.entities;

import com.geektrust.lengaburu.war.entities.battalion.BattalionStrength;

public class BasePlanet {
    protected final BattalionStrength totalStrength;
    protected BattalionStrength deployment;
    protected Double factor;

    public BasePlanet(BattalionStrength totalStrength, Double factor) {
        this.totalStrength = totalStrength;
        this.factor = factor;
    }

    public BasePlanet(int horses, int elephants, int armouredTanks, int slingGuns, double factor) {
        this.totalStrength = new BattalionStrength(horses, elephants, armouredTanks, slingGuns);
        this.factor = factor;
    }

    public BattalionStrength getTotalStrength() {
        return totalStrength;
    }

    public BattalionStrength getDeployment() {
        return deployment;
    }

    public Double getFactor() {
        return factor;
    }

    public void buildUpDeployment(int horses, int elephants, int armouredTanks, int slingGuns) throws Exception {
        this.deployment = new BattalionStrength(horses, elephants, armouredTanks, slingGuns);
        validateDeployment();
    }

    public void setDeployment(BattalionStrength deployment) throws Exception {
        this.deployment = deployment;
        validateDeployment();
    }

    private void validateDeployment() throws Exception {
        if (this.deployment.getHorses().getStrength() > this.getTotalStrength().getHorses().getStrength()
                || this.deployment.getElephants().getStrength() > this.getTotalStrength().getElephants().getStrength()
                || this.deployment.getArmouredTanks().getStrength() > this.getTotalStrength().getArmouredTanks().getStrength()
                || this.deployment.getSlingGuns().getStrength() > this.getTotalStrength().getSlingGuns().getStrength() ) {
            throw new Exception("Deployment exceeds capacity." + "Capacity: " + this.getTotalStrength().toStringCustom() + ", Deployment: " + this.deployment.toStringCustom());
        }
    }
}
