package com.geektrust.lengaburu.war.entities.battalion;

public class BaseBattalion implements Battalion {
    protected BattalionType type;
    protected Integer strength;

    public BaseBattalion(BattalionType type, Integer strength) {
        this.type = type;
        this.strength = strength;
    }

    @Override
    public BattalionType getType() {
        return type;
    }

    @Override
    public Integer getStrength() {
        return strength;
    }

    @Override
    public Integer getTotalStrength() {
        return strength * type.getOrderOfStrength();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BaseBattalion{");
        sb.append("type=").append(type);
        sb.append(", strength=").append(strength);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public String toStringCustom() {
        final StringBuilder sb = new StringBuilder();
        sb.append(strength).append(type.getShortName());
        return sb.toString();
    }
}
