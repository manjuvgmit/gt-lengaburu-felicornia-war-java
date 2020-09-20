package com.geektrust.lengaburu.war.entities.battalion;

import com.geektrust.lengaburu.war.strategy.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public enum BattalionType {
    HORSE("H", (int)Math.pow(2, 1), new HorseBattalionStrategy()),
    ELEPHANT("E",(int)Math.pow(2, 2), new ElephantBattalionStrategy()),
    ARMOURED_TANK("AT",(int)Math.pow(2, 3), new ArmouredTankBattalionStrategy()),
    SLING_GUN("SG",(int)Math.pow(2, 4), new SlingGunsBattalionStrategy());

    private final String shortName;
    private final Integer orderOfStrength;
    private final BaseStrategy strategy;

    BattalionType(String shortName, int orderOfStrength, BaseStrategy strategy) {
        this.shortName = shortName;
        this.orderOfStrength = orderOfStrength;
        this.strategy = strategy;
    }

    public String getShortName() {
        return shortName;
    }

    public int getOrderOfStrength() {
        return orderOfStrength;
    }

    public BaseStrategy getStrategy() {
        return strategy;
    }

    public Set<BattalionType> getLowerBattalions() {
        return getBattalions(e -> e.orderOfStrength < this.orderOfStrength);
    }

    public Set<BattalionType> getHigherBattalions() {
        return getBattalions(e -> e.orderOfStrength > this.orderOfStrength);
    }

    public Set<BattalionType> getBattalions(Predicate<BattalionType> predicate) {
        return Arrays.stream(values())
                .filter(predicate)
                .collect(Collectors.toSet());
    }

    public static Set<BattalionType> getBattalionsOnOrderOfStrength() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing(o -> o.orderOfStrength))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BattalionType{");
        sb.append("shortName='").append(shortName).append('\'');
        sb.append(", orderOfStrength=").append(orderOfStrength);
        sb.append(", strategy=").append(strategy);
        sb.append('}');
        return sb.toString();
    }
}
