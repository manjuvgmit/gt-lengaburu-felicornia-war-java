package com.geektrust.lengaburu.war.entities.battalion;

public class ArmouredTankBattalion extends BaseBattalion {
    public ArmouredTankBattalion(Integer strength) {
        super(BattalionType.ARMOURED_TANK, strength);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ArmouredTankBattalion{");
        sb.append("type=").append(type);
        sb.append(", strength=").append(strength);
        sb.append('}');
        return sb.toString();
    }
}
