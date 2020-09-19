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

    public void buildUpDeployment(int horses, int elephants, int armouredTanks, int slingGuns) {
        this.deployment = new BattalionStrength(horses, elephants, armouredTanks, slingGuns);
    }

    public void setDeployment(BattalionStrength deployment) {
        this.deployment = deployment;
    }
}
