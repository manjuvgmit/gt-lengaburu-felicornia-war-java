package com.geektrust.lengaburu.war.entities.battalion;

public class ElephantBattalion extends BaseBattalion {
    public ElephantBattalion(Integer strength) {
        super(BattalionType.ELEPHANT, strength);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ElephantBattalion{");
        sb.append("type=").append(type);
        sb.append(", strength=").append(strength);
        sb.append('}');
        return sb.toString();
    }
}
