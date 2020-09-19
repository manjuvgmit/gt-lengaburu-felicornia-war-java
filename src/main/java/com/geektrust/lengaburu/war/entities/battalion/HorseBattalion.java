package com.geektrust.lengaburu.war.entities.battalion;

public class HorseBattalion extends BaseBattalion {
    public HorseBattalion(Integer strength) {
        super(BattalionType.HORSE, strength);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HorseBattalion{");
        sb.append("type=").append(type);
        sb.append(", strength=").append(strength);
        sb.append('}');
        return sb.toString();
    }
}
